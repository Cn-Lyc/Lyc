package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.skilltrain.adapter.NewsAdapter;
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

public class NewsActivity extends AppCompatActivity {
    List<ZhaunTiNewsBean.RowsDTO> rowsDTOList;
    NewsAdapter newsAdapter;
    ListView newsLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initView();

        //  initData();

    }


    private void initView() {
        newsLv = findViewById(R.id.news_list);
        rowsDTOList = new ArrayList<>();
        newsAdapter = new NewsAdapter(NewsActivity.this, rowsDTOList);
        newsLv.setAdapter(newsAdapter);

        Intent intent = getIntent();
        ArrayList<String> title = intent.getStringArrayListExtra("title");
        ArrayList<String> content = intent.getStringArrayListExtra("content");
        ArrayList<String> imgUrl = intent.getStringArrayListExtra("imgUrl");

        //因为获取到的title、content、url都是一组的，所以他们的数组长度是一样的
        for (int i = 0; i < title.size(); i++) {
            String title1 = title.get(i);
            String content1 = content.get(i);
            String imgUrl1 = imgUrl.get(i);
            rowsDTOList.add(new ZhaunTiNewsBean.RowsDTO(title1, content1, imgUrl1));
        }


    }
}