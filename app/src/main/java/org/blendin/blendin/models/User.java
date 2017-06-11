package org.blendin.blendin.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String userId;
    public String name;
    public String photoUrl;

    public User() {
    }

    public User(String userId, String name, String photoUrl) {
        this.userId = userId;
        this.name = name;
        this.photoUrl = photoUrl;
    }
}
