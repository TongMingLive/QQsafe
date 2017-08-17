package com.example.tong.qqsafe.FragmentMorePackage;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tong.qqsafe.R;

import java.util.List;

/**
 * Created by POI4 on 2016/12/27.
 */

public class MoreWifi extends AppCompatActivity {
    private RelativeLayout re;

    public void back(View v) {
        finish();
    }

    //wifi管理者对象
    private WifiManager wifiManager;
    //wifi热点集合
    List<ScanResult> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_wifijiance);

        LinearLayout top = (LinearLayout) findViewById(R.id.top_bar);
        ImageView top_left = (ImageView) top.findViewById(R.id.top_bar_left);
        TextView top_title = (TextView) top.findViewById(R.id.top_bar_title);
        View top_bottom = top.findViewById(R.id.top_bar_bottom);
        top.setBackgroundColor(Color.TRANSPARENT);
        top_left.setImageResource(R.mipmap.arrow_back);
        top_title.setText("WIFI安全检测");
        top_title.setTextColor(Color.BLACK);
        top_bottom.setBackgroundColor(Color.TRANSPARENT);

        re = (RelativeLayout) findViewById(R.id.ou_more_jiance_rel);

        //获得wifi管理者对象
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //打开wifi
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }

        list = wifiManager.getScanResults();

        ListView listView = (ListView) findViewById(R.id.listView);

        if (list == null) {
            re.setAlpha(1f);
        } else {
            listView.setAdapter(new MyAdapter(this, list));

        }
    }

    public class MyAdapter extends BaseAdapter {

        LayoutInflater inflater;
        List<ScanResult> list;

        public MyAdapter(Context context, List<ScanResult> list) {
            // TODO Auto-generated constructor stub
            this.inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            re.setAlpha(0f);
            View view = null;
            //绘制布局
            view = inflater.inflate(R.layout.more_wifi_item, null);

            ScanResult scanResult = list.get(position);

            TextView textView = (TextView) view.findViewById(R.id.textView);

            textView.setText(scanResult.SSID);

            TextView signalStrenth = (TextView) view.findViewById(R.id.signal_strenth);

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            //判断信号强度，显示对应的指示图标
            if (Math.abs(scanResult.level) > 100) {
                imageView.setBackgroundDrawable(getResources().getDrawable(R.mipmap.connect_signal_level_3));
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.account_detail_ok));
                signalStrenth.setText("安全");
                signalStrenth.setTextColor(Color.parseColor("#19BF3D"));
            } else if (Math.abs(scanResult.level) > 80) {
                imageView.setBackgroundDrawable(getResources().getDrawable(R.mipmap.connect_signal_level_2));
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.account_detail_ok));
                signalStrenth.setText("安全");
                signalStrenth.setTextColor(Color.parseColor("#19BF3D"));
            } else if (Math.abs(scanResult.level) > 70) {
                imageView.setBackgroundDrawable(getResources().getDrawable(R.mipmap.connect_signal_level_1));
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.account_detail_sigh));
                signalStrenth.setText("警告");
                signalStrenth.setTextColor(Color.parseColor("#FF9B0D"));
            } else {
                imageView.setBackgroundDrawable(getResources().getDrawable(R.mipmap.connect_signal_level_0));
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.account_detail_sigh));
                signalStrenth.setText("警告");
                signalStrenth.setTextColor(Color.parseColor("#FF9B0D"));
            }
            return view;
        }

    }
}
