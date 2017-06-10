package org.blendin.blendin.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Post {
    private String userId;
    public final String id;
    public final String content;
    public final String details;

    public Post(String id, String content, String details) {
        this.userId = userId;
        this.id = id;
        this.content = content;
        this.details = details;
    }

    public Post(String userId, String id, String content, String details) {
        this.userId = userId;
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
//        result.put("uid", uid);
//        result.put("author", author);
//        result.put("title", title);
//        result.put("body", body);
//        result.put("starCount", starCount);
//        result.put("stars", stars);

        return result;
    }
}
