package org.blendin.blendin.feed;

import android.util.Log;

import org.blendin.blendin.repository.PostsRepository;

public class PostsPresenter {
    public static final String TAG = PostsPresenter.class.getSimpleName();

    private PostsView view;
    private PostsRepository repository;

    public PostsPresenter(PostsView view, PostsRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    void fetchPosts() {
        repository.fetchPosts();
    }

    public void createPost() {
        Log.d(TAG, "createPost: ");
        repository.writeNewPost("yurii", "First post", "Let's make Firebase great again");
    }
}
