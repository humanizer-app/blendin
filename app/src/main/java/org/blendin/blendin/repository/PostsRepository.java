package org.blendin.blendin.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.blendin.blendin.models.Post;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class PostsRepository {

    private final DatabaseReference database;
    private final FirebaseAuth auth;

    @Inject
    public PostsRepository(DatabaseReference database, FirebaseAuth auth) {
        this.database = database;
        this.auth = auth;
    }

    public void writeNewPost(String username, String title, String body) {
        String userId = auth.getCurrentUser().getUid();
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = database.child("posts").push().getKey();
        Post post = new Post(userId, username, title, body);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        database.updateChildren(childUpdates);
    }

    public void fetchPosts() {
        DatabaseReference ref = database.child("posts");
        ref.push().setValue("Hello, World!");
    }
}
