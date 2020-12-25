package com.example.skilltrain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;

import com.example.skilltrain.adapter.MainAdapter;
import com.example.skilltrain.adapter.NewsAdapter2;
import com.example.skilltrain.adapter.TuBiaoAdapter;
import com.example.skilltrain.bean.MainNewsBean;
import com.example.skilltrain.bean.TuBiaoBean;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainFragment extends Fragment {
    String title, content, imgUrl;
    ArrayList title1 = new ArrayList();
    ArrayList content1 = new ArrayList();
    ArrayList imgUrl1 = new ArrayList();
    List<TuBiaoBean> tuBiaoBeanList;
    TuBiaoAdapter tuBiaoAdapter;
    GridView gridView;
    Button searchBtn;
    EditText newsEt;
    List images = new ArrayList();
    String search;
    Banner banner;
    Intent intent;
    List<MainNewsBean.RowsDTO> rowsDTOList;
    NewsAdapter2 newsAdapter2;
    ListView mainNewsLv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main_fragment, container, false);

        return view;
    }

    @Override
    public void onStart() {
        Log.d("测试", "MainFragment的onStart()");
        super.onStart();

        initView();

        initTokenData();

        initNewsData();

        initBannerImg();

        checkPad(getActivity());

        initTuBiao();

        gridViewClick();

        initMainNewsData();

    }

    //网格图标里的点击事件
    private void gridViewClick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:

                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(), BusInfoActivity.class);
                        startActivity(intent1);
                        getActivity().finish();
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:
                        Intent intent = new Intent(getActivity(), ParkingMainActiviiy.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;

                    case 6:
//                        Intent intent1 = new Intent(getActivity(), AllServiceFragment.class);
//                        startActivity(intent1);
                        ViewPager vp = getActivity().findViewById(R.id.main_vp);
                        vp.setCurrentItem(1);

                        break;

                }
            }
        });
    }

    //初始化图标
    private void initTuBiao() {
        tuBiaoBeanList = new ArrayList<>();
        Log.d("当前Activity", getActivity().getLocalClassName());

        tuBiaoAdapter = new TuBiaoAdapter(getActivity(), tuBiaoBeanList);
        gridView.setAdapter(tuBiaoAdapter);

        tuBiaoBeanList.add(new TuBiaoBean("城市地铁", R.drawable.ditie));
        tuBiaoBeanList.add(new TuBiaoBean("智慧巴士", R.drawable.bus));
        tuBiaoBeanList.add(new TuBiaoBean("门诊预约", R.drawable.menzheng));
        tuBiaoBeanList.add(new TuBiaoBean("生活缴费", R.drawable.live));
        tuBiaoBeanList.add(new TuBiaoBean("违章查询", R.drawable.weizhang));
        tuBiaoBeanList.add(new TuBiaoBean("停车场", R.drawable.pack));
        tuBiaoBeanList.add(new TuBiaoBean("更多服务", R.drawable.menzheng));


    }

    //    判断是不是ipad
    private void checkPad(Context context) {
        if ((context.getResources().getConfiguration().screenLayout
                & Configuration.COLOR_MODE_HDR_MASK)
                > Configuration.SCREENLAYOUT_SIZE_LARGE) {
            gridView.setNumColumns(6);
        } else {
            gridView.setNumColumns(5);
        }
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
        Log.d("测试", "MainFragment取得的Activity:" + getActivity().getLocalClassName());
        searchBtn = getActivity().findViewById(R.id.searchBtn);
        newsEt = getActivity().findViewById(R.id.newsEt);
        banner = getActivity().findViewById(R.id.ad_banner);
        gridView = getActivity().findViewById(R.id.tubiao_gv);
        mainNewsLv = getActivity().findViewById(R.id.main_news_lv);

        rowsDTOList = new ArrayList<>();
        newsAdapter2 = new NewsAdapter2(getActivity(), rowsDTOList);
        mainNewsLv.setAdapter(newsAdapter2);


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
                //这边加三个清空数组,将前一次保存的数据给清空
                title1.clear();
                content1.clear();
                imgUrl1.clear();
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
                        //传递数据列表给新闻显示界面
                        title1.add(title);
                        content1.add(content);
                        imgUrl1.add(imgUrl);
                        intent = new Intent(getActivity(), NewsActivity.class);
                        //这边要传一个列表数据过去，因为有可能有多个新闻
                        intent.putStringArrayListExtra("title", title1);
                        intent.putStringArrayListExtra("content", content1);
                        intent.putStringArrayListExtra("imgUrl", imgUrl1);
                    }
                }
                startActivity(intent);
            }
        });

    }

    //发送请求新闻列表的网络请求
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

    //
    public void getMainNewsData(String json) {
        MainNewsBean mainNewsBean = new Gson().fromJson(json, MainNewsBean.class);
        List<MainNewsBean.RowsDTO> rowsDTOList1 = mainNewsBean.getRows();

        for (int i = 0; i < rowsDTOList1.size(); i++) {
            String title = rowsDTOList1.get(i).getTitle();
            String content = rowsDTOList1.get(i).getContent();
            String url = "http://dasai.sdvcst.edu.cn:8080" + rowsDTOList1.get(i).getImgUrl();
            String createTime = rowsDTOList1.get(i).getCreateTime();
            String likeNum = rowsDTOList1.get(i).getLikeNumber();
            String category = rowsDTOList1.get(i).getPressCategory();
            String viewNum = rowsDTOList1.get(i).getViewsNumber();

            rowsDTOList.add(new MainNewsBean.RowsDTO(title, content, url, createTime, likeNum, category, viewNum));

        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newsAdapter2.notifyDataSetChanged();

            }
        });

    }

    //发送主页新闻专栏
    public void initMainNewsData() {
        HttpUtil.Get("http://dasai.sdvcst.edu.cn:8080/press/press/list?pageNum=1&pageSize=10&pressCategory=37", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getMainNewsData(responseData);
            }
        });

    }


}