package org.blendin.blendin.comments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.blendin.blendin.Callback;
import org.blendin.blendin.R;
import org.blendin.blendin.dagger.DaggerAppComponent;
import org.blendin.blendin.models.Comment;
import org.blendin.blendin.repository.CommentRepository;

public class CommentsFragment extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommentRepository repository = DaggerAppComponent.builder().build().commentRepo();
        repository.getCommentsForPost("sddsd", new Callback<Comment>() {
            @Override
            public void onSuccess(Comment result) {
                showComment();
            }

            @Override
            public void onError(String message) {
                showError(message);
            }
        });
    }

    private void showComment() {
        // add comment to the adapter and notifyItemAdded();
    }

    private void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_comments, container, false);

        return view;
    }
}