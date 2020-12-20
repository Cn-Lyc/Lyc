package com.example.skilltrain.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skilltrain.R;
import com.example.skilltrain.bean.AllTuBiaoBean;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AllTuBiaoAdapter extends BaseAdapter {
    Context context;
    List<AllTuBiaoBean.RowsDTO> rowsDTOList;


    public AllTuBiaoAdapter(Context context, List<AllTuBiaoBean.RowsDTO> rowsDTOList) {
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


        view = LayoutInflater.from(context).inflate(R.layout.tubiao_item, viewGroup, false);

        ImageView ivPic = view.findViewById(R.id.tubiao_img);
        TextView textView = view.findViewById(R.id.tubiao_name);

        String pic_url = "http://dasai.sdvcst.edu.cn:8080" + rowsDTOList.get(i).getImgUrl();
        setPicBitmap(ivPic, pic_url);
        textView.setText(rowsDTOList.get(i).getServiceName());

        return view;
    }


    public static void setPicBitmap(final ImageView ivPic, final String pic_url) {
        //设置图片需要访问网络，因此不能在主线程中设置
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(pic_url).openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    ivPic.setImageBitmap(bitmap);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
