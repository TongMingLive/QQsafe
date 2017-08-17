package com.example.tong.qqsafe.FragmentUtilPackage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tong.qqsafe.BaseFragment;
import com.example.tong.qqsafe.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tong on 2016/12/5.
 */

public class FragmentUtil extends BaseFragment {
    private TextView num1, num2, num3, num4, num5, num6;
    private ProgressBar pb;
    private int renovateTime = 30;//验证码刷新间隔时间（单位秒）
    private int pint = 0;//当前进度 最大 1000%（单位%）

    //findViewById
    private void initView(View v) {
        num1 = (TextView) v.findViewById(R.id.util_num1);
        num2 = (TextView) v.findViewById(R.id.util_num2);
        num3 = (TextView) v.findViewById(R.id.util_num3);
        num4 = (TextView) v.findViewById(R.id.util_num4);
        num5 = (TextView) v.findViewById(R.id.util_num5);
        num6 = (TextView) v.findViewById(R.id.util_num6);

        pb = (ProgressBar) v.findViewById(R.id.util_pb);
    }

    //获取验证码
    private int getRandom() {
        int random = (int) (Math.random() * 10);
        return random;
    }

    //刷新验证码
    private void initNum() {
        num1.setText(getRandom() + "");
        num2.setText(getRandom() + "");
        num3.setText(getRandom() + "");
        num4.setText(getRandom() + "");
        num5.setText(getRandom() + "");
        num6.setText(getRandom() + "");
    }

    //进度条、验证码定时器
    private void change() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                pb.setProgress(pint);
                handler.postDelayed(this, renovateTime);//定时器执行间隔时间
                if (pint == 1001) {
                    pint = 0;
                    initNum();
                }
                pint++;
            }
        };
        handler.postDelayed(runnable, 0);//打开定时器
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_util, null);
        initView(v);
        initNum();
        change();
        pb.setMax(1000); //设置进度条最大为1000

        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.util_qq_protect, R.id.util_game_protect, R.id.util_mail_protect, R.id.util_qb_protect, R.id.util_account_lock, R.id.util_game_lock, R.id.util_modify_pwd, R.id.util_recover_friends})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.util_qq_protect:
                startActivity(new Intent(getActivity(),UtilQqProtect.class));
                break;
            case R.id.util_game_protect:
                startActivity(new Intent(getActivity(),UtilGameProtect.class));
                break;
            case R.id.util_mail_protect:
                startActivity(new Intent(getActivity(),UtilEmileProtect.class));
                break;
            case R.id.util_qb_protect:
                startActivity(new Intent(getActivity(),UtilQbProtect.class));
                break;
            case R.id.util_account_lock:
                startActivity(new Intent(getActivity(),UtilQqLock.class));
                break;
            case R.id.util_game_lock:
                startActivity(new Intent(getActivity(),UtilGameLock.class));
                break;
            case R.id.util_modify_pwd:
                startActivity(new Intent(getActivity(),UtilModifyPwd.class));
                break;
            case R.id.util_recover_friends:
                startActivity(new Intent(getActivity(),UtilRecoverFriends.class));
                break;
        }
    }
}