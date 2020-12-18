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
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String imgUrl = intent.getStringExtra("imgUrl");

        rowsDTOList.add(new ZhaunTiNewsBean.RowsDTO(title, content, imgUrl));

    }
}