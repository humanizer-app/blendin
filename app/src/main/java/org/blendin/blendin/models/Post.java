package org.blendin.blendin.models;

public class Post {
    public final String id;
    public final String content;
    public final String details;

    public Post(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }
}
