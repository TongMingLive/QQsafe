package com.example.tong.qqsafe.LeftNav;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.qqsafe.R;

/**
 * Created by Tong on 2016/12/1.
 */

public class FlashPsw extends AppCompatActivity {

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

    public void touch_psw(View v){startActivity(new Intent(FlashPsw.this,TouchPswWelcome.class));}

    public void no_function(View v){showToast(FlashPsw.this,"该功能内测中，尽情期待!",2000);}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_psw);
        LinearLayout top = (LinearLayout)findViewById(R.id.top_bar);
        TextView top_title = (TextView)top.findViewById(R.id.top_bar_title);
        top_title.setText("启动密码");
    }
}
