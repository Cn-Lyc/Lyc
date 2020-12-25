package com.example.skilltrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skilltrain.R;
import com.example.skilltrain.bean.BusInfoBean;

import java.util.List;

public class BusSpAdapter extends BaseAdapter {
    Context context;
    List<BusInfoBean.RowsDTO> rowsDTOList;

    public BusSpAdapter(Context context, List<BusInfoBean.RowsDTO> rowsDTOList) {
        this.context = context;
        this.rowsDTOList = rowsDTOList;

    }

    @Override
    public int getCount() {
        return rowsDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return rowsDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.bussp_item, viewGroup, false);
        TextView busRoadName = view.findViewById(R.id.busSpRoadNameTv);
        TextView busStart = view.findViewById(R.id.busSpStartTv);
        TextView busEnd = view.findViewById(R.id.busSpEndTv);
        TextView busTime = view.findViewById(R.id.busSpTimeTv);
        TextView busMile = view.findViewById(R.id.busSpMileTv);
        TextView busMoney = view.findViewById(R.id.busSpMoneyTv);

        busRoadName.setText(rowsDTOList.get(i).getName());
        busStart.setText(rowsDTOList.get(i).getFirst());
        busEnd.setText(rowsDTOList.get(i).getEnd());
        busTime.setText(rowsDTOList.get(i).getStartTime());
        busMile.setText(rowsDTOList.get(i).getMileage());
        busMoney.setText(rowsDTOList.get(i).getPrice());

        return view;


    }
}
