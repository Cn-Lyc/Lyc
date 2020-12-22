package com.example.skilltrain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.skilltrain.adapter.BaseParkingAdapter;
import com.example.skilltrain.bean.BaseParkingBean;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BaseParkingInfoFragment extends Fragment {
    ListView listView;
    List<BaseParkingBean.RowsDTO> rowsDTOList;
    BaseParkingAdapter baseParkingAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_base_parking_info_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        initView();

        initInfoData();
    }

    private void initView() {
        listView = getActivity().findViewById(R.id.baseparkLv);
        rowsDTOList = new ArrayList<>();
        baseParkingAdapter = new BaseParkingAdapter(getActivity(), rowsDTOList);


        /* 为什么这里设置适配器没有用 */
        //  listView.setAdapter(baseParkingAdapter);
    }


    public void getInfoJson(String json) {
        BaseParkingBean baseParkingBean = new Gson().fromJson(json, BaseParkingBean.class);
        List<BaseParkingBean.RowsDTO> rowsDTOList1 = baseParkingBean.getRows();

        for (int i = 0; i < rowsDTOList1.size(); i++) {
            String pkName = rowsDTOList1.get(i).getParkName();
            String pkVan = rowsDTOList1.get(i).getVacancy();
            String pkAddress = rowsDTOList1.get(i).getAddress();
            String pkRates = rowsDTOList1.get(i).getRates();
            String pkDistance = rowsDTOList1.get(i).getDistance();

            rowsDTOList.add(new BaseParkingBean.RowsDTO(pkName, pkVan, pkAddress, pkRates, pkDistance));
            order(0);
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(baseParkingAdapter);
            }
        });

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