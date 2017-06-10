package org.blendin.blendin.posts.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.blendin.blendin.R;
import org.blendin.blendin.dagger.AppComponent;
import org.blendin.blendin.dagger.DaggerAppComponent;
import org.blendin.blendin.models.Post;

import java.util.ArrayList;

public class PostsFragment extends Fragment implements PostsView {
    private static final int COLUMN_NUMBER = 1;

    private PostsPresenter presenter;
    private PostsAdapter adapter;

    public PostsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent appComponent = DaggerAppComponent.builder().build();
        appComponent.inject(this);
        presenter = new PostsPresenter(this, appComponent.postsRepo(), appComponent.userRepo(), appComponent.bus());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.createPost();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (COLUMN_NUMBER <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, COLUMN_NUMBER));
            }
            adapter = new PostsAdapter(new ArrayList<Post>());
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void showPost(Post post) {
        adapter.addPost(post);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
