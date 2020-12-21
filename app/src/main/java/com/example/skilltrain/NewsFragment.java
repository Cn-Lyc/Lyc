package com.example.skilltrain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.skilltrain.adapter.NewsAdapter;
import com.example.skilltrain.adapter.NewsAdapter2;
import com.example.skilltrain.bean.ZhaunTiNewsBean;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsFragment extends Fragment {
    List<ZhaunTiNewsBean.RowsDTO> rowsDTOList;
    NewsAdapter newsAdapter;
    ListView newsLv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
        initNewsData();

    }

    private void initView() {
        newsLv = getActivity().findViewById(R.id.news_listfg);
        rowsDTOList = new ArrayList<>();
        newsAdapter = new NewsAdapter(getActivity(), rowsDTOList);
        newsLv.setAdapter(newsAdapter);
//        Intent intent = getActivity().getIntent();
//        ArrayList<String> title = intent.getStringArrayListExtra("title");
//        ArrayList<String> content = intent.getStringArrayListExtra("content");
//        ArrayList<String> imgUrl = intent.getStringArrayListExtra("imgUrl");
//
//        //因为获取到的title、content、url都是一组的，所以他们的数组长度是一样的
//        for (int i = 0; i < title.size(); i++) {
//            String title1 = title.get(i);
//            String content1 = content.get(i);
//            String imgUrl1 = imgUrl.get(i);
//            rowsDTOList.add(new ZhaunTiNewsBean.RowsDTO(title1, content1, imgUrl1));
//        }

    }

    public void getNewsJson(String json) {

        ZhaunTiNewsBean zhaunTiNewsBean = new Gson().fromJson(json, ZhaunTiNewsBean.class);

        List<ZhaunTiNewsBean.RowsDTO> rowsDTOList1 = zhaunTiNewsBean.getRows();

        rowsDTOList.clear();

        for (int i = 0; i < rowsDTOList1.size(); i++) {
            String title = rowsDTOList1.get(i).getTitle();
            String content = rowsDTOList1.get(i).getContent();
            String imgUrl = " http://dasai.sdvcst.edu.cn:8080" + rowsDTOList1.get(i).getImgUrl();
            rowsDTOList.add(new ZhaunTiNewsBean.RowsDTO(title, content, imgUrl));
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newsLv.setAdapter(newsAdapter);
            }
        });
    }


    public void initNewsData() {
        HttpUtil.Get("http://dasai.sdvcst.edu.cn:8080/press/press/list", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getNewsJson(responseData);
            }
        });


    }


}