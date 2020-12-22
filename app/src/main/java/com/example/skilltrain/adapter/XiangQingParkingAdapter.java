package com.example.skilltrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skilltrain.R;
import com.example.skilltrain.bean.BaseParkingBean;
import com.example.skilltrain.bean.XiangQingParkingBean;

import java.util.List;

public class XiangQingParkingAdapter extends BaseAdapter {
    Context context;
    List<XiangQingParkingBean.RowsDTO> rowsDTOList;


    public XiangQingParkingAdapter(Context context, List<XiangQingParkingBean.RowsDTO> rowsDTOList) {
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
        view = LayoutInflater.from(context).inflate(R.layout.xiangqinginfo_item, viewGroup, false);
        TextView pkName = view.findViewById(R.id.XqparkingNameTv);
        TextView pkVan = view.findViewById(R.id.XqparkingVanTv);
        TextView pkAddress = view.findViewById(R.id.XqparkingAddressTv);
        TextView pkRates = view.findViewById(R.id.XqparkingRatesTv);
        TextView pkDistance = view.findViewById(R.id.XqparkingDistanceTv);
        TextView pkMoney = view.findViewById(R.id.XqparkingMoneyTv);
        TextView pkAll = view.findViewById(R.id.XqparkingAllTv);

        pkName.setText(rowsDTOList.get(i).getParkName());
        pkVan.setText(rowsDTOList.get(i).getVacancy());
        pkAddress.setText(rowsDTOList.get(i).getAddress());
        pkRates.setText(rowsDTOList.get(i).getRates());
        pkDistance.setText(rowsDTOList.get(i).getDistance());
        pkMoney.setText(rowsDTOList.get(i).getPriceCaps());
        pkAll.setText(rowsDTOList.get(i).getAllPark());

        return view;
    }
}
