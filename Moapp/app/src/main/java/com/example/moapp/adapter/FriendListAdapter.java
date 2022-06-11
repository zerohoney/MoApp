package com.example.moapp.adapter;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moapp.R;
import com.example.moapp.activity.PlusFriendActivity;
import com.example.moapp.decoration.SetItemDecoration;
import com.example.moapp.model.Friend;
import com.example.moapp.model.Schedule;
import com.example.moapp.model.UserAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private List<String> friendList;
    private int selectedPosition = -1;


    public FriendListAdapter(List<String> friendList) {
        this.friendList = friendList;
    }

    @NonNull
    @Override
    public FriendListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.friend_list_category, parent, false);
        FriendListAdapter.ViewHolder viewHolder = new FriendListAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull FriendListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String friendListTempList = friendList.get(position);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        holder.bind(friendListTempList);

        holder.friendName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.friendName.getContext(),"친구가 되었습니다!",Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference("UserAccount").child(firebaseUser.getUid()).child("response").setValue(true);



            }
        });
    }


    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView friendName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            friendName = itemView.findViewById(R.id.reqFriendName);
        }

        private void bind(String friendList) {
            friendName.setText(friendList);
        }
    }
}
