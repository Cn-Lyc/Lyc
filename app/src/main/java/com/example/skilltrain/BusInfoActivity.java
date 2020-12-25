package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.skilltrain.adapter.BusInfoAdapter;
import com.example.skilltrain.bean.BusInfoBean;
import com.example.skilltrain.bean.BusSubBean;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BusInfoActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    BusInfoAdapter adapter;
    List<BusInfoBean.RowsDTO> busInfoList;
    List<List<BusSubBean.RowsDTO>> busSubList;
    List<BusSubBean.RowsDTO> sub1 = new ArrayList<>();
    List<BusSubBean.RowsDTO> sub2 = new ArrayList<>();
    List<BusSubBean.RowsDTO> sub3 = new ArrayList<>();
    List<BusSubBean.RowsDTO> sub4 = new ArrayList<>();
    TextView backTv;


    /*
     * 这题被坑了，直接用给的总的站点list接口就好，不要想着用id来读数据
     * 用id来读会导致所有的解析数据是一下子发送的，然后会数据错乱。
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_info);

        initView();

        initBusInfoData();

        initBusSub1Data();

        onClick();

    }

    private void onClick() {
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            //i是父布局的位置，i1是子布局的位置
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                i = i + 1;
                String id = String.valueOf(i);
                Intent intent = new Intent(BusInfoActivity.this, BusSpecialActivity.class);
                intent.putExtra("busId", id);
                startActivity(intent);
                Log.d("i", "onChildClick: " + i);
                Log.d("i1", "onChildClick: " + i1);
                return false;
            }
        });
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        backTv = findViewById(R.id.busMainBackTv);
        expandableListView = findViewById(R.id.busInfoExLv);
        busInfoList = new ArrayList<>();
        busSubList = new ArrayList<>();
        adapter = new BusInfoAdapter(BusInfoActivity.this, busInfoList, busSubList);
        expandableListView.setAdapter(adapter);
    }

    //父布局
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
        List<BusInfoBean.RowsDTO> busInfoList1 = busInfoBean.getRows();

        for (int i = 0; i < busInfoList1.size(); i++) {
            String infoName = busInfoList1.get(i).getName();
            String infoFirst = busInfoList1.get(i).getFirst();
            String infoEnd = busInfoList1.get(i).getEnd();
            String infoStartTime = busInfoList1.get(i).getStartTime();
            String infoPrice = busInfoList1.get(i).getPrice();
            String infoMile = busInfoList1.get(i).getMileage();

            busInfoList.add(new BusInfoBean.RowsDTO(infoName, infoFirst, infoEnd, infoStartTime, infoPrice, infoMile));

        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    //子布局
    public void initBusSub1Data() {
        HttpUtil.Get("http://dasai.sdvcst.edu.cn:8080/userinfo/busStop/list", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getBusSub1Info(responseData);
            }
        });


    }

    public void getBusSub1Info(String json) {
        BusSubBean busSubBean = new Gson().fromJson(json, BusSubBean.class);
        List<BusSubBean.RowsDTO> busSubList1 = busSubBean.getRows();
        for (int i = 0; i < busSubList1.size(); i++) {
            String busSubName = busSubList1.get(i).getName();
            String linesId = busSubList1.get(i).getLinesId();

            if (linesId.equals("1")) {
                sub1.add(new BusSubBean.RowsDTO(busSubName));
            } else if (linesId.equals("2")) {
                sub2.add(new BusSubBean.RowsDTO(busSubName));
            } else if (linesId.equals("3")) {
                sub3.add(new BusSubBean.RowsDTO(busSubName));
            } else if (linesId.equals("4")) {
                sub4.add(new BusSubBean.RowsDTO(busSubName));
            }

        }
        busSubList.add(sub1);
        busSubList.add(sub2);
        busSubList.add(sub3);
        busSubList.add(sub4);

    }


}