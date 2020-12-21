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
import com.example.skilltrain.bean.ZhaunTiNewsBean;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class NewsAdapter2 extends BaseAdapter {
    List<ZhaunTiNewsBean.RowsDTO> rowsDTOList;
    Context context;


    public NewsAdapter2(Context context, List<ZhaunTiNewsBean.RowsDTO> rowsDTOList) {
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
        view = LayoutInflater.from(context).inflate(R.layout.news_item2,null);
        TextView titleTv = view.findViewById(R.id.news_title2);
        TextView contentTv = view.findViewById(R.id.news_content2);
        ImageView ivPic = view.findViewById(R.id.news_img2);
        ZhaunTiNewsBean.RowsDTO rowsDTO = rowsDTOList.get(i);
        titleTv.setText(rowsDTO.getTitle());
        contentTv.setText(rowsDTO.getContent());
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
