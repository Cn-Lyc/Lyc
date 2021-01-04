package com.example.skilltrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skilltrain.R;
import com.example.skilltrain.bean.PowerBean;
import com.example.skilltrain.bean.WaterBean;

import java.util.List;

public class PowerAdapter extends BaseAdapter {
    Context context;
    List<PowerBean.RowsDTO> rowsDTOList;

    public PowerAdapter(Context context, List<PowerBean.RowsDTO> rowsDTOList) {
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
        view = LayoutInflater.from(context).inflate(R.layout.power_item, null);
        TextView powerName = view.findViewById(R.id.powerNameTv);
        TextView powerDoor = view.findViewById(R.id.powerDoorTv);
        TextView powerDoorName = view.findViewById(R.id.powerDoorNameTv);
        TextView powerBalance = view.findViewById(R.id.powerBalanceTv);
        TextView powerCost = view.findViewById(R.id.powerCostTv);


        powerName.setText(rowsDTOList.get(i).getChargeUnit());
        powerDoor.setText(rowsDTOList.get(i).getDoorNo());
        powerDoorName.setText(rowsDTOList.get(i).getOwnerName());
        powerBalance.setText(rowsDTOList.get(i).getBalance());
        powerCost.setText(rowsDTOList.get(i).getElectricityMoney());
        return view;
    }
}
