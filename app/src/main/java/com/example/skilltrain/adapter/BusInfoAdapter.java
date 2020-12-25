package com.example.skilltrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.skilltrain.R;
import com.example.skilltrain.bean.BusInfoBean;
import com.example.skilltrain.bean.BusSubBean;

import java.util.List;

public class BusInfoAdapter extends BaseExpandableListAdapter {
    List<BusInfoBean.RowsDTO> busInfoList;
    List<List<BusSubBean.RowsDTO>> busSubList;
    Context context;

    public BusInfoAdapter(Context context, List<BusInfoBean.RowsDTO> busInfoList, List<List<BusSubBean.RowsDTO>> busSubList) {
        this.context = context;
        this.busInfoList = busInfoList;
        this.busSubList = busSubList;
    }

    //返回第一级List长度
    @Override
    public int getGroupCount() {
        return busInfoList.size();
    }

    //返回指定i的第二级List长度
    @Override
    public int getChildrenCount(int i) {
        return busSubList.get(i).size();
    }

    //返回一级List的内容
    @Override
    public Object getGroup(int i) {
        return busInfoList.get(i);
    }

    //返回二级List的内容
    @Override
    public Object getChild(int i, int i1) {
        return busSubList.get(i).get(i1);
    }

    //返回一级View的id 保证id唯一
    @Override
    public long getGroupId(int i) {
        return i;
    }

    //返回二级View的id 保证id唯一
    @Override
    public long getChildId(int i, int i1) {
        return i + i1;
    }

    /**
     * 指示在对基础数据进行更改时子ID和组ID是否稳定
     *
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.bus_item, viewGroup, false);
        TextView busRoadName = view.findViewById(R.id.busRoadNameTv);
        TextView busStart = view.findViewById(R.id.busStartTv);
        TextView busEnd = view.findViewById(R.id.busEndTv);
        TextView busTime = view.findViewById(R.id.busTimeTv);
        TextView busMile = view.findViewById(R.id.busMileTv);
        TextView busMoney = view.findViewById(R.id.busMoneyTv);

        busRoadName.setText(busInfoList.get(i).getName());
        busStart.setText(busInfoList.get(i).getFirst());
        busEnd.setText(busInfoList.get(i).getEnd());
        busTime.setText(busInfoList.get(i).getStartTime());
        busMile.setText(busInfoList.get(i).getMileage());
        busMoney.setText(busInfoList.get(i).getPrice());

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.bus_sub_item, viewGroup, false);
        TextView busStop = view.findViewById(R.id.busSubStopTv);

        busStop.setText(busSubList.get(i).get(i1).getName());

        return view;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
