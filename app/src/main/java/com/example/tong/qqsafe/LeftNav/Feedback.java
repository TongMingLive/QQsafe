package com.example.tong.qqsafe.LeftNav;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.qqsafe.R;

/**
 * Created by Tong on 2016/12/12.
 */

public class Feedback extends AppCompatActivity {
    private EditText et;
    private TextView top_btn;
    //返回按钮
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
        setContentView(R.layout.feedback);
        et = (EditText)findViewById(R.id.feedback_et);
        top_btn = new TextView(this);

        LinearLayout top = (LinearLayout) findViewById(R.id.top_bar);
        TextView top_title = (TextView) top.findViewById(R.id.top_bar_title);
        LinearLayout top_right = (LinearLayout) top.findViewById(R.id.top_bar_right);
        top_title.setText("我要反馈");
        top_btn.setLayoutParams(
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        top_btn.setText("发送");
        top_btn.setTextColor(Color.parseColor("#AAAAAA"));
        top_btn.setTextSize(15);
        top_btn.setPadding(0,0,dip2px(getApplicationContext(),10),0);
        top_right.addView(top_btn);
        //输入框监听事件
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (et.getSelectionEnd() > 0){
                    top_btn.setTextColor(Color.parseColor("#5890E5"));
                    top_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                            Toast.makeText(Feedback.this,"您的反馈已被采纳，我们将会尽快处理！",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    top_btn.setTextColor(Color.parseColor("#AAAAAA"));
                    top_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }
            }
        });
    }
}
