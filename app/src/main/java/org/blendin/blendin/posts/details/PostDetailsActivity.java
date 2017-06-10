package org.blendin.blendin.posts.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.blendin.blendin.Callback;
import org.blendin.blendin.R;
import org.blendin.blendin.dagger.DaggerAppComponent;
import org.blendin.blendin.models.Post;

public class PostDetailsActivity extends AppCompatActivity implements PostDetailsView {

    private String postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            postId = extras.getString("postId");
            DaggerAppComponent.create().postsRepo().getPostWith(postId, new Callback<Post>() {
                @Override
                public void onSuccess(Post result) {
                    if(result != null) {
                        showPost(result);
                    } else {
                        showError("Cannot find post with id:" + postId);
                        finish();
                    }
                }

                @Override
                public void onError(String message) {
                    showError(message);
                }
            });
        } else {
            showError("Cannot find post id in extras");
        }
    }

    private void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void showPost(Post post) {
        Toast.makeText(getApplicationContext(), post.details, Toast.LENGTH_SHORT).show();
    }
}
