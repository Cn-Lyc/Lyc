package com.example.skilltrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GeRenMiMaActivity extends AppCompatActivity {
    TextView backTv;
    EditText phoneEt, nameEt;
    Button xiugaiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_ren_mi_ma);

        initView();

        onClick();


    }


    private void onClick() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeRenMiMaActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        xiugaiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

 

    }

    private void initView() {
        backTv = findViewById(R.id.gerenMiMaBackTv);
        nameEt = findViewById(R.id.mimaUserNameEt);
        phoneEt = findViewById(R.id.mimaPhoneEt);
        xiugaiBtn = findViewById(R.id.mimaBtn);
    }
}