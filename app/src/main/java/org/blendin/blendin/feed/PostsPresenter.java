package org.blendin.blendin.feed;

import org.blendin.blendin.repository.PostsRepository;

public class PostsPresenter {

    private PostsView view;

    public PostsPresenter(PostsView view, PostsRepository repository) {
        this.view = view;
    }

    void fetchPosts() {

    }
}
