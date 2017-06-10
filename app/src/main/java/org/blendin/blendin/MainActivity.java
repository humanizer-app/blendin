package org.blendin.blendin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import org.blendin.blendin.auth.LoginActivity;
import org.blendin.blendin.dagger.DaggerAppComponent;
import org.blendin.blendin.posts.list.PostsFragment;
import org.blendin.blendin.models.Post;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements PostsFragment.OnListFragmentInteractionListener{

    @Inject FirebaseAuth auth;

    @BindView(R.id.feed_view)
    RecyclerView feedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerAppComponent.builder().build().inject(this);
        ButterKnife.bind(this);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PostsFragment())
                    .commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(auth.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @OnClick(R.id.logout_button)
    void logout() {
        auth.signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onListFragmentInteraction(Post item) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();
    }
}
