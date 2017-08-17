package com.example.tong.qqsafe.LeftNav;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.qqsafe.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tong on 2016/12/1.
 */

public class TouchPswWelcome extends AppCompatActivity {

    public void back(View v) {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_psw_welcome);
        ButterKnife.bind(this);
        LinearLayout top = (LinearLayout) findViewById(R.id.top_bar);
        ImageView top_left = (ImageView) top.findViewById(R.id.top_bar_left);
        TextView top_title = (TextView) top.findViewById(R.id.top_bar_title);
        View top_bottom = top.findViewById(R.id.top_bar_bottom);
        top.setBackgroundColor(Color.TRANSPARENT);
        top_left.setImageResource(R.mipmap.arrow_back_white);
        top_title.setText("手势启动密码");
        top_title.setTextColor(Color.WHITE);
        top_bottom.setBackgroundColor(Color.parseColor("#313131"));
    }

    @OnClick(R.id.touch_psw_welcome_btn)
    public void onClick() {
        Toast.makeText(this, "该功能内测中，尽情期待！", Toast.LENGTH_SHORT).show();
    }
}
