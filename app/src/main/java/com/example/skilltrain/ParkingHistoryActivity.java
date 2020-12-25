package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.skilltrain.adapter.ParkingHistoryAdapter;
import com.example.skilltrain.bean.ParkingHistoryBean;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ParkingHistoryActivity extends AppCompatActivity {
    ListView listView;
    ParkingHistoryAdapter adapter;
    List<ParkingHistoryBean.RowsDTO> rowsDTOList;
    TextView backTv, moreTv;
    DateFormat df = new SimpleDateFormat("YYYY-MM-DD mm:ss");
    EditText timeEt;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_history);

        initView();

        initHistoryData();

        onClick();

    }

    private void onClick() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParkingHistoryActivity.this, ParkingMainActiviiy.class);
                startActivity(intent);
                finish();
            }
        });

        moreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowsDTOList.clear();
                initAlltHistoryData();
                moreTv.setVisibility(View.GONE);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowsDTOList.clear();

                initAlltHistoryTimeData();

                moreTv.setVisibility(View.GONE);
            }
        });

    }

    private void initView() {
        timeEt = findViewById(R.id.timeHistoryEt);
        searchBtn = findViewById(R.id.searchHistoryBtn);
        moreTv = findViewById(R.id.historyMoreTv);
        backTv = findViewById(R.id.historyBackTv);
        listView = findViewById(R.id.parkingHistoryLv);
        rowsDTOList = new ArrayList<>();
        adapter = new ParkingHistoryAdapter(ParkingHistoryActivity.this, rowsDTOList);

        listView.setAdapter(adapter);
    }

    //5条
    public void getHistoryData(String json) {
        ParkingHistoryBean parkingHistoryBean = new Gson().fromJson(json, ParkingHistoryBean.class);
        List<ParkingHistoryBean.RowsDTO> rowsDTOList1 = parkingHistoryBean.getRows();

        for (int i = 0; i < rowsDTOList1.size(); i++) {
            String pkHiEnter = rowsDTOList1.get(i).getEntryTime();
            String pkHiOut = rowsDTOList1.get(i).getOutTime();
            String pkHiNum = rowsDTOList1.get(i).getPlateNumber();
            String pkHiMon = rowsDTOList1.get(i).getMonetary() + "元";
            String pkHiName = rowsDTOList1.get(i).getParkName();


            rowsDTOList.add(new ParkingHistoryBean.RowsDTO(pkHiEnter, pkHiOut, pkHiNum, pkHiMon, pkHiName));
            if (rowsDTOList.size() > 5) {
                break;
            }

        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    //全部
    public void getAllHistoryData(String json) {
        ParkingHistoryBean parkingHistoryBean = new Gson().fromJson(json, ParkingHistoryBean.class);
        List<ParkingHistoryBean.RowsDTO> rowsDTOList1 = parkingHistoryBean.getRows();

        for (int i = 0; i < rowsDTOList1.size(); i++) {
            String pkHiEnter = rowsDTOList1.get(i).getEntryTime();
            String pkHiOut = rowsDTOList1.get(i).getOutTime();
            String pkHiNum = rowsDTOList1.get(i).getPlateNumber();
            String pkHiMon = rowsDTOList1.get(i).getMonetary() + "元";
            String pkHiName = rowsDTOList1.get(i).getParkName();

            rowsDTOList.add(new ParkingHistoryBean.RowsDTO(pkHiEnter, pkHiOut, pkHiNum, pkHiMon, pkHiName));
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    //查询时间段内的记录
    public void getAllHistoryTimeData(String json) {
        ParkingHistoryBean parkingHistoryBean = new Gson().fromJson(json, ParkingHistoryBean.class);
        List<ParkingHistoryBean.RowsDTO> rowsDTOList1 = parkingHistoryBean.getRows();
        String time = timeEt.getText().toString();
        for (int i = 0; i < rowsDTOList1.size(); i++) {
            String pkHiEnter = rowsDTOList1.get(i).getEntryTime();
            String pkHiOut = rowsDTOList1.get(i).getOutTime();
            String pkHiNum = rowsDTOList1.get(i).getPlateNumber();
            String pkHiMon = rowsDTOList1.get(i).getMonetary() + "元";
            String pkHiName = rowsDTOList1.get(i).getParkName();
            try {
                Date enter = df.parse(pkHiEnter);
                Date out = df.parse(pkHiOut);
                Date input = df.parse(time);
                if (input.getTime() >= enter.getTime() && input.getTime() <= out.getTime()) {
                    rowsDTOList.add(new ParkingHistoryBean.RowsDTO(pkHiEnter, pkHiOut, pkHiNum, pkHiMon, pkHiName));
                } else {
//                    rowsDTOList.add(new ParkingHistoryBean.RowsDTO("无", "无", "无", "无", "无"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void initAlltHistoryTimeData() {
        HttpUtil.Get("http://dasai.sdvcst.edu.cn:8080/userinfo/parkrecord/list", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getAllHistoryTimeData(responseData);
            }
        });
    }

    public void initAlltHistoryData() {
        HttpUtil.Get("http://dasai.sdvcst.edu.cn:8080/userinfo/parkrecord/list", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getAllHistoryData(responseData);
            }
        });
    }


    public void initHistoryData() {
        HttpUtil.Get("http://dasai.sdvcst.edu.cn:8080/userinfo/parkrecord/list", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getHistoryData(responseData);
            }
        });
    }

}