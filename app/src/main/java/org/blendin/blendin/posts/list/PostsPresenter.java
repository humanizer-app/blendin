package org.blendin.blendin.posts.list;

import android.util.Log;

import org.blendin.blendin.Callback;
import org.blendin.blendin.models.Post;
import org.blendin.blendin.mvp.Presenter;
import org.blendin.blendin.repository.PostRepository;
import org.blendin.blendin.repository.UserRepository;
import org.greenrobot.eventbus.EventBus;

public class PostsPresenter extends Presenter {
    public static final String TAG = PostsPresenter.class.getSimpleName();

    private PostsView view;
    private PostRepository repo;
    private UserRepository userRepo;

    public PostsPresenter(PostsView view, PostRepository repo, UserRepository userRepo, EventBus bus) {
        super(bus);
        this.view = view;
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public void fetchPosts() {
        repo.getAllPosts(new Callback<Post>() {
            @Override
            public void onSuccess(Post result) {
                view.showPost(result);
            }

            @Override
            public void onError(String message) {
                view.showError(message);
            }
        });
    }

    public void createPost() {
        Log.d(TAG, "addPost: ");
        Post post = new Post(userRepo.getUser(),
                "Please help with doc translation", "I need help with translation to Estonian", System.currentTimeMillis(), "Translation");
        repo.addPost(post);
    }
}
