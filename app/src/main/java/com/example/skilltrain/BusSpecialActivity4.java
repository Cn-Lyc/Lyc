package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skilltrain.util.HttpUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BusSpecialActivity4 extends AppCompatActivity {
    String busId, userName, userNum, inBus, outBus, time, price, path;
    TextView userNameTv, userNumTv, inBusTv, outBusTv, timeTv, backTv;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_special4);

        getExtra();

        initView();

        onClick();

    }

    private void onClick() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusSpecialActivity4.this, BusSpecialActivity3.class);
                intent.putExtra("busId", busId);
                startActivity(intent);
                finish();
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPutData();
            }
        });

    }

    private void initView() {
        userNameTv = findViewById(R.id.busSpUserNameTv4);
        userNumTv = findViewById(R.id.busSpUserNumTv4);
        inBusTv = findViewById(R.id.busSpInBusTv4);
        outBusTv = findViewById(R.id.busSpOutBusTv4);
        timeTv = findViewById(R.id.busSpTimeTv4);
        backTv = findViewById(R.id.busSpBackTv4);
        userNameTv.setText(userName);
        userNumTv.setText(userNum);
        inBusTv.setText(inBus);
        outBusTv.setText(outBus);
        timeTv.setText(time);
        submitBtn = findViewById(R.id.busSubMitBtn);
    }

    private void getExtra() {
        Intent intent = getIntent();
        busId = intent.getStringExtra("busId");
        userName = intent.getStringExtra("userName");
        if (userName.equals("")) {
            userName = "admin";
        }
        userNum = intent.getStringExtra("userNum");
        if (userNum.equals("")) {
            userNum = "12345678900";
        }
        inBus = intent.getStringExtra("inBus");
        outBus = intent.getStringExtra("outBus");
        time = intent.getStringExtra("time");
        price = intent.getStringExtra("price");
        path = intent.getStringExtra("path");
        Log.d("TAG4", "getExtra: " + path);

    }


    public void initPutData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("start", inBus);
            jsonObject.put("end", outBus);
            jsonObject.put("userName", userName);
            jsonObject.put("userTel", userNum);
            jsonObject.put("price", price);
            jsonObject.put("path", path);
        } catch (Exception e) {
            e.printStackTrace();
        }


        HttpUtil.PostHeader(" http://dasai.sdvcst.edu.cn:8080/userinfo/busOrders", jsonObject, MainFragment.token, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getPutData(responseData);
            }
        });
    }

    public void getPutData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String msg = jsonObject.getString("msg");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BusSpecialActivity4.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}