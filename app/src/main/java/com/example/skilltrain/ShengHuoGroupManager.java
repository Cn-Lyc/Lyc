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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skilltrain.adapter.AddGroupAdapter;
import com.example.skilltrain.bean.GroupBean;

import java.util.ArrayList;
import java.util.List;

public class ShengHuoGroupManager extends AppCompatActivity {
    TextView addGroupTv, backTv;
    Spinner addSp, categorySp;
    EditText addGroupEt, addDoorEt;
    String group;
    List<GroupBean> groupBeanList;
    AddGroupAdapter addGroupAdapter;
    ListView addLv;

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

                if (addGroupEt.getVisibility() == View.VISIBLE) {
                    if (zidingyi.length() > 0) {
                        group = zidingyi;
                    } else {
                        group = "我家";
                    }
                }
                Toast.makeText(ShengHuoGroupManager.this, "操作成功", Toast.LENGTH_SHORT).show();
                groupBeanList.add(new GroupBean(group, "", ""));
                addGroupAdapter.notifyDataSetChanged();
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

        addLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder addDialog = new AlertDialog.Builder(ShengHuoGroupManager.this);
                final View view1 = LayoutInflater.from(ShengHuoGroupManager.this).inflate(R.layout.addgroup_categorydoor_dialog, null);
                addDialog.setTitle("添加信息");
                addDialog.setView(view1);
                categorySp = view1.findViewById(R.id.addCategorySpinner);
                addDoorEt = view1.findViewById(R.id.addDoorEt);

                addDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String category = String.valueOf(categorySp.getSelectedItem());
                        String door = addDoorEt.getText().toString();
                        String name = groupBeanList.get(position).getGroupName();
                        groupBeanList.set(position, new GroupBean(name, category, door));
                        addGroupAdapter.notifyDataSetChanged();
                    }
                });

                //取消按钮
                addDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                addDialog.show();

            }
        });
    }

    private void initView() {
        addGroupTv = findViewById(R.id.lifeAddGroupTv);
        backTv = findViewById(R.id.lifeAddGroupBackTv);
        addLv = findViewById(R.id.groupLv);
        groupBeanList = new ArrayList<>();
        addGroupAdapter = new AddGroupAdapter(ShengHuoGroupManager.this, groupBeanList);
        addLv.setAdapter(addGroupAdapter);
    }
}