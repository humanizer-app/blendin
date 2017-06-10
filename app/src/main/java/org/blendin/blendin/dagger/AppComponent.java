package org.blendin.blendin.dagger;

import org.blendin.blendin.MainActivity;
import org.blendin.blendin.auth.LoginActivity;
import org.blendin.blendin.feed.PostsFragment;
import org.blendin.blendin.repository.PostsRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {
    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(PostsFragment postsFragment);

    PostsRepository postsRepository();
}
