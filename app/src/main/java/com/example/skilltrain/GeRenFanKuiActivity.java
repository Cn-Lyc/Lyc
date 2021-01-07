package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.skilltrain.util.HttpUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GeRenFanKuiActivity extends AppCompatActivity {
    TextView backTv;
    EditText fanKuiEt;
    Button fanKuiBtn;
    String fankui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_ren_fan_kui);

        initView();

        onClick();
    }

    private void onClick() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeRenFanKuiActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        fanKuiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fankui = fanKuiEt.getText().toString();
                fanKuiDataPost();
            }
        });


    }

    private void initView() {
        backTv = findViewById(R.id.gerenFanKuiBackTv);
        fanKuiEt = findViewById(R.id.fanKuiEt);
        fanKuiBtn = findViewById(R.id.fanKuiBtn);
    }


    public void fanKuiDataPost() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content", fankui);
            jsonObject.put("userId", "0118");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpUtil.PostHeader(" http://dasai.sdvcst.edu.cn:8080/userinfo/feedback", jsonObject, MainFragment.token, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                getFanKui(responseData);
            }
        });
    }

    public void getFanKui(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String msg = jsonObject.getString("msg");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(GeRenFanKuiActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}