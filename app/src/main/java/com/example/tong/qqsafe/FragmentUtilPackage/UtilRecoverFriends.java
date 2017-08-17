package com.example.tong.qqsafe.FragmentUtilPackage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.tong.qqsafe.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tong on 2016/12/22.
 */

public class UtilRecoverFriends extends AppCompatActivity {
    @Bind(R.id.top_bar_title)
    TextView topBarTitle;

    public void back(View v) {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_resume_friend);
        ButterKnife.bind(this);
        topBarTitle.setText("恢复好友");
    }

    @OnClick(R.id.util_friends_btn)
    public void onClick() {
        String url = "https://aq.qq.com/cn2/frd_recovery/frd_recovery_index?source_id=3221";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
