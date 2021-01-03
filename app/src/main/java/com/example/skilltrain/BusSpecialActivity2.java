package com.example.skilltrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class BusSpecialActivity2 extends AppCompatActivity {
    CalendarView calendarView;
    TextView backTv, nextTv;
    String time, first, end, busId, price, path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_special2);

        Toast.makeText(this, "请先选择日期，随后点击右上角的下一步进行操作", Toast.LENGTH_LONG).show();

        initView();

        onClick();

        //获取从上个界面拿到的首尾站，和id，这个id过会点返回要重新传给第一步
        Intent intentGet = getIntent();
        first = intentGet.getStringExtra("first");
        end = intentGet.getStringExtra("end");
        busId = intentGet.getStringExtra("busId");
        price = intentGet.getStringExtra("price");
        path = intentGet.getStringExtra("path");
        Log.d("TAG2", "onCreate: " + path);


    }

    private void onClick() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusSpecialActivity2.this, BusSpecialActivity.class);
                intent.putExtra("busId", busId);
                startActivity(intent);
                finish();
            }
        });

        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BusSpecialActivity2.this, BusSpecialActivity3.class);
                intent1.putExtra("time", time);
                intent1.putExtra("first", first);
                intent1.putExtra("end", end);
                intent1.putExtra("busId", busId);
                intent1.putExtra("price", price);
                intent1.putExtra("path", path);
                startActivity(intent1);
                finish();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                i1 = i1 + 1;
                time = i + "年" + i1 + "月" + i2 + "日";
                nextTv.setVisibility(View.VISIBLE);
            }
        });


    }

    private void initView() {
        calendarView = findViewById(R.id.cv);
        backTv = findViewById(R.id.busSpBackTv2);
        nextTv = findViewById(R.id.busSpNextTv2);
    }
}