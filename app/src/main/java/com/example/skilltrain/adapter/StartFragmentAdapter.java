package com.example.skilltrain.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.skilltrain.StartActivity;
import com.example.skilltrain.StartFragment1;
import com.example.skilltrain.StartFragment2;
import com.example.skilltrain.StartFragment3;
import com.example.skilltrain.StartFragment4;
import com.example.skilltrain.StartFragment5;

import java.util.List;

public class StartFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;

    public StartFragmentAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }



}
