package com.example.skilltrain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skilltrain.adapter.StartFragmentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    TextView huadong1, huadong2, huadong3, huadong4, huadong5;
    Button joinBtn, interBtn;
    EditText ip_et;
    //IP的正则表达式,自学一下，还是比较好理解的
    String reg = "((25[0-5]|2[0-4]\\d|[0-1]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[0-1]?\\d\\d?)\\b";

    //几个代表页面的常量

    StartFragmentAdapter startFragmentAdapter;
    ViewPager viewPager;
    //判断是否第一次进入的布尔型变量
    Boolean check = true;
    List<Fragment> fragmentList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_fragment);

        //从SP中取数据
        check = getSharedPreferences("myinfo", 0).getBoolean("check", true);

        fragmentList = new ArrayList<>();
        fragmentList.add(new StartFragment1());
        fragmentList.add(new StartFragment2());
        fragmentList.add(new StartFragment3());
        fragmentList.add(new StartFragment4());
        fragmentList.add(new StartFragment5());
        startFragmentAdapter = new StartFragmentAdapter(getSupportFragmentManager(), fragmentList);


        initView();

        checkFirst();

    }

    private void checkFirst() {
        if (check) {
            SharedPreferences.Editor editor = getSharedPreferences("myinfo", 0).edit();
            editor.putBoolean("check", false);
            editor.commit();
        } else {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }



    private void initView() {
        huadong1 = findViewById(R.id.start_huadong1);
        huadong2 = findViewById(R.id.start_huadong2);
        huadong3 = findViewById(R.id.start_huadong3);
        huadong4 = findViewById(R.id.start_huadong4);
        huadong5 = findViewById(R.id.start_huadong5);

        joinBtn = findViewById(R.id.start_join);
        interBtn = findViewById(R.id.start_internet);
        joinBtn.setOnClickListener(this);
        interBtn.setOnClickListener(this);


        ip_et = findViewById(R.id.ip_et);
//       ip_et.addTextChangedListener(this);

        //viewpager的使用
        viewPager = findViewById(R.id.start_vp1);


        viewPager.setAdapter(startFragmentAdapter);

        /* 这个setcurrentitem这里用不到*/
        //   viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        joinBtn.setVisibility(View.GONE);
                        interBtn.setVisibility(View.GONE);
                        huadong1.setBackground(getDrawable(R.drawable.start_bg2));
                        huadong2.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong3.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong4.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong5.setBackground(getDrawable(R.drawable.start_bg1));
                        break;

                    case 1:
                        joinBtn.setVisibility(View.GONE);
                        interBtn.setVisibility(View.GONE);
                        huadong1.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong2.setBackground(getDrawable(R.drawable.start_bg2));
                        huadong3.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong4.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong5.setBackground(getDrawable(R.drawable.start_bg1));


                        break;
                    case 2:
                        joinBtn.setVisibility(View.GONE);
                        interBtn.setVisibility(View.GONE);
                        huadong1.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong2.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong3.setBackground(getDrawable(R.drawable.start_bg2));
                        huadong4.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong5.setBackground(getDrawable(R.drawable.start_bg1));
                        break;
                    case 3:
                        joinBtn.setVisibility(View.GONE);
                        interBtn.setVisibility(View.GONE);
                        huadong1.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong2.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong3.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong4.setBackground(getDrawable(R.drawable.start_bg2));
                        huadong5.setBackground(getDrawable(R.drawable.start_bg1));
                        break;
                    case 4:
                        joinBtn.setVisibility(View.VISIBLE);
                        interBtn.setVisibility(View.VISIBLE);
                        huadong1.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong2.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong3.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong4.setBackground(getDrawable(R.drawable.start_bg1));
                        huadong5.setBackground(getDrawable(R.drawable.start_bg2));
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    //自定义对话框
    public void resignDialog() {
        AlertDialog.Builder resignDialog = new AlertDialog.Builder(StartActivity.this);
        //对话框的布局来自__
        final View dialogView = LayoutInflater.from(StartActivity.this).inflate(R.layout.activity_internect, null);
        //设置标题
        resignDialog.setTitle("网络设置");
        //设置对话框的界面是上面获取到的view
        resignDialog.setView(dialogView);

        //保存按钮点击事件
        resignDialog.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //获取到输入框输入的内容，必须要在这里面用View来进行绑定
                ip_et = dialogView.findViewById(R.id.ip_et);

                String st1 = ip_et.getText().toString();

                //正则的使用
                Pattern patter = Pattern.compile(reg);
                //.matcher("需要去匹配bai该正则的string字符du串");
                Matcher matcher = patter.matcher(st1);
                //是否为真,matches取到的是布尔型
                boolean check = matcher.matches();
                if (check) {
                    Toast.makeText(StartActivity.this, "当前输入的ip是:" + st1, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StartActivity.this, "当前输入的IP有误，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //取消按钮
        resignDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        resignDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_join:
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.start_internet:
                resignDialog();
                break;

        }
    }
}