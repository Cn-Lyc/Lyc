package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShengHuoJiaoFeiActivity extends AppCompatActivity {
    TextView backTv, cityTv, waterTv, powerTv, groupTv;
    String latLongString;
    Button groupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheng_huo_jiao_fei);

        initView();

        onClick();


        /* 应该是写出来了，但是手机有问题 出不来结果 */
        // GPSSetting();
    }


    private void onClick() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShengHuoJiaoFeiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        groupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShengHuoJiaoFeiActivity.this, ShengHuoGroupManager.class);
                startActivity(intent);
                finish();
            }
        });

        waterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShengHuoJiaoFeiActivity.this, ShengHuoWaterManActivity.class);
                startActivity(intent);
                finish();
            }
        });

        groupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShengHuoJiaoFeiActivity.this, ShengHuoGroupManager.class);
                startActivity(intent);
                finish();
            }
        });

        powerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShengHuoJiaoFeiActivity.this, ShengHuoPowerActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initView() {
        backTv = findViewById(R.id.lifeBackTv);
        cityTv = findViewById(R.id.lifeCityTv);
        waterTv = findViewById(R.id.waterManTv);
        powerTv = findViewById(R.id.powerManTv);
        groupTv = findViewById(R.id.groupManTv);
        groupBtn = findViewById(R.id.lifeGroupBt);
    }


    public void GPSSetting() {
        LocationManager lm = (LocationManager) ShengHuoJiaoFeiActivity.this.getSystemService(Context.LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(ShengHuoJiaoFeiActivity.this, "GPS模块正常", Toast.LENGTH_SHORT).show();
            getLocation();
            return;
        } else {
            Toast.makeText(ShengHuoJiaoFeiActivity.this, "GPS不模块正常", Toast.LENGTH_SHORT).show();
        }
    }

    private void getLocation() {
        // 获取位置管理服务
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) ShengHuoJiaoFeiActivity.this.getSystemService(serviceName);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗

        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(provider); // 通过GPS获取位置
        updateToNewLocation(location);
    }

    private void updateToNewLocation(Location location) {

        TextView tv1;
//        tv1 = (TextView) this.findViewById(R.id.weiduTv);
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            List<Address> addList = null;
            Geocoder ge = new Geocoder(this);

            try {
                addList = ge.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }


            Log.d("TAGaddress", "updateToNewLocation: " + addList);


            if (addList != null && addList.size() > 0) {

                for (int i = 0; i < addList.size(); i++) {

                    Address ad = addList.get(i);

                    latLongString += "n";

                    latLongString += ad.getCountryName() + ";" + ad.getLocality();

                }
                cityTv.setText(latLongString);
            }

//            tv1.setText("纬度:" + latitude + "\n经度:" + longitude);
        } else {
//            tv1.setText("无法获取地理信息");
        }

    }


}