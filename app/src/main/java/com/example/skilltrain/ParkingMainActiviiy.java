package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skilltrain.adapter.BaseParkingAdapter;
import com.example.skilltrain.bean.BaseParkingBean;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ParkingMainActiviiy extends AppCompatActivity {
    ListView listView;
    List<BaseParkingBean.RowsDTO> rowsDTOList;
    BaseParkingAdapter baseParkingAdapter;
    TextView moreTv, backTv, historyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_main_activiiy);

        initView();

        initInfoData();

        onClick();
    }

    private void onClick() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = view.findViewById(R.id.parkingNameTv);
                String name = textView.getText().toString();
                Intent intent = new Intent(ParkingMainActiviiy.this, ParkingXiangQingActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);

            }
        });

        moreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowsDTOList.clear();
                initAllInfoData();
                moreTv.setVisibility(View.GONE);
            }
        });

        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParkingMainActiviiy.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        historyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParkingMainActiviiy.this, ParkingHistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        listView = findViewById(R.id.baseparkLv);
        rowsDTOList = new ArrayList<>();
        baseParkingAdapter = new BaseParkingAdapter(this, rowsDTOList);
        listView.setAdapter(baseParkingAdapter);

        moreTv = findViewById(R.id.mainMoreTv);
        backTv = findViewById(R.id.parkingMainBackTv);
        historyTv = findViewById(R.id.historyTv);
    }

    //5条
    public void getInfoJson(String json) {
        BaseParkingBean baseParkingBean = new Gson().fromJson(json, BaseParkingBean.class);
        List<BaseParkingBean.RowsDTO> rowsDTOList1 = baseParkingBean.getRows();

        for (int i = 0; i < rowsDTOList1.size(); i++) {
            String pkName = rowsDTOList1.get(i).getParkName();
            String pkVan = rowsDTOList1.get(i).getVacancy();
            String pkAddress = rowsDTOList1.get(i).getAddress();
            String pkRates = rowsDTOList1.get(i).getRates() + " 元/时";
            String pkDistance = rowsDTOList1.get(i).getDistance();
            rowsDTOList.add(new BaseParkingBean.RowsDTO(pkName, pkVan, pkAddress, pkRates, pkDistance));
            if (rowsDTOList.size() > 5) {
                order(0);
                break;
            }

        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                baseParkingAdapter.notifyDataSetChanged();
            }
        });

    }

    //全部
    public void getAllInfoJson(String json) {
        BaseParkingBean baseParkingBean = new Gson().fromJson(json, BaseParkingBean.class);
        List<BaseParkingBean.RowsDTO> rowsDTOList1 = baseParkingBean.getRows();

        for (int i = 0; i < rowsDTOList1.size(); i++) {
            String pkName = rowsDTOList1.get(i).getParkName();
            String pkVan = rowsDTOList1.get(i).getVacancy();
            String pkAddress = rowsDTOList1.get(i).getAddress();
            String pkRates = rowsDTOList1.get(i).getRates() + " 元/时";
            String pkDistance = rowsDTOList1.get(i).getDistance();
            rowsDTOList.add(new BaseParkingBean.RowsDTO(pkName, pkVan, pkAddress, pkRates, pkDistance));

            order(0);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                baseParkingAdapter.notifyDataSetChanged();
            }
        });

    }

    //显示全部
    public void initAllInfoData() {
        HttpUtil.Get(" http://dasai.sdvcst.edu.cn:8080/userinfo/parklot/list", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getAllInfoJson(responseData);
            }
        });


    }

    //默认显示5条
    public void initInfoData() {
        HttpUtil.Get(" http://dasai.sdvcst.edu.cn:8080/userinfo/parklot/list", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getInfoJson(responseData);
            }
        });


    }

    //排序
    public void order(int type) {
        switch (type) {
            case 0:
                Collections.sort(rowsDTOList, new Comparator<BaseParkingBean.RowsDTO>() {
                    @Override
                    public int compare(BaseParkingBean.RowsDTO rowsDTO, BaseParkingBean.RowsDTO t1) {
                        return new Integer(rowsDTO.getDistance()).compareTo(new Integer(t1.getDistance()));
                    }
                });
                break;

        }


    }


}