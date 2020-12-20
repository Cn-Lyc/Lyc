package com.example.skilltrain.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.skilltrain.AllServiceFragment;
import com.example.skilltrain.HuanBaoFragment;
import com.example.skilltrain.MainActivity;
import com.example.skilltrain.MainFragment;
import com.example.skilltrain.NewsFragment;
import com.example.skilltrain.StartFragment1;
import com.example.skilltrain.StartFragment2;
import com.example.skilltrain.ZhongXinFragment;

public class MainAdapter extends FragmentPagerAdapter {

    final int PAGER_COUNT = 5;
    MainFragment mainFragment = null;
    NewsFragment newsFragment = null;
    AllServiceFragment allServiceFragment = null;
    HuanBaoFragment huanBaoFragment = null;
    ZhongXinFragment zhongXinFragment = null;

    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mainFragment = new MainFragment();
        newsFragment = new NewsFragment();
        allServiceFragment = new AllServiceFragment();
        huanBaoFragment = new HuanBaoFragment();
        zhongXinFragment = new ZhongXinFragment();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = mainFragment;
                break;
            case MainActivity.PAGE_TWO:
                fragment = allServiceFragment;
                break;
            case MainActivity.PAGE_THREE:
                fragment = huanBaoFragment;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = newsFragment;
                break;
            case MainActivity.PAGE_FIVE:
                fragment = zhongXinFragment;
                break;

        }


        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
