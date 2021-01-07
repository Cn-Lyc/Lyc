package com.example.skilltrain.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MainAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    FragmentManager fragmentManager;
    /**
     * 当数据发生改变时的回调接口
     */
    private OnReloadListener mListener;

//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//
//    }


    public MainAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.fragmentList = fragmentList;
        this.fragmentManager = fragmentManager;
        notifyDataSetChanged();
    }

    public void setPagerItems(List<Fragment> items) {
        if (items != null) {
            for (int i = 0; i < fragmentList.size(); i++) {
                fragmentManager.beginTransaction().remove(fragmentList.get(i)).commit();
            }
            fragmentList = items;
        }

    }


    public interface OnReloadListener{
        public void onReload();
    }


    public void setOnReloadListener(OnReloadListener listener){
        this.mListener=listener;
    }


    public void reLoad(){
        if (mListener!=null){
            mListener.onReload();
        }
        this.notifyDataSetChanged();
    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
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

    @Override
    public long getItemId(int position) {
        return fragmentList.get(position).hashCode();
    }

}
