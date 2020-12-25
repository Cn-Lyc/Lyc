package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class BusSpecialActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_special3);

        Intent intent = getIntent();
        String time = intent.getStringExtra("time");
        Log.d("TAG3", "onCreate: " + time);

    }
}