package com.example.moapp.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moapp.R;
import com.example.moapp.adapter.FriendAdapter;
import com.example.moapp.adapter.FriendListAdapter;
import com.example.moapp.decoration.SetItemDecoration;
import com.example.moapp.model.Friend;
import com.example.moapp.model.UserAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FriendListActivity extends AppCompatActivity {
    private Friend friend;
    private FriendListAdapter friendAdapter;
    private RecyclerView recyclerView;
    private List<String> requestFriendList;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        mAuth = FirebaseAuth.getInstance();
        requestFriendList = new ArrayList<>();
        firebaseUser = mAuth.getCurrentUser();
        friend=new Friend();

        databaseReference = FirebaseDatabase.getInstance().getReference("UserAccount");

        databaseReference.child(firebaseUser.getUid()).child("requestFriend").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                friend = snapshot.getValue(Friend.class);

                databaseReference.child(friend.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        requestFriendList.add(snapshot.getValue(UserAccount.class).getName());
                        recyclerView = findViewById(R.id.friendListCheckRecyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(FriendListActivity.this));
                        SetItemDecoration itemDecoration = new SetItemDecoration(20);
                        recyclerView.addItemDecoration(itemDecoration);
                        friendAdapter = new FriendListAdapter(requestFriendList);
                        recyclerView.setAdapter(friendAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
