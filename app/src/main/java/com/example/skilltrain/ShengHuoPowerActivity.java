package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.skilltrain.adapter.PowerAdapter;
import com.example.skilltrain.bean.PowerBean;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ShengHuoPowerActivity extends AppCompatActivity {
    TextView backTv;
    ListView powerLv;
    List<PowerBean.RowsDTO> rowsDTOList;
    PowerAdapter powerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheng_huo_power);

        initView();

        onClick();

        initPowerData();
    }

    private void onClick() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShengHuoPowerActivity.this, ShengHuoJiaoFeiActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        backTv = findViewById(R.id.lifePowerBackTv);
        powerLv = findViewById(R.id.powerLv);
        rowsDTOList = new ArrayList<>();
        powerAdapter = new PowerAdapter(ShengHuoPowerActivity.this, rowsDTOList);
        powerLv.setAdapter(powerAdapter);
    }


    public void initPowerData() {
        HttpUtil.GetHeader("http://dasai.sdvcst.edu.cn:8080/userinfo/electricity/list", MainFragment.token, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getPowerData(responseData);
            }
        });

    }

    public void getPowerData(String json) {
        PowerBean powerBean = new Gson().fromJson(json, PowerBean.class);
        List<PowerBean.RowsDTO> rowsDTOList1 = powerBean.getRows();
        for (int i = 0; i < rowsDTOList1.size(); i++) {
            if (rowsDTOList1.get(i).getLiveName().contains("水费")) {
                String powerName = rowsDTOList1.get(i).getChargeUnit();
                String powerDoor = rowsDTOList1.get(i).getDoorNo();
                String powerDoorName = rowsDTOList1.get(i).getOwnerName();
                String powerBalance = rowsDTOList1.get(i).getBalance();
                String powerCost = rowsDTOList1.get(i).getElectricityMoney();
                rowsDTOList.add(new PowerBean.RowsDTO(powerName, powerDoor, powerDoorName, powerBalance, powerCost));
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                powerAdapter.notifyDataSetChanged();
            }
        });
    }

}