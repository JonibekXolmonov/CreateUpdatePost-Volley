package com.example.parsingtask2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parsingtask2.R;
import com.example.parsingtask2.helper.ItemClickListener;
import com.example.parsingtask2.model.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostVH> {

    ArrayList<Post> posts;
    ItemClickListener clickListener;

    public PostAdapter(ArrayList<Post> posts, ItemClickListener clickListener) {
        this.posts = posts;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PostVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostVH holder, int position) {
        Post post = posts.get(position);

        holder.tvTitle.setText(post.title);
        holder.tvBody.setText(post.body);

        holder.ivEdit.setOnClickListener(v -> clickListener.itemClick(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void addPost(Post post) {
        posts.add(post);
        notifyItemInserted(posts.size() - 1);
    }

    public void updatePost(int position, Post post) {
        posts.set(position, post);
        notifyItemChanged(position);
    }

    public static class PostVH extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvBody;
        public LinearLayout llForeground, llBackground;
        public ImageView ivEdit;

        public PostVH(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);

            llForeground = itemView.findViewById(R.id.llForeground);
            ivEdit = itemView.findViewById(R.id.ivEditVisible);
        }
    }

}
