package org.blendin.blendin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import org.blendin.blendin.auth.LoginActivity;
import org.blendin.blendin.dagger.DaggerAppComponent;
import org.blendin.blendin.posts.create.NewPostActivity;
import org.blendin.blendin.posts.list.PostsFragment;
import org.blendin.blendin.profile.ProfileActivity;
import org.blendin.blendin.repository.UserRepository;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Inject FirebaseAuth auth;
    @Inject
    UserRepository userRepository;

    @BindView(R.id.feed_view)
    RecyclerView feedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerAppComponent.create().inject(this);
        ButterKnife.bind(this);
        if(savedInstanceState == null && userRepository.getUser() != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PostsFragment())
                    .commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
//        if(auth.getCurrentUser() == null) {
//            startActivity(new Intent(this, LoginActivity.class));
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add) {
            startActivity(new Intent(this, NewPostActivity.class));
            return true;
        }

        if (id == R.id.action_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.logout_button)
    void logout() {
        auth.signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(this, LoginActivity.class));
    }
}
