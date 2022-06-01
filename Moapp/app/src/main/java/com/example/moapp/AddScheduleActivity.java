package com.example.moapp;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddScheduleActivity extends AppCompatActivity {
    private Button startButton, endButton;
    private int hour,minute,second;
    private TimePicker timePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        startButton = (Button)findViewById(R.id.startBtn);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View dialogView = getLayoutInflater().inflate(R.layout.add_schedule_popup, null);
                OnClickHandler(dialogView);

            }
        });
        endButton = findViewById(R.id.endBtn);


    }

    public void OnClickHandler(View view) {
        View dialogView = getLayoutInflater().inflate(R.layout.add_schedule_popup, null);
   timePicker=dialogView.findViewById(R.id.timePicker1);
   timePicker.setIs24HourView(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        hour=timePicker.getCurrentHour();
        minute=timePicker.getCurrentMinute();
       startButton.setText(hour+":"+minute);
    }
});

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
