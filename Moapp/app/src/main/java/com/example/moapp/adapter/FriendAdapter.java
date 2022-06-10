package com.example.moapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moapp.R;
import com.example.moapp.model.Friend;
import com.example.moapp.model.Schedule;
import com.example.moapp.model.UserAccount;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    private List<UserAccount> friendList;
    private int selectedPosition = -1;


    public FriendAdapter(List<UserAccount> friendList) {
        this.friendList = friendList;
    }

    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.plus_friend_category, parent, false);
        FriendAdapter.ViewHolder viewHolder = new FriendAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.ViewHolder holder, int position) {
        UserAccount friendListTempList = friendList.get(position);
        holder.bind(friendListTempList);


    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView friendName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            friendName = itemView.findViewById(R.id.friendName);
        }

        private void bind(UserAccount friendList) {
            friendName.setText(friendList.getName());
        }
    }
}
