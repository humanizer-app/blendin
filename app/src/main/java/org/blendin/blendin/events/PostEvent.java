package org.blendin.blendin.events;

import org.blendin.blendin.models.Post;

public class PostEvent extends BaseEvent<Post> {
    public PostEvent(Post result, boolean success, String error) {
        super(result, success, error);
    }
}
