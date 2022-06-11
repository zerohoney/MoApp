package com.example.moapp.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moapp.R;
import com.example.moapp.adapter.FriendAdapter;
import com.example.moapp.decoration.SetItemDecoration;
import com.example.moapp.model.Friend;
import com.example.moapp.model.UserAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class
PlusFriendActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private List<UserAccount> userAccounts = new ArrayList<>();
    private RecyclerView recyclerView;
    private FriendAdapter friendAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_friend_activity);

        databaseReference = FirebaseDatabase.getInstance().getReference("UserAccount");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    userAccounts.add(dataSnapshot.getValue(UserAccount.class));

                }
                recyclerView = findViewById(R.id.friendListRecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(PlusFriendActivity.this));
                SetItemDecoration itemDecoration = new SetItemDecoration(20);
                recyclerView.addItemDecoration(itemDecoration);
                friendAdapter = new FriendAdapter(userAccounts);
                recyclerView.setAdapter(friendAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
