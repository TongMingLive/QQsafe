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

import com.example.tong.qqsafe.Help;
import com.example.tong.qqsafe.R;
import com.kyleduo.switchbutton.SwitchButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tong on 2016/12/22.
 */

public class UtilGameLock extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    SharedPreferences spf;
    Editor editor;
    @Bind(R.id.top_bar_title)
    TextView topBarTitle;
    @Bind(R.id.top_bar_right)
    LinearLayout topBarRight;
    @Bind(R.id.sb_qq_car)
    SwitchButton sbQqCar;
    @Bind(R.id.sb_lol)
    SwitchButton sbLol;
    @Bind(R.id.sb_jianling)
    SwitchButton sbJianling;
    @Bind(R.id.sb_dnf)
    SwitchButton sbDnf;
    @Bind(R.id.sb_cf)
    SwitchButton sbCf;

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
        setContentView(R.layout.util_game_lock);
        ButterKnife.bind(this);
        topBarTitle.setText("游戏登陆保护");
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
                intent.putExtra("url", "https://kf.qq.com/touch/sappfaq/120509riAvQj140408y2uYbI.html?platform=14");
                intent.putExtra("title", "帮助");
                intent.setClass(UtilGameLock.this, Help.class);
                startActivity(intent);
            }
        });

        spf = this.getSharedPreferences("sb", MODE_PRIVATE);
        editor = spf.edit();
        boolean QqCar = spf.getBoolean("sbQqCar", false);
        boolean Lol = spf.getBoolean("sbLol", false);
        boolean Jianling = spf.getBoolean("sbJianling", false);
        boolean Dnf = spf.getBoolean("sbDnf", false);
        boolean Cf = spf.getBoolean("sbCf", false);

        sbQqCar.setCheckedImmediately(QqCar);
        sbLol.setCheckedImmediately(Lol);
        sbJianling.setCheckedImmediately(Jianling);
        sbDnf.setCheckedImmediately(Dnf);
        sbCf.setCheckedImmediately(Cf);

        sbQqCar.setOnCheckedChangeListener(this);
        sbLol.setOnCheckedChangeListener(this);
        sbJianling.setOnCheckedChangeListener(this);
        sbDnf.setOnCheckedChangeListener(this);
        sbCf.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sb_qq_car:
                editor.putBoolean("sbQqCar", isChecked);
                break;
            case R.id.sb_lol:
                editor.putBoolean("sbLol", isChecked);
                break;
            case R.id.sb_jianling:
                editor.putBoolean("sbJianling", isChecked);
                break;
            case R.id.sb_dnf:
                editor.putBoolean("sbDnf", isChecked);
                break;
            case R.id.sb_cf:
                editor.putBoolean("sbCf", isChecked);
                break;
        }
        editor.commit();
    }
}
