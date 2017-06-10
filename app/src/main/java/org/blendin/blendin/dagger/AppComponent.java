package org.blendin.blendin.dagger;

import org.blendin.blendin.MainActivity;
import org.blendin.blendin.auth.LoginActivity;
import org.blendin.blendin.posts.list.PostsFragment;
import org.blendin.blendin.repository.CommentRepository;
import org.blendin.blendin.repository.PostRepository;
import org.blendin.blendin.repository.UserRepository;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(PostsFragment postsFragment);

    PostRepository postsRepo();

    UserRepository userRepo();

    EventBus bus();

    CommentRepository commentRepo();
}
