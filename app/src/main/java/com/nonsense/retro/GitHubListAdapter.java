package com.nonsense.retro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class GitHubListAdapter extends RecyclerView.Adapter<GitHubListAdapter.GitHubRecycleViewAdapterHolder> {
    private static final int LIST_ITEM = 0;
    private static final int GRID_ITEM = 1;
    boolean isSwitchView = true;
    private List<GitModel> userList = new ArrayList<>();


    @Override
    public int getItemViewType(int position) {
        if (isSwitchView) {
            return LIST_ITEM;
        } else {
            return GRID_ITEM;
        }
    }


    public void setData(List<GitModel> list) {
        userList.clear();
        userList.addAll(list);
        this.notifyDataSetChanged();
    }


    @Override
    public GitHubRecycleViewAdapterHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView;
        if (i == LIST_ITEM) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.linear_list_users_fragment, null);
        } else {

            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_layout_users_fragment, null);
            itemView.findViewById(R.id.user_bio).setVisibility(View.INVISIBLE);


        }
        return new GitHubRecycleViewAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubRecycleViewAdapterHolder holder, int position) {
        if (!userList.isEmpty()) {
            GitModel model = userList.get(position);

            if (holder instanceof GitHubRecycleViewAdapterHolder) {
                holder.initializeViews(model);
            }
        }


    }


    @Override
    public int getItemCount() {
        return 15;
    }


    public static class GitHubRecycleViewAdapterHolder extends RecyclerView.ViewHolder {


        CardView cv;
        ImageView mImageView;
        TextView mTextViewUserName;
        TextView mTextViewUserBio;


        public GitHubRecycleViewAdapterHolder(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.card_view);
            mImageView = itemView.findViewById(R.id.user_avatar);
            mTextViewUserName = itemView.findViewById(R.id.user_name);
            mTextViewUserBio = itemView.findViewById(R.id.user_bio);
        }


        private void initializeViews(GitModel model) {
            Picasso.get()
                    .load(model.getAvatarUrl()).fit().centerInside()
                    .into(mImageView);
            mTextViewUserName.setText(model.getLogin());
            mTextViewUserBio.setText(model.getHtmlUrl());
        }


    }
}