package com.example.skilltrain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.skilltrain.adapter.MainAdapter;

public class ZhongXinLoFragment extends Fragment {
    Button loginBtn;
    MainAdapter mainAdapter;
    ViewPager viewPager;
    LinearLayout l1, lMain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_zhong_xin_lo_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        initView();

        onClick();

        Log.d("中心", "onStart: ");
    }

    private void onClick() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);

                intent.putExtra("zhongxin", 4);

                startActivity(intent);

                getActivity().finish();
            }
        });
    }


    private void initView() {
        l1 = getActivity().findViewById(R.id.zhongxinL1);
        lMain = getActivity().findViewById(R.id.zhongxinLMain);
        loginBtn = getActivity().findViewById(R.id.login_btn);
        mainAdapter = new MainAdapter(getActivity().getSupportFragmentManager(), MainActivity.fragmentList);
        viewPager = getActivity().findViewById(R.id.main_vp);
    }

}