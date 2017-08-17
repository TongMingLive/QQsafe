package com.xys.libzxing.zxing.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.R;

/**
 * Created by Tong on 2016/12/19.
 */

public class ResultStudy extends Activity {
    private ImageView iv;
    private TextView tv,type,tips;
    private Button btn;
    private String re;

    public void back(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        iv = (ImageView)findViewById(R.id.result_img);
        tv = (TextView) findViewById(R.id.result_tv);
        type = (TextView)findViewById(R.id.result_type);
        tips = (TextView)findViewById(R.id.result_tips);
        btn = (Button) findViewById(R.id.result_btn);

        Intent intent = getIntent();
        re = intent.getStringExtra("result");
        tv.setText(re);

        if (Patterns.WEB_URL.matcher(re).matches() && re.substring(0, 4).equals("http")) {
            //符合url标准
            if (re.substring(0, 8).equals("https://")){
                type.setText("安全网站，请放心访问");
                iv.setImageResource(R.drawable.common_success);
                tips.setVisibility(View.GONE);
            }else {
                btn.setText("仍然访问");
            }
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(re));
                    startActivity(i);
                }
            });
        } else {
            //不符合url标准
            iv.setVisibility(View.GONE);
            type.setVisibility(View.GONE);
            tips.setVisibility(View.GONE);
            btn.setVisibility(View.GONE);
        }
    }
}
