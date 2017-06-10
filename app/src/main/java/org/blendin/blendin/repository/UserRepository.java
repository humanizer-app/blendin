package org.blendin.blendin.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.blendin.blendin.models.User;

public class UserRepository {

    private final FirebaseAuth auth;

    public UserRepository(FirebaseAuth auth) {
        this.auth = auth;
    }

    public User getUser() {
        FirebaseUser user = auth.getCurrentUser();
        return new User(user.getUid(), user.getDisplayName(), user.getPhotoUrl().toString());
    }
}
