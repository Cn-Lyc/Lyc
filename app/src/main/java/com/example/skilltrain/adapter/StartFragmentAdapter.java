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


//    final int PAGE_COUNT = 5;
//    StartFragment1 startFragment1 = null;
//    StartFragment2 startFragment2 = null;
//    StartFragment3 startFragment3 = null;
//    StartFragment4 startFragment4 = null;
//    StartFragment5 startFragment5 = null;
//
//
//    public StartFragmentAdapter(FragmentManager fragmentManager) {
//        super(fragmentManager);
//        startFragment1 = new StartFragment1();
//        startFragment2 = new StartFragment2();
//        startFragment3 = new StartFragment3();
//        startFragment4 = new StartFragment4();
//        startFragment5 = new StartFragment5();
//
//    }
//
//
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        Fragment fragment = null;
//        switch (position) {
//            case StartActivity.PAGE_ONE:
//                fragment = new StartFragment1();
//                break;
//            case StartActivity.PAGE_TWO:
//                fragment = new StartFragment2();
//                break;
//            case StartActivity.PAGE_THREE:
//                fragment = new StartFragment3();
//                break;
//            case StartActivity.PAGE_FOUR:
//                fragment = new StartFragment4();
//                break;
//            case StartActivity.PAGE_FIVE:
//                fragment = new StartFragment5();
//                break;
//        }
//
//        return fragment;
//    }
//
//
//    @Override
//    public int getCount() {
//        return PAGE_COUNT;
//    }


}
