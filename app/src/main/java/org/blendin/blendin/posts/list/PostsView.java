package org.blendin.blendin.posts.list;

import org.blendin.blendin.models.Post;

public interface PostsView {
    void showPost(Post result);

    void showError(String msg);

}
