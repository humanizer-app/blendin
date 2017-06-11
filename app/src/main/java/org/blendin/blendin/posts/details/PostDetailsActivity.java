package org.blendin.blendin.posts.details;

import android.content.Context;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.blendin.blendin.Callback;
import org.blendin.blendin.R;
import org.blendin.blendin.dagger.AppComponent;
import org.blendin.blendin.dagger.DaggerAppComponent;
import org.blendin.blendin.models.Comment;
import org.blendin.blendin.models.Post;
import org.blendin.blendin.posts.list.PostsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class PostDetailsActivity extends AppCompatActivity implements PostDetailsView {

    private String postId;

    AppComponent component;
    private CommentsAdapter commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        ButterKnife.bind(this);

        component = DaggerAppComponent.create();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            postId = extras.getString("postId");
            component.postsRepo().getPostWith(postId, new Callback<Post>() {
                @Override
                public void onSuccess(Post result) {
                    if(result != null) {
                        showPost(result);
                        fetchComments();
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
            finish();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.commentsListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentsAdapter = new CommentsAdapter();
        recyclerView.setAdapter(commentsAdapter);

        EditText commentEdit = (EditText) findViewById(R.id.commentEditText);

        Button sendButton = (Button) findViewById(R.id.sendBtn);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void fetchComments() {
        component.commentRepo().getCommentsForPost(postId, new Callback<Comment>() {
            @Override
            public void onSuccess(Comment result) {
                commentsAdapter.add(result);
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void showPost(Post post) {

        //ImageView tv = (ImageView) findViewById(R.id.photo_view);
        //tv.setImageResource(R.drawable.);

        //user name to be the text of findViewById(R.id.author_name)

        TextView tv2 = (TextView) findViewById(R.id.details);
        tv2.setText(post.title);
        TextView tv3 = (TextView) findViewById(R.id.postContent);
        tv3.setText(post.details);
        long timestamp = post.timestamp;
        long current = Calendar.getInstance().getTimeInMillis();
        long diff = current-timestamp;
        TextView tv4 = (TextView) findViewById(R.id.time_ago);
        int i = (int) ((diff / (1000*60)) % 60);
        tv4.setText(Integer.toString(i) + " minutes ago");

    //Toast.makeText(getApplicationContext(), post.details, Toast.LENGTH_SHORT).show();
    }
}
