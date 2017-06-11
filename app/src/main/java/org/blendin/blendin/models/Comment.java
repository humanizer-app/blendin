package org.blendin.blendin.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Comment {

    public String text;

    public long timestamp;

    public Comment() {
    }

    public Comment(String text) {
        this.text = text;
    }
}
