package com.example.tong.qqsafe.FragmentMorePackage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tong.qqsafe.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tong on 2016/12/22.
 */

public class MoreSaoma extends AppCompatActivity {

    public void back(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_saoma);
        ButterKnife.bind(this);

        LinearLayout top = (LinearLayout) findViewById(R.id.top_bar);
        ImageView top_left = (ImageView) top.findViewById(R.id.top_bar_left);
        TextView top_title = (TextView) top.findViewById(R.id.top_bar_title);
        View top_bottom = top.findViewById(R.id.top_bar_bottom);
        top.setBackgroundColor(Color.TRANSPARENT);
        top_left.setImageResource(R.mipmap.arrow_back_white);
        top_title.setText("二维码安全检测");
        top_title.setTextColor(Color.WHITE);
        top_bottom.setBackgroundColor(Color.TRANSPARENT);
    }

    @OnClick(R.id.more_saoma_btn)
    public void onClick() {
        finish();
        startActivityForResult(new Intent(MoreSaoma.this, CaptureActivity.class),0);
    }
}

