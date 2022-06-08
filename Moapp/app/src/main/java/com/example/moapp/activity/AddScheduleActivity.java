package com.example.moapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moapp.R;
import com.example.moapp.model.Schedule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddScheduleActivity extends AppCompatActivity {
    private Button startButton, endButton;
    private EditText summary, descriptionEditText;
    private TextView save, cancel;
    private String startTime, endTime, date;
    private int hour, minute;
    private long second;
    private TimePicker timePicker;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        Intent intent = getIntent();
        date = intent.getExtras().getString("date");
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        startButton = (Button) findViewById(R.id.startBtn);
        endButton = (Button) findViewById(R.id.endBtn);
        summary = findViewById(R.id.summary);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
//원하는 xml 인플레이터
        View dialogView = getLayoutInflater().inflate(R.layout.add_schedule_popup, null);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setStartDate(dialogView);
            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setEndDate(dialogView);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddScheduleActivity.this, MainCalendarActinty.class);
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                Schedule schedule = new Schedule();
                schedule.setStartTime(startTime);
                schedule.setEndTime(endTime);
                schedule.setSummary(summary.getText().toString().trim());
                schedule.setDescription(descriptionEditText.getText().toString().trim());
                databaseReference.child(firebaseUser.getUid()).child(date).child(String.valueOf(second)).setValue(schedule);

                startActivity(intent);


            }
        });
    }

    public void setStartDate(View view) {
        timePicker = view.findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        builder.setView(view);
        builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
                second = System.currentTimeMillis();
                startButton.setText(hour + ":" + minute);
                startTime = hour + "/" + minute;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setEndDate(View view) {
        timePicker = view.findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        builder.setView(view);
        builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
                second = System.currentTimeMillis();
                endButton.setText(hour + ":" + minute);
                endTime = hour + "/" + minute;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
