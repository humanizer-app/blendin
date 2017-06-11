package org.blendin.blendin.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Comment {

//    public String authorId;
    public User author;

    public String text;

    public long timestamp;

    public Comment() {
    }

//    public Comment(String authorId, String text, long timestamp) {
//        this.authorId = authorId;
//        this.text = text;
//        this.timestamp = timestamp;
//    }

    public Comment(User author, String text, long timestamp) {
        this.author = author;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Comment(String text) {
        this.text = text;
    }
}
