package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skilltrain.bean.BusSubBean;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BusSpecialActivity3 extends AppCompatActivity {
    String time, first, end, busId, inBus, outBus, userName, userNum;
    TextView firstTv, endTv, backTv, nextTv;
    EditText userNameEt, userNumEt, inBusEt, outBusEt;
    String reg = "1[3|4|5|7|8]\\d{9}";
    Boolean check = false;
    String price, path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_special3);

        getExtra();

        initView();

        onClick();

    }

    private void onClick() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusSpecialActivity3.this, BusSpecialActivity2.class);
                intent.putExtra("busId", busId);
                startActivity(intent);
                finish();
            }
        });

        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BusSpecialActivity3.this, BusSpecialActivity4.class);
                inBus = inBusEt.getText().toString();
                outBus = outBusEt.getText().toString();
                userName = userNameEt.getText().toString();
                userNum = userNumEt.getText().toString();

//                Pattern pattern = Pattern.compile(reg);
//                Matcher matcher = pattern.matcher(userNum);
//                check = matcher.matches();

                if (inBus.equals("")) {
                    inBus = first;
                }
                if (outBus.equals("")) {
                    outBus = end;
                }
                intent1.putExtra("inBus", inBus);
                intent1.putExtra("time", time);
                intent1.putExtra("busId", busId);
                intent1.putExtra("userName", userName);
                intent1.putExtra("userNum", userNum);
                intent1.putExtra("outBus", outBus);
                intent1.putExtra("price", price);
                intent1.putExtra("path", path);
                startActivity(intent1);
                finish();
            }
        });


    }

    private void initView() {
        userNameEt = findViewById(R.id.busUserNameEt);
        userNumEt = findViewById(R.id.busUserNumEt);
        inBusEt = findViewById(R.id.inBusEt);
        outBusEt = findViewById(R.id.outBusEt);
        backTv = findViewById(R.id.busSpBackTv3);
        nextTv = findViewById(R.id.busSpNextTv3);
        firstTv = findViewById(R.id.busSpFirstTv3);
        endTv = findViewById(R.id.busSpEndTv3);
        firstTv.setText(first);
        endTv.setText(end);


    }

    private void getExtra() {
        Intent intent = getIntent();
        busId = intent.getStringExtra("busId");
        time = intent.getStringExtra("time");
        first = intent.getStringExtra("first");
        end = intent.getStringExtra("end");
        path = intent.getStringExtra("path");
        price = intent.getStringExtra("price");
        Log.d("TAG3", "onCreate: " + time);
        Log.d("TAG3", "onCreate: " + first);
        Log.d("TAG3", "onCreate: " + end);
        Log.d("TAG3", "钱" + price);


    }

    public void initBusStopData() {
        HttpUtil.Get("http://dasai.sdvcst.edu.cn:8080/userinfo/busStop/list?pageNum=1&pageSize=10&linesId=" + busId, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getBusStop(responseData);
            }
        });


    }

    public void getBusStop(String json) {

        BusSubBean busSubBean = new Gson().fromJson(json, BusSubBean.class);
        List<BusSubBean.RowsDTO> rowsDTOList = busSubBean.getRows();
        for (BusSubBean.RowsDTO rowsDTO : rowsDTOList) {
            if (rowsDTO.getName().contains(inBus)) {
//                intent1.putExtra("inBus", inBus);
//                intent1.putExtra("time", time);
//                intent1.putExtra("busId", busId);
//                intent1.putExtra("userName", userName);
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BusSpecialActivity3.this, "请输入正确的上车站点", Toast.LENGTH_SHORT).show();
                    }
                });
            }


            if (check == false) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BusSpecialActivity3.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
//                intent1.putExtra("userNum", userNum);
            }

            if (rowsDTO.getName().contains(outBus)) {
//                intent1.putExtra("outBus", outBus);
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BusSpecialActivity3.this, "请输入正确的下车站点", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }
    }


}