package com.example.skilltrain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;

import com.example.skilltrain.adapter.AllTuBiaoAdapter;
import com.example.skilltrain.adapter.TuBiaoAdapter;
import com.example.skilltrain.bean.AllTuBiaoBean;
import com.example.skilltrain.bean.TuBiaoBean;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AllServiceFragment extends Fragment {
    GridView gridView;
    List<TuBiaoBean> rowsDTOList;
    TuBiaoAdapter allTuBiaoAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        return inflater.inflate(R.layout.activity_all_service, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        gridView = getActivity().findViewById(R.id.allService_gv);
        rowsDTOList = new ArrayList<>();
        allTuBiaoAdapter = new TuBiaoAdapter(getActivity(), rowsDTOList);
        gridView.setAdapter(allTuBiaoAdapter);
        rowsDTOList.add(new TuBiaoBean("城市地铁", R.drawable.ditie));
        rowsDTOList.add(new TuBiaoBean("智慧巴士", R.drawable.bus));
        rowsDTOList.add(new TuBiaoBean("门诊预约", R.drawable.menzheng));
        rowsDTOList.add(new TuBiaoBean("生活缴费", R.drawable.live));
        rowsDTOList.add(new TuBiaoBean("违章查询", R.drawable.weizhang));
        rowsDTOList.add(new TuBiaoBean("停车场", R.drawable.pack));

//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                allTuBiaoAdapter.notifyDataSetChanged();
//            }
//        });

        // initTuBiaoData();
    }


    public void getTuBiaoJson(String json) {
        AllTuBiaoBean allTuBiaoBean = new Gson().fromJson(json, AllTuBiaoBean.class);
        List<AllTuBiaoBean.RowsDTO> rowsDTOList1 = allTuBiaoBean.getRows();


        //        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                allTuBiaoAdapter.notifyDataSetChanged();
//            }
//        });
    }

    private void initTuBiaoData() {
        HttpUtil.Get(" http://dasai.sdvcst.edu.cn:8080/service/service/list", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getTuBiaoJson(responseData);
            }
        });
    }


}