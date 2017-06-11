package org.blendin.blendin.posts.create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import org.blendin.blendin.R;
import org.blendin.blendin.dagger.AppComponent;
import org.blendin.blendin.dagger.DaggerAppComponent;
import org.blendin.blendin.models.Post;

public class NewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        View sendButton = findViewById(R.id.sendBtn);
        final AppComponent component = DaggerAppComponent.create();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                component.postsRepo().addPost(new Post(component.userRepo().getUser(),
                        "Need help", "Description of my serious issue", System.currentTimeMillis(),
                        "Finance"));
                finish();
            }
        });

    }
}
