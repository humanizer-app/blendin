package org.blendin.blendin.dagger;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.blendin.blendin.repository.CommentRepository;
import org.blendin.blendin.repository.PostRepository;
import org.blendin.blendin.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class RepositoryModule {
    @Provides @Singleton
    DatabaseReference provideDatabaseReference() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        database.setPersistenceEnabled(true);
        return database.getReference();
    }

    @Provides @Singleton
    UserRepository provideUserRepository(FirebaseAuth auth) {
        return new UserRepository(auth);
    }

    @Provides @Singleton
    PostRepository providePostRepository(FirebaseAuth auth, DatabaseReference database) {
        return new PostRepository(database, auth);
    }

    @Provides @Singleton
    CommentRepository provideCommentRepository(FirebaseAuth auth, DatabaseReference database) {
        return new CommentRepository(database, auth);
    }
}
