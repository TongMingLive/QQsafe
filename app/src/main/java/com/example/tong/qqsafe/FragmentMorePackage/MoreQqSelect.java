package com.example.tong.qqsafe.FragmentMorePackage;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tong.qqsafe.Help;
import com.example.tong.qqsafe.R;

import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tong on 2016/12/27.
 */

public class MoreQqSelect extends AppCompatActivity {
    static final Pattern ACCEPTED_URI_SCHEMA = Pattern.compile("(?i)"
            + // switch on case insensitive matching
            "("
            + // begin group for schema
            "(?:http|https|file):\\/\\/" + "|(?:inline|data|about|javascript):" + "|(?:.*:.*@)"
            + ")" + "(.*)");

    private WebView wv;

    public void back(View v){finish();}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_qq_select);
        LinearLayout top = (LinearLayout) findViewById(R.id.top_bar);
        ImageView top_left = (ImageView) top.findViewById(R.id.top_bar_left);
        TextView top_title = (TextView) top.findViewById(R.id.top_bar_title);
        View top_bottom = top.findViewById(R.id.top_bar_bottom);
        top.setBackgroundColor(Color.TRANSPARENT);
        top_left.setImageResource(R.mipmap.arrow_back_white);
        top_title.setText("诈骗QQ查询");
        top_title.setTextColor(Color.WHITE);
        top_bottom.setBackgroundColor(Color.TRANSPARENT);

        wv = (WebView)findViewById(R.id.more_select_wb);
        wv.loadUrl("https://ui.ptlogin2.qq.com/cgi-bin/login?style=8&appid=523005422&s_url=http%3A%2F%2Faq.qq.com%2Fcn2%2Fmanage%2Fmbtoken%2Ffraudqq_check&low_login=0&hln_css=https%3A%2F%2Faq.qq.com%2Fv2%2Fimages%2Flogo_new.png&hln_custompage=1");
        //启用支持javascript
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置不让跳转系统浏览器
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
        });
        //拉起QQ一键登录
        wv.setWebViewClient(new MyWebViewClient());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()){
            wv.goBack();//返回前一个页面
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
        if(MoreQqSelect.this.getPackageManager().resolveActivity(intent,0) ==null) {
            String packagename = intent.getPackage();
            if(packagename !=null) {
                intent =new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pname:"+ packagename));
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                Log.i("TAG","packagename="+packagename);
                MoreQqSelect.this.finish();
                MoreQqSelect.this.startActivity(intent);
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
            if(MoreQqSelect.this.startActivityIfNeeded(intent, -1)) {
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
        PackageManager pm = MoreQqSelect.this.getPackageManager();
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
