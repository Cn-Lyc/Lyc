package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.skilltrain.adapter.WaterAdapter;
import com.example.skilltrain.bean.WaterBean;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ShengHuoWaterManActivity extends AppCompatActivity {
    TextView backTv;
    ListView waterLv;
    WaterAdapter waterAdapter;
    List<WaterBean.RowsDTO> rowsDTOList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheng_huo_water_man);

        initView();

        onClick();

        initWaterData();
    }

    private void onClick() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShengHuoWaterManActivity.this, ShengHuoJiaoFeiActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        backTv = findViewById(R.id.lifeWaterBackTv);
        waterLv = findViewById(R.id.waterLv);
        rowsDTOList = new ArrayList<>();
        waterAdapter = new WaterAdapter(ShengHuoWaterManActivity.this, rowsDTOList);
        waterLv.setAdapter(waterAdapter);
    }

    public void getWaterData(String json) {
        WaterBean waterBeans = new Gson().fromJson(json, WaterBean.class);
        List<WaterBean.RowsDTO> rowsDTOList1 = waterBeans.getRows();

        for (int i = 0; i < rowsDTOList1.size(); i++) {
            String waterName = rowsDTOList1.get(i).getOwnerName();
            String waterDoor = rowsDTOList1.get(i).getDoorNo();
            rowsDTOList.add(new WaterBean.RowsDTO(waterName, waterDoor));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                waterAdapter.notifyDataSetChanged();
            }
        });
    }

    public void initWaterData() {
        HttpUtil.GetHeader("http://dasai.sdvcst.edu.cn:8080/userinfo/feeslist/list? userId=2&classifyId=2", MainFragment.token, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getWaterData(responseData);
            }
        });
    }


}