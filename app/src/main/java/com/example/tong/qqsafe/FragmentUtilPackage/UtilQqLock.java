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

public class UtilQqLock extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    SharedPreferences spf;
    Editor editor;
    @Bind(R.id.top_bar_title)
    TextView topBarTitle;
    @Bind(R.id.top_bar_right)
    LinearLayout topBarRight;
    @Bind(R.id.sb_all_safe)
    SwitchButton sbAllSafe;
    @Bind(R.id.sb_WeChat)
    SwitchButton sbWeChat;
    @Bind(R.id.sb_phone_qq)
    SwitchButton sbPhoneQq;
    @Bind(R.id.sb_computer_qq)
    SwitchButton sbComputerQq;
    @Bind(R.id.sb_Other)
    SwitchButton sbOther;

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
        setContentView(R.layout.util_account_lock);
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
                intent.putExtra("url", "https://kf.qq.com/touch/sappfaq/120509riAvQj140224bQzMFN.html?platform=14");
                intent.putExtra("title", "帮助");
                intent.setClass(UtilQqLock.this, Help.class);
                startActivity(intent);
            }
        });

        spf = this.getSharedPreferences("sb", MODE_PRIVATE);
        editor = spf.edit();
        boolean AllSafe = spf.getBoolean("sbAllSafe", false);
        boolean WeChat = spf.getBoolean("sbWeChat", false);
        boolean PhoneQq = spf.getBoolean("sbPhoneQq", false);
        boolean ComputerQq = spf.getBoolean("sbComputerQq", false);
        boolean Other = spf.getBoolean("sbOther", false);

        sbAllSafe.setCheckedImmediately(AllSafe);
        if (AllSafe){
            sbWeChat.setCheckedImmediately(true);
            sbPhoneQq.setCheckedImmediately(true);
            sbComputerQq.setCheckedImmediately(true);
            sbOther.setCheckedImmediately(true);
        }

        sbWeChat.setCheckedImmediately(WeChat);
        sbPhoneQq.setCheckedImmediately(PhoneQq);
        sbComputerQq.setCheckedImmediately(ComputerQq);
        sbOther.setCheckedImmediately(Other);

        sbAllSafe.setOnCheckedChangeListener(this);
        sbWeChat.setOnCheckedChangeListener(this);
        sbPhoneQq.setOnCheckedChangeListener(this);
        sbComputerQq.setOnCheckedChangeListener(this);
        sbOther.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sb_all_safe:
                editor.putBoolean("sbAllSafe", isChecked);
                if (isChecked){
                    sbWeChat.setChecked(true);
                    sbPhoneQq.setChecked(true);
                    sbComputerQq.setChecked(true);
                    sbOther.setChecked(true);
                }else {
                    sbWeChat.setChecked(false);
                    sbPhoneQq.setChecked(false);
                    sbComputerQq.setChecked(false);
                    sbOther.setChecked(false);
                }
                break;
            case R.id.sb_WeChat:
                editor.putBoolean("sbWeChat", isChecked);
                break;
            case R.id.sb_phone_qq:
                editor.putBoolean("sbPhoneQq", isChecked);
                break;
            case R.id.sb_computer_qq:
                editor.putBoolean("sbComputerQq", isChecked);
                break;
            case R.id.sb_Other:
                editor.putBoolean("sbOther", isChecked);
                break;
        }
        editor.commit();
    }
}
