package com.example.tong.qqsafe.LeftNav;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tong.qqsafe.R;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tong on 2016/12/12.
 */

public class Recommend extends AppCompatActivity {

    public void back(View v) {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend);
        ButterKnife.bind(this);

        LinearLayout top = (LinearLayout) findViewById(R.id.top_bar);
        TextView top_title = (TextView) top.findViewById(R.id.top_bar_title);
        top_title.setText("推荐给好友");
    }

    @OnClick(R.id.recommend_frends)
    public void onClick(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "QQ安全中心，千万人的选择\n点击立即下载！\nhttp://m.aq.qq.com/lp?source_id=2713");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
