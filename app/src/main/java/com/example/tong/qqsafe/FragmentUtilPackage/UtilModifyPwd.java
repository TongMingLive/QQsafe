package com.example.tong.qqsafe.FragmentUtilPackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.qqsafe.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tong on 2016/12/22.
 */

public class UtilModifyPwd extends AppCompatActivity {
    @Bind(R.id.top_bar_title)
    TextView topBarTitle;
    @Bind(R.id.util_password_et)
    EditText utilPasswordEt;

    public void back(View v) {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_modify_password);
        ButterKnife.bind(this);
        topBarTitle.setText("修改QQ密码");
    }

    @OnClick(R.id.util_password_btn)
    public void onClick(View v) {
        String text = utilPasswordEt.getText().toString();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
        } else if (text.length() <= 6) {
            Toast.makeText(this, "密码长度过短", Toast.LENGTH_SHORT).show();
        } else if (text.trim().matches("[\\u4E00-\\u9FA5]")) {
            Toast.makeText(this, "密码中不能包含中文", Toast.LENGTH_SHORT).show();
        } else {
            finish();
            Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
        }
    }
}
