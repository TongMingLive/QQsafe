package com.xys.libzxing.zxing.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.xys.libzxing.R;

/**
 * Created by Tong on 2016/12/22.
 */

public class HelpSaoma extends Activity {
    public void back(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        TextView topBarTitle = (TextView)findViewById(R.id.top_bar_title);
        topBarTitle.setText("帮助");

        WebView helpWeb = (WebView) findViewById(R.id.help_web);
        helpWeb.loadUrl("https://kf.qq.com/touch/sappfaq/120509riAvQj140416ZRNf6B.html?platform=14");

        //启用支持javascript
        WebSettings settings = helpWeb.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}
