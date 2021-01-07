package com.example.skilltrain;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.skilltrain.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    public static ViewPager viewPager;
    RadioGroup mainRg;
    RadioButton shouyeRb, quanbuRb, huanbaoRb, xinwenRb, zhongxinRb;
    public static Context context;
    public static MainAdapter mainAdapter;
    public static List<Fragment> fragmentList;
    int zhongxin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        zhongxin = intent.getIntExtra("zhongxin", 4);
        Log.d("中心跳转值", "onCreate: "+zhongxin);

        fragmentList = new ArrayList<>();
        fragmentList.add(new MainFragment());
        fragmentList.add(new AllServiceFragment());
        fragmentList.add(new HuanBaoFragment());
        fragmentList.add(new NewsFragment());
        fragmentList.add(new ZhongXinLoFragment2());


        //这句话总是忘写
        mainAdapter = new MainAdapter(getSupportFragmentManager(), fragmentList);

       /*mainAdapter.setOnReloadListener(new MainAdapter.OnReloadListener() {
            @Override
            public void onReload() {
                fragmentList = null;
                List<Fragment> list = new ArrayList<Fragment>();
                list.add(new MainFragment());
                list.add(new AllServiceFragment());
                list.add(new HuanBaoFragment());
                list.add(new NewsFragment());
                list.add(new ZhongXinLoFragment());
                mainAdapter.setPagerItems(list);
                viewPager.setAdapter(mainAdapter);
            }
        });*/

        initView();
        shouyeRb.setChecked(true);
        shouyeRb.setBackgroundColor(Color.parseColor("#58B39D"));
        viewPager.setOffscreenPageLimit(fragmentList.size());
        viewPager.setCurrentItem(zhongxin);
        Log.d("测试", "onCreate");
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
        switch (position) {
            case 0:
                shouyeRb.setBackgroundColor(Color.parseColor("#58B39D"));
                quanbuRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                huanbaoRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                xinwenRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                zhongxinRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                break;
            case 1:
                shouyeRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                quanbuRb.setBackgroundColor(Color.parseColor("#58B39D"));
                huanbaoRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                xinwenRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                zhongxinRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                break;
            case 2:
                shouyeRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                quanbuRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                huanbaoRb.setBackgroundColor(Color.parseColor("#58B39D"));
                xinwenRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                zhongxinRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                break;

            case 3:
                shouyeRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                quanbuRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                huanbaoRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                xinwenRb.setBackgroundColor(Color.parseColor("#58B39D"));
                zhongxinRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                break;

            case 4:
                shouyeRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                quanbuRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                huanbaoRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                xinwenRb.setBackgroundColor(Color.parseColor("#d3d3d3"));
                zhongxinRb.setBackgroundColor(Color.parseColor("#58B39D"));
                break;
        }
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