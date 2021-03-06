package org.blendin.blendin.posts.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.blendin.blendin.R;
import org.blendin.blendin.models.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private final List<String> ids = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    public CommentsAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_comment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Comment comment = comments.get(position);
//        holder.titleView.setText(c.userId);
//        holder.detailsView.setText(post.details);
//        holder.answersView.setText("6 answers");
//        CharSequence ago = DateUtils.getRelativeTimeSpanString(post.timestamp,
//                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
//        holder.timeAgoView.setText(ago);
//        Glide.with(holder.context).load("http://goo.gl/gEgYUd")
//                .bitmapTransform(new CropCircleTransformation(holder.context))
//                .into(holder.photoView);

//        holder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(holder.context, PostDetailsActivity.class);
//                intent.putExtra("postId", post.id);
//                holder.context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void add(Comment comment) {
        comments.add(comment);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public ImageView photoView;
        public TextView titleView;
        public TextView detailsView;
        public TextView answersView;
        public TextView timeAgoView;

        public final Context context;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
//            titleView = (TextView) view.findViewById(R.id.author_name);
//            detailsView = (TextView) view.findViewById(R.id.details);
//            answersView = (TextView) view.findViewById(R.id.answer_count);
//            timeAgoView = (TextView) view.findViewById(R.id.time_ago);
            context = view.getContext();
        }
    }
}
