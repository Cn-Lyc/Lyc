package com.example.skilltrain;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.skilltrain.util.GlideImgUtil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StartFragment1 extends Fragment {
    //    String url = ;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate的必须是start_background,不能是activity_start的
        View view = inflater.inflate(R.layout.start_background, container, false);
        //  LinearLayout linearLayout = view.findViewById(R.id.start_bg);
        //linearLayout.setBackground(getResources().getDrawable(R.drawable.fuzimiao));

        imageView = view.findViewById(R.id.img);
//        imageView.setImageResource(R.drawable.fuzimiao);


//        new Thread(new Runnable() {
//
//            @Override
//
//            public void run() {
//
//                Bitmap bmp = getURLimage(" http://dasai.sdvcst.edu.cn:8080/profile/1-yingdao.jpg");
//
//                Message msg = new Message();
//
//                msg.what = 0;
//
//                msg.obj = bmp;
//
//                handle.sendMessage(msg);
//
//            }
//
//        }).start();
        Glide.with(view).load("http://dasai.sdvcst.edu.cn:8080/profile/1-yingdao.jpg").into(imageView);
        return view;

    }
//
//    Handler handle = new Handler() {
//        public void handleMessage(Message msg) {
//
//            switch (msg.what) {
//
//                case 0:
//
//                    Bitmap bmp = (Bitmap) msg.obj;
//
//                    imageView.setImageBitmap(bmp);
//
//                    break;
//
//            }
//
//        }
//
//    };


//    public Bitmap getURLimage(String url) {
//
//        Bitmap bmp = null;
//
//        try {
//
//            URL myurl = new URL(url);
//
//            // 获得连接
//
//            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
//
////            conn.setConnectTimeout(6000);//设置超时
//
////            conn.setDoInput(true);
//
////            conn.setUseCaches(false);//不缓存
//
//            conn.connect();
//
//            InputStream is = conn.getInputStream();//获得图片的数据流
//
//            bmp = BitmapFactory.decodeStream(is);//读取图像数据
//
//            //    is.close();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//        return bmp;
//
//    }

}