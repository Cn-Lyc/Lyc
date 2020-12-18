package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.skilltrain.bean.ZhaunTiNewsBean;
import com.example.skilltrain.util.GlideImgUtil;
import com.example.skilltrain.util.HttpUtil;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String title, content, imgUrl;
    Button searchBtn;
    EditText newsEt;
    TextView newsTitle, newsContent;
    List images = new ArrayList();
    String search;
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initTokenData();

        initNewsData();

        initBannerImg();


    }

    //初始化banner里的图片
    private void initBannerImg() {
        images.add("http://dasai.sdvcst.edu.cn:8080/profile/home2.png");
        images.add("http://dasai.sdvcst.edu.cn:8080/profile/home3.png");
        images.add("http://dasai.sdvcst.edu.cn:8080/profile/home4.png");
        images.add("http://dasai.sdvcst.edu.cn:8080/profile/home1.png");
        //设置图片加载器
        banner.setImageLoader(new GlideImgUtil());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    private void initView() {
        searchBtn = findViewById(R.id.searchBtn);
        newsEt = findViewById(R.id.newsEt);
        banner = findViewById(R.id.ad_banner);
    }

    //解析Token的数据来拿到Token
    public void postTokenJson(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            String token = jsonObject.getString("token");
            String msg = jsonObject.getString("msg");
            Log.d("TAG", "令牌 " + token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        Gson gson=new Gson();
//        Bean bean=gson.fromJson(json,TokenBean.class);
//        String token=bean.getToken();
//        Log.d("TAG", "令牌Gson: "+token);
    }

    //发送请求Token的网络请求
    public void initTokenData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", "lyc");
            jsonObject.put("password", "lyc");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpUtil.Post(" http://dasai.sdvcst.edu.cn:8080/login", jsonObject, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("TAG", "请求失败 ");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                postTokenJson(responseData);
            }
        });

    }

    //解析新闻列表的数据来拿到新闻数据
    public void getNewsJson(String json) {

        ZhaunTiNewsBean zhaunTiNewsBean = new Gson().fromJson(json, ZhaunTiNewsBean.class);

        List<ZhaunTiNewsBean.RowsDTO> rowsDTOList = zhaunTiNewsBean.getRows();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = newsEt.getText().toString();
                for (ZhaunTiNewsBean.RowsDTO rowsDTO : rowsDTOList) {
                    title = rowsDTO.getTitle();
                    content = rowsDTO.getContent();
                    //获取到的imgurl是不带前面的http的，要自己加
                    imgUrl = " http://dasai.sdvcst.edu.cn:8080" + rowsDTO.getImgUrl();
                    Log.d("TAG", "url" + imgUrl);
                    /* 此处犯了一个很严重的错误，不应该用search来包含title和content
                     *  应该是用完整的来匹配不完整的
                     *  */
                    if (title.contains(search) || content.contains(search)) {
                        //传递数据给新闻显示界面
                        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                        intent.putExtra("title", title);
                        intent.putExtra("content", content);
                        intent.putExtra("imgUrl", imgUrl);
                        startActivity(intent);
                        break;
                    }

                }

            }
        });

    }

    //发送请求新闻列表的网络请求
    public void initNewsData() {
        HttpUtil.Get(" http://dasai.sdvcst.edu.cn:8080/press/press/list?pageNum=1&pageSize=10", new Callback() {
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