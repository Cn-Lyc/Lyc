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
import com.example.skilltrain.bean.MainNewsBean;
import com.example.skilltrain.bean.ZhaunTiNewsBean;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class NewsAdapter2 extends BaseAdapter {
    List<MainNewsBean.RowsDTO> rowsDTOList;
    Context context;


    public NewsAdapter2(Context context, List<MainNewsBean.RowsDTO> rowsDTOList) {
        this.rowsDTOList = rowsDTOList;
        this.context = context;
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
        view = LayoutInflater.from(context).inflate(R.layout.news_item2, null);
        TextView titleTv = view.findViewById(R.id.news_title2);
        TextView contentTv = view.findViewById(R.id.news_content2);
        TextView categoryTv = view.findViewById(R.id.news_category2);
        TextView likeTv = view.findViewById(R.id.news_likenumber2);
        TextView viewTv = view.findViewById(R.id.news_viewnumber2);
        TextView timeTv = view.findViewById(R.id.news_createtime2);
        ImageView ivPic = view.findViewById(R.id.news_img2);
        MainNewsBean.RowsDTO rowsDTO = rowsDTOList.get(i);
        titleTv.setText(rowsDTO.getTitle());
        contentTv.setText(rowsDTO.getContent());
        categoryTv.setText(rowsDTO.getPressCategory());
        likeTv.setText(rowsDTO.getLikeNumber());
        viewTv.setText(rowsDTO.getViewsNumber());
        timeTv.setText(rowsDTO.getCreateTime());
        String pic_url = rowsDTO.getImgUrl();
        setPicBitmap(ivPic, pic_url);
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
