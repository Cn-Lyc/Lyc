package com.example.skilltrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skilltrain.R;
import com.example.skilltrain.bean.WaterBean;

import java.util.List;

public class WaterAdapter extends BaseAdapter {
    Context context;
    List<WaterBean.RowsDTO> rowsDTOList;

    public WaterAdapter(Context context, List<WaterBean.RowsDTO> rowsDTOList) {
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
        view = LayoutInflater.from(context).inflate(R.layout.water_item, null);
        TextView waterName = view.findViewById(R.id.waterNameTv);
        TextView waterDoor = view.findViewById(R.id.waterDoorTv);
        waterName.setText(rowsDTOList.get(i).getOwnerName());
        waterDoor.setText(rowsDTOList.get(i).getDoorNo());
        return view;
    }
}
