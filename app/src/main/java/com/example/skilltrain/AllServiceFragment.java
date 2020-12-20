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
    List<AllTuBiaoBean.RowsDTO> rowsDTOList;
    AllTuBiaoAdapter allTuBiaoAdapter;


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
        allTuBiaoAdapter = new AllTuBiaoAdapter(getActivity(), rowsDTOList);
        gridView.setAdapter(allTuBiaoAdapter);


        initTuBiaoData();
    }


    public void getTuBiaoJson(String json) {
        AllTuBiaoBean allTuBiaoBean = new Gson().fromJson(json, AllTuBiaoBean.class);
        List<AllTuBiaoBean.RowsDTO> rowsDTOList1 = allTuBiaoBean.getRows();

        //这边不能用列表遍历，只能用i++的循环
        for (int i = 0; i < rowsDTOList1.size(); i++) {
            String name = rowsDTOList1.get(i).getServiceName();
            String url = rowsDTOList1.get(i).getImgUrl();
            Log.d("TAG", "getTuBiaoJson: " + url);
            rowsDTOList.add(new AllTuBiaoBean.RowsDTO(name, url));
        }
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