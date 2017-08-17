package com.example.tong.qqsafe.FragmentUtilPackage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.qqsafe.Help;
import com.example.tong.qqsafe.R;
import com.kyleduo.switchbutton.SwitchButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tong on 2016/12/22.
 */

public class UtilQqProtect extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    SharedPreferences spf;
    Editor editor;

    @Bind(R.id.sb_login_qq_safe)
    SwitchButton sbLoginQqSafe;
    @Bind(R.id.sb_web_qq_safe)
    SwitchButton sbWebQqSafe;
    @Bind(R.id.top_bar_title)
    TextView topBarTitle;
    @Bind(R.id.top_bar_right)
    LinearLayout topBarRight;

    public void back(View v) {
        finish();
    }

    //DP转PX像素单位
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_qq_protect);
        ButterKnife.bind(this);
        topBarTitle.setText("登陆保护");
        ImageView help = new ImageView(this);
        help.setLayoutParams(
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        help.setImageResource(R.mipmap.title_button_help_black);
        help.setPadding(dip2px(getApplicationContext(), 15),
                dip2px(getApplicationContext(), 15),
                dip2px(getApplicationContext(), 15),
                dip2px(getApplicationContext(), 15));
        topBarRight.addView(help);
        topBarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("url", "https://kf.qq.com/touch/sappfaq/120509riAvQj1501203MbuaY.html?platform=14");
                intent.putExtra("title", "帮助");
                intent.setClass(UtilQqProtect.this, Help.class);
                startActivity(intent);
            }
        });

        spf = this.getSharedPreferences("sb",MODE_PRIVATE);
        editor = spf.edit();
        boolean LoginQqSafe = spf.getBoolean("sbLoginQqSafe",false);
        boolean WebQqSafe = spf.getBoolean("sbWebQqSafe",false);

        sbLoginQqSafe.setCheckedImmediately(LoginQqSafe);
        sbWebQqSafe.setCheckedImmediately(WebQqSafe);
        sbLoginQqSafe.setOnCheckedChangeListener(this);
        sbWebQqSafe.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sb_login_qq_safe:
                editor.putBoolean("sbLoginQqSafe",isChecked);
                break;
            case R.id.sb_web_qq_safe:
                editor.putBoolean("sbWebQqSafe",isChecked);
                break;
        }
        editor.commit();
    }
}
