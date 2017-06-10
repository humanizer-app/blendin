package org.blendin.blendin.feed;

import org.blendin.blendin.repository.PostsRepository;

public class PostsPresenter {

    private PostsView view;
    private PostsRepository repository;

    public PostsPresenter(PostsView view, PostsRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    void fetchPosts() {
        repository.fetchPosts();
    }
}
