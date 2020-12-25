package com.example.skilltrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skilltrain.adapter.BusSpAdapter;
import com.example.skilltrain.bean.BusInfoBean;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BusSpecialActivity extends AppCompatActivity {
    ListView listView;
    BusSpAdapter adapter;
    List<BusInfoBean.RowsDTO> rowsDTOList;
    String busId;
    TextView backTv, nextTv;
    String putFirst, putEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_special);

        Intent intent = getIntent();
        busId = intent.getStringExtra("busId");
        Log.d("测试", "onCreate: ");

        initView();

        initBusInfoData();

        onClick();
    }

    private void onClick() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusSpecialActivity.this, BusInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BusSpecialActivity.this, BusSpecialActivity2.class);
                intent1.putExtra("first", putFirst);
                intent1.putExtra("end", putEnd);
                intent1.putExtra("busId", busId);
                startActivity(intent1);
                finish();
            }
        });
    }

    private void initView() {
        nextTv = findViewById(R.id.busSpNextTv1);
        backTv = findViewById(R.id.busSpBackTv1);
        listView = findViewById(R.id.busSpLv1);
        rowsDTOList = new ArrayList<>();
        adapter = new BusSpAdapter(BusSpecialActivity.this, rowsDTOList);
        listView.setAdapter(adapter);
    }

    public void initBusInfoData() {
        HttpUtil.Get("http://dasai.sdvcst.edu.cn:8080/userinfo/lines/list", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getBusInfo(responseData);
            }
        });


    }

    public void getBusInfo(String json) {
        BusInfoBean busInfoBean = new Gson().fromJson(json, BusInfoBean.class);
        List<BusInfoBean.RowsDTO> rowsDTOList1 = busInfoBean.getRows();

        for (int i = 0; i < rowsDTOList1.size(); i++) {
            String infoName = rowsDTOList1.get(i).getName();
            String infoFirst = rowsDTOList1.get(i).getFirst();
            String infoEnd = rowsDTOList1.get(i).getEnd();
            String infoStartTime = rowsDTOList1.get(i).getStartTime();
            String infoPrice = rowsDTOList1.get(i).getPrice();
            String infoMile = rowsDTOList1.get(i).getMileage();

            if (rowsDTOList1.get(i).getId().equals(busId)) {
                rowsDTOList.add(new BusInfoBean.RowsDTO(infoName, infoFirst, infoEnd, infoStartTime, infoPrice, infoMile));
                putFirst = infoFirst;
                putEnd = infoEnd;
            }

        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }


}