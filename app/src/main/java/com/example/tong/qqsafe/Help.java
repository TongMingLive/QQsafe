package com.example.tong.qqsafe;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tong on 2016/12/22.
 */

public class Help extends AppCompatActivity {
    static final Pattern ACCEPTED_URI_SCHEMA = Pattern.compile("(?i)"
            + // switch on case insensitive matching
            "("
            + // begin group for schema
            "(?:http|https|file):\\/\\/" + "|(?:inline|data|about|javascript):" + "|(?:.*:.*@)"
            + ")" + "(.*)");

    @Bind(R.id.top_bar_title)
    TextView topBarTitle;
    @Bind(R.id.help_web)
    WebView helpWeb;

    public void back(View v){finish();}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");

        topBarTitle.setText(title);

        helpWeb.loadUrl(url);
        //启用支持javascript
        WebSettings settings = helpWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置不让跳转系统浏览器
        helpWeb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
        });
        helpWeb.setWebViewClient(new MyWebViewClient());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && helpWeb.canGoBack()){
            helpWeb.goBack();//返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //拉起手机QQ App
    class MyWebViewClient extends WebViewClient {
        @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return checkUrlLoading(url);
        }
    }

    private boolean checkUrlLoading(String url) {
        Intent intent;
        try{
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
        }catch(URISyntaxException e) {
            Log.w("CustomWebViewClient","Bad URI "+ url +": "+ e.getMessage());
            return false;
        }
        if(Help.this.getPackageManager().resolveActivity(intent,0) ==null) {
            String packagename = intent.getPackage();
            if(packagename !=null) {
                intent =new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pname:"+ packagename));
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                Log.i("TAG","packagename="+packagename);
                Help.this.finish();
                Help.this.startActivity(intent);
                return true;
            }else{
                return false;
            }
        }
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        intent.setComponent(null);
        Matcher m = ACCEPTED_URI_SCHEMA.matcher(url);
        if(m.matches() && !isSpecializedHandlerAvailable(intent)) {
            return false;
        }
        try{
            if(Help.this.startActivityIfNeeded(intent, -1)) {
                Log.i("TAG","startActivityIfNeeded");
                return true;
            }
        }catch(ActivityNotFoundException ex) {
            // ignore the error. If no application can handle the URL,
            // eg about:blank, assume the browser can handle it.
        }
        return false;
    }
    private boolean isSpecializedHandlerAvailable(Intent intent) {
        PackageManager pm = Help.this.getPackageManager();
        List<ResolveInfo> handlers = pm.queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER);
        if(handlers ==null|| handlers.size() ==0) {
            return false;
        }
        for(ResolveInfo resolveInfo : handlers) {
            IntentFilter filter = resolveInfo.filter;
            if(filter ==null) {
                continue;
            }
            if(filter.countDataAuthorities() ==0|| filter.countDataPaths() ==0) {
                // Generic handler, skip
                continue;
            }
            return true;
        }
        return false;
    }
}
