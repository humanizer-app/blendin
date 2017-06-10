package org.blendin.blendin.posts.list;

import android.util.Log;

import org.blendin.blendin.Callback;
import org.blendin.blendin.events.PostEvent;
import org.blendin.blendin.models.Post;
import org.blendin.blendin.mvp.Presenter;
import org.blendin.blendin.repository.PostRepository;
import org.blendin.blendin.repository.UserRepository;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    @Override
    public void onStart() {
        super.onStart();
        fetchPosts();
    }

    void fetchPosts() {
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
        Post post = new Post(userRepo.getUser().userId,
                "First post", "Let's make Firebase great again", System.currentTimeMillis());
        repo.addPost(post);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostEvent(PostEvent event) {
        if(event.isSuccess()) {
            view.showPost(event.getResult());
        } else {
            view.showError(event.getError());
        }
    };
}
