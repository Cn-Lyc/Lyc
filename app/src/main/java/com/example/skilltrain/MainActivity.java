package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.skilltrain.adapter.MainAdapter;
import com.example.skilltrain.adapter.TuBiaoAdapter;
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
import java.util.regex.Matcher;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    ViewPager viewPager;
    RadioGroup mainRg;
    RadioButton shouyeRb, quanbuRb, huanbaoRb, xinwenRb, zhongxinRb;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;
    MainAdapter mainAdapter;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //这句话总是忘写
        fragmentList = new ArrayList<>();
        fragmentList.add(new MainFragment());
        fragmentList.add(new AllServiceFragment());
        fragmentList.add(new HuanBaoFragment());
        fragmentList.add(new NewsFragment());
        fragmentList.add(new ZhongXinFragment());
        mainAdapter = new MainAdapter(getSupportFragmentManager(), fragmentList);
        initView();
        shouyeRb.setChecked(true);
    }

    private void initView() {
        viewPager = findViewById(R.id.main_vp);
        shouyeRb = findViewById(R.id.shouye_rb);
        quanbuRb = findViewById(R.id.quanbu_rb);
        huanbaoRb = findViewById(R.id.huanbao_rb);
        xinwenRb = findViewById(R.id.xinwen_rb);
        zhongxinRb = findViewById(R.id.zhongxin_rb);
        mainRg = findViewById(R.id.main_radiogroup);
        mainRg.setOnCheckedChangeListener(this);

        viewPager.setAdapter(mainAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);

    }

    //viewpage的滑动事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
            switch (viewPager.getCurrentItem()) {
                case 0:
                    shouyeRb.setChecked(true);
                    break;
                case 1:
                    quanbuRb.setChecked(true);
                    break;
                case 2:
                    huanbaoRb.setChecked(true);
                    break;
                case 3:
                    xinwenRb.setChecked(true);
                    break;
                case 4:
                    zhongxinRb.setChecked(true);
                    break;

            }


        }
    }


    //RadioGroup的选择事件
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.shouye_rb:
                viewPager.setCurrentItem(0);
                break;
            case R.id.quanbu_rb:
                viewPager.setCurrentItem(1);
                break;
            case R.id.huanbao_rb:
                viewPager.setCurrentItem(2);
                break;
            case R.id.xinwen_rb:
                viewPager.setCurrentItem(3);
                break;
            case R.id.zhongxin_rb:
                viewPager.setCurrentItem(4);
                break;
        }
    }
}