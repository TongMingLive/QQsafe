package com.example.tong.qqsafe.LeftNav;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.qqsafe.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tong on 2016/12/1.
 */

public class TimeCorrect extends AppCompatActivity {
    private LinearLayout top;
    private TextView top_title;
    private TextView years, hoer;
    private Button correct_btn;
    private SimpleDateFormat ymd, hm;
    private Date curDate;

    //自定义toast显示时间方法
    public static void showToast(final Activity activity, final String word, final long time) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = Toast.makeText(activity, word, Toast.LENGTH_LONG);
                toast.show();
                android.os.Handler handler = new android.os.Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }

    public void back(View v){
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_correct);
        top = (LinearLayout)findViewById(R.id.top_bar);
        top_title = (TextView)top.findViewById(R.id.top_bar_title);
        years = (TextView) findViewById(R.id.time_years);
        hoer = (TextView) findViewById(R.id.time_hoer);
        correct_btn = (Button) findViewById(R.id.time_correct_btn);

        top_title.setText("校准时间");

        ymd = new SimpleDateFormat("yyyy年MM月dd日");
        hm = new SimpleDateFormat("HH:mm");
        curDate = new Date(System.currentTimeMillis());//获取当前时间
        years.setText(ymd.format(curDate));
        hoer.setText(hm.format(curDate));

        correct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curDate = new Date(System.currentTimeMillis());
                years.setText(ymd.format(curDate));
                hoer.setText(hm.format(curDate));
                showToast(TimeCorrect.this,
                        "校准成功,当前运营商时间已调整为\n\t\t\tGMT+8\t\t" + ymd.format(curDate) + hm.format(curDate),
                        2000);
            }
        });
    }
}
