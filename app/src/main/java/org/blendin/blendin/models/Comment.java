package org.blendin.blendin.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Comment {
    String text;

    public Comment() {
    }

    public Comment(String text) {
        this.text = text;
    }
}
