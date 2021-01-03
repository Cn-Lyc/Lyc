package com.example.skilltrain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ShengHuoGroupManager extends AppCompatActivity {
    TextView addGroupTv, backTv;
    Spinner addSp;
    EditText addGroupEt;
    String group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheng_huo_group_manager);


        initView();

        onClick();

    }

    public void resignDialog() {
        AlertDialog.Builder resignDialog = new AlertDialog.Builder(ShengHuoGroupManager.this);
        final View dialogView = LayoutInflater.from(ShengHuoGroupManager.this).inflate(R.layout.addgroup_dialog, null);
        resignDialog.setTitle("增加分组");
        resignDialog.setView(dialogView);

        addSp = dialogView.findViewById(R.id.addSpinner);
        addGroupEt = dialogView.findViewById(R.id.addGroupEt);

        addSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        group = "我家";
                        addGroupEt.setVisibility(View.GONE);
                        break;
                    case 1:
                        group = "父母";
                        addGroupEt.setVisibility(View.GONE);
                        break;
                    case 2:
                        group = "房东";
                        addGroupEt.setVisibility(View.GONE);
                        break;
                    case 3:
                        group = "朋友";
                        addGroupEt.setVisibility(View.GONE);
                        break;
                    case 4:
                        addGroupEt.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        resignDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //要在这里获取zidingyi
                String zidingyi = addGroupEt.getText().toString();

                if (addGroupEt.getVisibility()==View.VISIBLE){
                    if (zidingyi.length() > 0) {
                        group = zidingyi;
                    } else {
                        group = "我家";
                    }
                }
                Toast.makeText(ShengHuoGroupManager.this, group, Toast.LENGTH_SHORT).show();
            }
        });

        //取消按钮
        resignDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        resignDialog.show();

    }


    private void onClick() {
        addGroupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resignDialog();
            }
        });

        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShengHuoGroupManager.this, ShengHuoJiaoFeiActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void initView() {
        addGroupTv = findViewById(R.id.lifeAddGroupTv);
        backTv = findViewById(R.id.lifeAddGroupBackTv);
    }
}