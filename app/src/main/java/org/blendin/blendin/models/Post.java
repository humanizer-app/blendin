package org.blendin.blendin.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

import java.util.Map;

@IgnoreExtraProperties
public class Post {
    public String id;
//    public User user;
    public String userId;
    public String title;
    public String details;
    public long timestamp;
//    List<Comment> comments = Collections.singletonList(new Comment("First comment!"));

    public Post() {
    }

    public Post(String userId, String title, String details, long timestamp) {
        this.userId = userId;
        this.title = title;
        this.details = details;
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return details;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
//        result.put("uid", uid);
        result.put("userId", userId);
        result.put("title", title);
        result.put("details", details);
        result.put("timestamp", timestamp);
//        result.put("comments", comments);

        return result;
    }

    public void setId(String id) {
        this.id = id;
    }
}
