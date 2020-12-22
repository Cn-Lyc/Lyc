package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.skilltrain.adapter.XiangQingParkingAdapter;
import com.example.skilltrain.bean.BaseParkingBean;
import com.example.skilltrain.bean.XiangQingParkingBean;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ParkingXiangQingActivity extends AppCompatActivity {
    XiangQingParkingAdapter adapter;
    List<XiangQingParkingBean.RowsDTO> rowsDTOList;
    ListView listView;
    String name;
    TextView backTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_xiang_qing);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        Log.d("TAG", "onCreate: " + name);

        initView();

        initInfoData();
    }

    private void initView() {
        listView = findViewById(R.id.pkXiangQingLv);
        rowsDTOList = new ArrayList<>();
        adapter = new XiangQingParkingAdapter(this, rowsDTOList);
        listView.setAdapter(adapter);
        backTv=findViewById(R.id.xiangqingBackTv);
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ParkingXiangQingActivity.this,ParkingMainActiviiy.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void getInfoJson(String json) {
        XiangQingParkingBean xiangQingParkingBean = new Gson().fromJson(json, XiangQingParkingBean.class);
        List<XiangQingParkingBean.RowsDTO> rowsDTOList1 = xiangQingParkingBean.getRows();

        for (int i = 0; i < rowsDTOList1.size(); i++) {
            if (rowsDTOList1.get(i).getParkName().equals(name)) {
                String pkName = rowsDTOList1.get(i).getParkName();
                String pkVan = rowsDTOList1.get(i).getVacancy();
                String pkAddress = rowsDTOList1.get(i).getAddress();
                String pkRates = rowsDTOList1.get(i).getRates() + " 元/时";
                String pkDistance = rowsDTOList1.get(i).getDistance();
                String pkMoney =pkRates +" 最高"+rowsDTOList1.get(i).getPriceCaps()+"元/天";
                String pkAll = rowsDTOList1.get(i).getAllPark();
                rowsDTOList.add(new XiangQingParkingBean.RowsDTO(pkName, pkVan, pkAddress, pkRates, pkDistance, pkMoney, pkAll));

            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listView.setAdapter(adapter);
                }
            });
        }


    }


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


}