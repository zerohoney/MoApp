package com.example.moapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moapp.R;


public class SecondActivity extends AppCompatActivity {

    String setHour;
    String setMinute;
    String setDoing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Intent intent = getIntent();
        setHour = intent.getStringExtra("SetHour");
        setMinute = intent.getStringExtra("SetMinute");
        setDoing = intent.getStringExtra("SetDoing");
        TextView todo1Time = (TextView) findViewById(R.id.todoTime1);
        TextView todo1Do = (TextView) findViewById(R.id.todoDo1);
        todo1Time.setText(setHour+":"+setMinute);
        todo1Do.setText(setDoing);

    }

}
