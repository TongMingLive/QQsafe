package com.example.tong.qqsafe.LeftNav;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tong.qqsafe.*;

/**
 * Created by Tong on 2016/12/1.
 */

public class About extends AppCompatActivity {
    private Button video,xieyi;

    public void back(View v){
        finish();
    }
    public void pinlun(View v){
        String url = "http://fusion.qq.com/cgi-bin/qzapps/unified_jump?actionFlag=0&appid=9676&params=pname%3Dcom.tencent.token%26versioncode%3D75%26channelid%3D%26actionflag%3D0&from=mqq";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void help(View v){
        Intent intent = new Intent();
        intent.putExtra("url","https://kf.qq.com/touch/product/aq_app.html?platform=14&scene_id=kf106");
        intent.putExtra("title","帮助");
        intent.setClass(About.this,Help.class);
        startActivity(intent);
    }
    public void xuliehao(View v){startActivity(new Intent(About.this,AboutXulie.class));}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        video = (Button)findViewById(R.id.about_video);
        xieyi = (Button)findViewById(R.id.about_xieyi);

        LinearLayout top = (LinearLayout)findViewById(R.id.top_bar);
        TextView top_title = (TextView)top.findViewById(R.id.top_bar_title);
        top_title.setText("关于QQ安全中心");

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://player.youku.com/embed/XNzcwMTc3NDUy";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://m.aq.qq.com/cn/manage/token/wtoken_terms";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}