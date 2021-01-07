package com.example.skilltrain;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.skilltrain.adapter.MainAdapter;

public class ZhongXinLoFragment2 extends Fragment implements View.OnClickListener {
    TextView quitTv, infoTv, dingdanTv, mimaTv, fankuiTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_zhong_xin_lo_fragment2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        initView();

    }


    private void initView() {
        infoTv = getActivity().findViewById(R.id.gerenInfo);
        dingdanTv = getActivity().findViewById(R.id.gerenDingdan);
        mimaTv = getActivity().findViewById(R.id.gerenPassword);
        fankuiTv = getActivity().findViewById(R.id.gerenFanKui);
        quitTv = getActivity().findViewById(R.id.zhongxinQuitTv);

        infoTv.setOnClickListener(this);
        dingdanTv.setOnClickListener(this);
        mimaTv.setOnClickListener(this);
        fankuiTv.setOnClickListener(this);
        quitTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhongxinQuitTv:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("tuichu", 4);
                startActivity(intent);
                getActivity().finish();
                break;

            case R.id.gerenInfo:

                break;

            case R.id.gerenDingdan:

                break;

            case R.id.gerenPassword:
                Intent intent2 = new Intent(getActivity(), GeRenMiMaActivity.class);
                startActivity(intent2);
                getActivity().finish();
                break;

            case R.id.gerenFanKui:
                Intent intent1 = new Intent(getActivity(), GeRenFanKuiActivity.class);
                startActivity(intent1);
                getActivity().finish();
                break;

        }
    }
}