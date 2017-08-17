package com.example.tong.qqsafe.LeftNav;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tong.qqsafe.R;

/**
 * Created by Tong on 2016/12/4.
 */

public class AboutXulie extends AppCompatActivity {

    public void back(View v) {
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_xulie);

        LinearLayout top = (LinearLayout)findViewById(R.id.top_bar);
        TextView top_title = (TextView)top.findViewById(R.id.top_bar_title);
        top_title.setText("序列号");
    }
}
