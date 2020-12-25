package com.example.skilltrain.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.skilltrain.AllServiceFragment;
import com.example.skilltrain.HuanBaoFragment;
import com.example.skilltrain.MainActivity;
import com.example.skilltrain.MainFragment;
import com.example.skilltrain.NewsFragment;
import com.example.skilltrain.R;
import com.example.skilltrain.ZhongXinFragment;

import java.util.ArrayList;
import java.util.List;

public class ZhongXinLoFragment extends Fragment {
    Button loginBtn;
    MainAdapter mainAdapter;
    ViewPager viewPager;


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

    }

    private void onClick() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   chargeFragment(new HuanBaoFragment());
//                MainActivity.fragmentList.clear();
//
//                MainActivity.fragmentList.add(new MainFragment());
//                MainActivity.fragmentList.add(new AllServiceFragment());
//                MainActivity.fragmentList.add(new HuanBaoFragment());
//                MainActivity.fragmentList.add(new NewsFragment());
//                MainActivity.fragmentList.add(new HuanBaoFragment());
//
//                viewPager.setAdapter(mainAdapter);
//                mainAdapter.notifyDataSetChanged();

            }
        });
    }


    public void chargeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.zhongxin_l1, fragment);
        fragmentTransaction.commit();
    }


    private void initView() {
        loginBtn = getActivity().findViewById(R.id.login_btn);

        mainAdapter = new MainAdapter(getActivity().getSupportFragmentManager(), MainActivity.fragmentList);
        viewPager = getActivity().findViewById(R.id.main_vp);
    }

}