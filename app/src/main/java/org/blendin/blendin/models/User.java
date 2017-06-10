package org.blendin.blendin.models;

public class User {
    public String userId;
    public String name;
    public String photoUrl;

    public User(String userId, String name, String photoUrl) {
        this.userId = userId;
        this.name = name;
        this.photoUrl = photoUrl;
    }
}
