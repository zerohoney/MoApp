package com.example.moapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moapp.R;
import com.example.moapp.adapter.TodoAdapter;
import com.example.moapp.decoration.SetItemDecoration;
import com.example.moapp.model.Schedule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainCalendarActinty extends AppCompatActivity {
    private CalendarView calendarView;
    private String date;
    private Button btn;


    private RecyclerView recyclerView;
    private List<Schedule> Schedules = new ArrayList<>();
    private TodoAdapter todoAdapter;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        calendarView = findViewById(R.id.calendar);
        btn = findViewById(R.id.angry_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Data");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                date = String.valueOf(year) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(day);
                Log.d("날짜", date);
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                databaseReference.child(firebaseUser.getUid()).child(String.valueOf(year)).child(String.valueOf(month + 1)).child(String.valueOf(day)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Schedules.clear();
                        Schedule tempSchedule;
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            tempSchedule = dataSnapshot.getValue(Schedule.class);
                            Schedules.add(tempSchedule);
                        }
                        recyclerView = (RecyclerView) findViewById(R.id.todoRecyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainCalendarActinty.this));
                        SetItemDecoration itemDecoration = new SetItemDecoration(20);
                        recyclerView.addItemDecoration(itemDecoration);
                        if (Schedules.size() == 0) {
                            todoAdapter = new TodoAdapter();
                            Schedules.add(new Schedule("", "", "일정을 입력해 주세요", ""));
                            todoAdapter.setScheduleList(Schedules);

                        } else {

                            todoAdapter = new TodoAdapter();
                            todoAdapter.setScheduleList(Schedules);
                        }
                        recyclerView.setAdapter(todoAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainCalendarActinty.this, AddScheduleActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });


    }
}
