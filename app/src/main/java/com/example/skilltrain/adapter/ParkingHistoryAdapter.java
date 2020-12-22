package com.example.skilltrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skilltrain.R;
import com.example.skilltrain.bean.ParkingHistoryBean;

import java.util.List;

public class ParkingHistoryAdapter extends BaseAdapter {
    Context context;
    List<ParkingHistoryBean.RowsDTO> rowsDTOList;

    public ParkingHistoryAdapter(Context context, List<ParkingHistoryBean.RowsDTO> rowsDTOList) {
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
        view = LayoutInflater.from(context).inflate(R.layout.parkinghistory_item, viewGroup, false);
        TextView pkHiEnter = view.findViewById(R.id.parkingEnterTv);
        TextView pkHiOut = view.findViewById(R.id.parkingOutTv);
        TextView pkHiNum = view.findViewById(R.id.parkingNumTv);
        TextView PKHiMon = view.findViewById(R.id.parkingmoneyTv);
        TextView PkHiName = view.findViewById(R.id.parkingHistoryNameTv);
        pkHiEnter.setText(rowsDTOList.get(i).getEntryTime());
        pkHiOut.setText(rowsDTOList.get(i).getOutTime());
        pkHiNum.setText(rowsDTOList.get(i).getPlateNumber());
        PKHiMon.setText(rowsDTOList.get(i).getMonetary());
        PkHiName.setText(rowsDTOList.get(i).getParkName());

        return view;
    }
}
