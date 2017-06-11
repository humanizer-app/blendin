package org.blendin.blendin.repository;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import org.blendin.blendin.Callback;
import org.blendin.blendin.models.Comment;
import org.blendin.blendin.models.Post;

public class CommentRepository {

    private final DatabaseReference database;
    private final FirebaseAuth auth;

    private CommentListener commentListener;

    public CommentRepository(DatabaseReference database, FirebaseAuth auth) {
        this.database = database;
        this.auth = auth;
    }

    public void addComment(Comment comment, String postId) {
        database.child("/comments/" + postId).push().setValue(comment);
    }

    public void getCommentsForPost(String postId, final Callback<Comment> callback) {
        database.child("/comments/" + postId);
        if (commentListener != null) {
            database.removeEventListener(commentListener);
        }
        commentListener = new CommentListener(callback);
        database.addChildEventListener(commentListener);
    }

    private static class CommentListener implements ChildEventListener {

        private Callback<Comment> callback;

        public CommentListener(Callback<Comment> callback) {
            this.callback = callback;
        }

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            callback.onSuccess(dataSnapshot.getValue(Comment.class));
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
