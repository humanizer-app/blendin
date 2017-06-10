package org.blendin.blendin.dagger;

import com.facebook.CallbackManager;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Singleton
    @Provides CallbackManager provideCallbackManager() {
        return CallbackManager.Factory.create();
    }

    @Singleton
    @Provides EventBus provideEventBus() {
        return EventBus.getDefault();
    }
}
