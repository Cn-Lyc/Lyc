package com.example.skilltrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skilltrain.R;
import com.example.skilltrain.bean.TuBiaoBean;

import java.util.List;

public class TuBiaoAdapter extends BaseAdapter {

    /**
     * 这次用了优化版的BaseAdapter，具体说明可以去看菜鸟教程
     * https://www.runoob.com/w3cnote/android-tutorial-baseadapter.html
     */

    List<TuBiaoBean> tuBiaoBeanList;
    Context context;


    public TuBiaoAdapter(Context context, List<TuBiaoBean> tuBiaoBeanList) {
        this.context = context;
        this.tuBiaoBeanList = tuBiaoBeanList;
    }


    @Override
    public int getCount() {
        return tuBiaoBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return tuBiaoBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.tubiao_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.tubiao_img);
            viewHolder.textView = view.findViewById(R.id.tubiao_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(tuBiaoBeanList.get(i).getName());
        viewHolder.imageView.setImageResource(tuBiaoBeanList.get(i).getImg());

        return view;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;

    }

}
