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

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
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
    public void onBindViewHolder(@NonNull FriendAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        UserAccount friendListTempList = friendList.get(position);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        holder.bind(friendListTempList);
        long now;
        Date mdDate;
        String recent;
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
        now = System.currentTimeMillis();
        mdDate = new Date(now);
        recent = mFormat.format(mdDate);
        holder.friendName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference("UserAccount");
                if (firebaseUser.getUid().equals(friendListTempList.getUID())) {
                    Toast.makeText(holder.friendName.getContext(), "자기자신한테는 친추를 할 수 없어요 ㅜ", Toast.LENGTH_SHORT).show();

                }
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            friendList.add(dataSnapshot.getValue(UserAccount.class));
                        }
                        Friend friend = new Friend(firebaseUser.getUid());
                        databaseReference.child(friendList.get(position ).getUID()).child("requestFriend").setValue(friend);
                        databaseReference.child(friendList.get(position ).getUID()).child("request").setValue(true);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

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

            friendName = itemView.findViewById(R.id.friendName);
        }

        private void bind(UserAccount friendList) {
            friendName.setText(friendList.getName());
        }
    }
}
