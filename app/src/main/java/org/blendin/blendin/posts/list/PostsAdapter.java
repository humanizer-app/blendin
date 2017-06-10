package org.blendin.blendin.posts.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.blendin.blendin.R;
import org.blendin.blendin.models.Post;
import org.blendin.blendin.posts.details.PostDetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private final List<Post> posts;

    public PostsAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Post post = posts.get(position);
        holder.titleView.setText(post.userId);
        holder.detailsView.setText(post.details);
        holder.answersView.setText("6 answers");

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.context, PostDetailsActivity.class);
                intent.putExtra("postId", post.id);
                holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public ImageView photoView;
        public TextView titleView;
        public TextView detailsView;
        public TextView answersView;

        public final Context context;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            titleView = (TextView) view.findViewById(R.id.title);
            detailsView = (TextView) view.findViewById(R.id.details);
            answersView = (TextView) view.findViewById(R.id.answer_count);
            context = view.getContext();
        }
    }
}
