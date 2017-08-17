package com.example.tong.qqsafe.FragmentIndexPackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tong.qqsafe.R;

/**
 * Created by Tong on 2016/12/27.
 */

public class IndexMySafe extends AppCompatActivity {

    public void back(View v){finish();}
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_mysafe);
        LinearLayout top = (LinearLayout)findViewById(R.id.top_bar);
        TextView top_title = (TextView)top.findViewById(R.id.top_bar_title);
        top_title.setText("我的保护");
    }
}
