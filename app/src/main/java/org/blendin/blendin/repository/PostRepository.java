package org.blendin.blendin.repository;

import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import org.blendin.blendin.events.PostEvent;
import org.blendin.blendin.models.Post;
import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class PostRepository {

    private final DatabaseReference database;
    private final EventBus bus;
    private final FirebaseAuth auth;

    @Inject
    public PostRepository(DatabaseReference database, EventBus bus, FirebaseAuth auth) {
        this.database = database;
        this.bus = bus;
        this.auth = auth;
    }

    public void writeNewPost(Post post) {
        String userId = auth.getCurrentUser().getUid();
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously

        String key = database.child("/user-posts/" + userId).push().getKey();
        Map<String, Object> postValues = post.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        database.updateChildren(childUpdates);
    }

    public void fetchPosts() {
        String userId = auth.getCurrentUser().getUid();
        final DatabaseReference posts = database.child("/user-posts/" + userId);
        posts.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Post post = dataSnapshot.getValue(Post.class);
                bus.post(new PostEvent(post, true, null));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                Post post = dataSnapshot.getValue(Post.class);
//                bus.post(new PostEvent(post, true, null));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                bus.post(new PostEvent(null, true, databaseError.getMessage()));
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                posts.push().setValue(
                        new Post("xx" + System.currentTimeMillis(), "First post", "Let's make Firebase great again", 1234567890));
            }
        }, 1000);
    }


}
