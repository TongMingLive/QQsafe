package com.example.tong.qqsafe.FragmentIndexPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tong.qqsafe.FragmentUtilPackage.UtilModifyPwd;
import com.example.tong.qqsafe.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tong on 2016/12/27.
 */

public class IndexMyPswd extends AppCompatActivity {

    public void back(View v) {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_mypswd);
        ButterKnife.bind(this);
        LinearLayout top = (LinearLayout) findViewById(R.id.top_bar);
        TextView top_title = (TextView) top.findViewById(R.id.top_bar_title);
        top_title.setText("我的密码");

    }

    @OnClick(R.id.reset_psw_btn)
    public void onClick() {
        startActivity(new Intent(IndexMyPswd.this, UtilModifyPwd.class));
    }
}
