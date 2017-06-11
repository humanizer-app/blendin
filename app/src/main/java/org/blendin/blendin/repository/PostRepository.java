package org.blendin.blendin.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.blendin.blendin.Callback;
import org.blendin.blendin.models.Post;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PostRepository {
    private final DatabaseReference database;
    private final FirebaseAuth auth;
    private PostListener postListener;

    public PostRepository(DatabaseReference database, FirebaseAuth auth) {
        this.database = database;
        this.auth = auth;
    }

    public void addPost(Post post) {
        String userId = auth.getCurrentUser().getUid();
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously

        String key = database.child("/user-posts/" + userId).push().getKey();
        post.setId(key);
        Map<String, Object> postValues = post.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        database.updateChildren(childUpdates);
    }

    public void getPostWith(String id, final Callback<Post> callback) {
        database.child("/posts/" + id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onSuccess(dataSnapshot.getValue(Post.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }

    public void getAllPosts(Callback<Post> callback) {
        if(auth.getCurrentUser() == null) {
            return;
        }
        String userId = auth.getCurrentUser().getUid();

        final DatabaseReference posts = database.child("/user-posts/" + userId);

        if(postListener != null) {
            posts.removeEventListener(postListener);
        }
        postListener = new PostListener(callback);
        posts.addChildEventListener(postListener);
    }

    private static class PostListener implements ChildEventListener {

        Callback<Post> callback;

        public PostListener(Callback<Post> callback) {
            this.callback = callback;
        }

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Post post = dataSnapshot.getValue(Post.class);
            post.id = dataSnapshot.getKey();
            callback.onSuccess(post);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            callback.onError(databaseError.getMessage());
        }
    }
}
