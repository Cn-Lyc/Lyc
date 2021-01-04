package com.example.skilltrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skilltrain.R;
import com.example.skilltrain.bean.GroupBean;

import java.util.List;

public class AddGroupAdapter extends BaseAdapter {
    Context context;
    List<GroupBean> groupBeanList;

    public AddGroupAdapter(Context context, List<GroupBean> groupBeanList) {
        this.context = context;
        this.groupBeanList = groupBeanList;
    }


    @Override
    public int getCount() {
        return groupBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return groupBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.addgroup_item, viewGroup, false);
        TextView groupName = view.findViewById(R.id.groupNameTv);
        TextView groupCategory = view.findViewById(R.id.groupCategoryTv);
        TextView groupDoor = view.findViewById(R.id.groupDoorTv);
        groupName.setText(groupBeanList.get(i).getGroupName());
        groupCategory.setText(groupBeanList.get(i).getGroupCategory());
        groupDoor.setText(groupBeanList.get(i).getGroupDoor());

        return view;
    }
}
