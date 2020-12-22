package com.example.skilltrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skilltrain.R;
import com.example.skilltrain.bean.BaseParkingBean;

import java.util.List;

public class BaseParkingAdapter extends BaseAdapter {
    Context context;
    List<BaseParkingBean.RowsDTO> rowsDTOList;


    public BaseParkingAdapter(Context context, List<BaseParkingBean.RowsDTO> rowsDTOList) {
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
        view = LayoutInflater.from(context).inflate(R.layout.baseinfo_item, viewGroup, false);
        TextView pkName = view.findViewById(R.id.parkingNameTv);
        TextView pkVan = view.findViewById(R.id.parkingVanTv);
        TextView pkAddress = view.findViewById(R.id.parkingAddressTv);
        TextView pkRates = view.findViewById(R.id.parkingRatesTv);
        TextView pkDistance = view.findViewById(R.id.parkingDistanceTv);

        pkName.setText(rowsDTOList.get(i).getParkName());
        pkVan.setText(rowsDTOList.get(i).getVacancy());
        pkAddress.setText(rowsDTOList.get(i).getAddress());
        pkRates.setText(rowsDTOList.get(i).getRates());
        pkDistance.setText(rowsDTOList.get(i).getDistance());


        return view;
    }
}
