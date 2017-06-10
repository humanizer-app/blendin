package org.blendin.blendin.feed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.blendin.blendin.R;
import org.blendin.blendin.feed.PostsFragment.OnListFragmentInteractionListener;
import org.blendin.blendin.models.Post;

import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private final List<Post> posts;
    private final OnListFragmentInteractionListener mListener;

    public PostsAdapter(List<Post> items, OnListFragmentInteractionListener listener) {
        posts = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.titleView.setText(post.userId);
        holder.detailsView.setText(post.details);
        holder.answersView.setText("6 answers");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.post);
                }
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
        public final View mView;
        public ImageView photoView;
        public TextView titleView;
        public TextView detailsView;
        public TextView answersView;
        public Post post;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleView = (TextView) view.findViewById(R.id.title);
            detailsView = (TextView) view.findViewById(R.id.details);
            answersView = (TextView) view.findViewById(R.id.answer_count);
        }
    }
}
