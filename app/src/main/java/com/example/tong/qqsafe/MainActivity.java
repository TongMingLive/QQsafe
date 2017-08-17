package com.example.tong.qqsafe;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.qqsafe.FragmentIndexPackage.FragmentIndex;
import com.example.tong.qqsafe.FragmentMorePackage.FragmentMore;
import com.example.tong.qqsafe.FragmentUtilPackage.FragmentUtil;
import com.example.tong.qqsafe.LeftNav.About;
import com.example.tong.qqsafe.LeftNav.Feedback;
import com.example.tong.qqsafe.LeftNav.FlashPsw;
import com.example.tong.qqsafe.LeftNav.Recommend;
import com.example.tong.qqsafe.LeftNav.SlidingMenu;
import com.example.tong.qqsafe.LeftNav.TimeCorrect;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    //leftNave区
    private LinearLayout left_nav;
    private ListView lv;
    private Integer[] list_img = new Integer[]{
            R.mipmap.passwordicon,
            R.mipmap.settimeicon,
            R.mipmap.aboutandhelp,
            R.mipmap.recommendicon,
            R.mipmap.sendfeedbackicon};
    private String[] list_text_left = new String[]{"启动密码", "校准时间", "关于和帮助", "推荐给好友", "我要反馈"};
    private String[] list_text_right = new String[]{"未开启", "", "V6.8.4", "", ""};
    //main区
    private LinearLayout main,main_view;
    //当前显示的fragment
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private SlidingMenu sm;
    private FragmentManager manager;
    private Fragment currentFragment = new Fragment();
    private List<Fragment> fragments = new ArrayList<>();
    private int currentIndex = 0;
    private ImageView index, util, more;
    private long exitTime = 0;
    private LinearLayout top,topL, topR;
    private ImageView topLimg, topRimg;
    private TextView topTv;
    private PopupWindow mPopupWindow;
    //初始化控件
    private void initView(){
        //leftNav区
        left_nav = (LinearLayout)findViewById(R.id.left_nav_include);
        lv = (ListView) left_nav.findViewById(R.id.left_nav_lv);
        //main区
        sm = (SlidingMenu)findViewById(R.id.main_sm);
        main = (LinearLayout) findViewById(R.id.main);
        main_view = (LinearLayout) findViewById(R.id.main_visible);
        top = (LinearLayout) findViewById(R.id.top_nav);
        topL = (LinearLayout) findViewById(R.id.top_nav_left);
        topR = (LinearLayout) findViewById(R.id.top_nav_right);
        topLimg = (ImageView)findViewById(R.id.top_nav_left_img);
        topRimg = (ImageView)findViewById(R.id.top_nav_right_img);
        topTv = (TextView) findViewById(R.id.top_nav_text);

        index = (ImageView) findViewById(R.id.tab_index);
        util = (ImageView) findViewById(R.id.tab_util);
        more = (ImageView) findViewById(R.id.tab_more);
    }
    //设置left ListView
    private void initLeft(){
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list_img.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View v = inflater.inflate(R.layout.left_nav_item, null);
                ImageView img = (ImageView) v.findViewById(R.id.nav_item_img);
                TextView tvLeft = (TextView) v.findViewById(R.id.nav_item_left_text);
                TextView tvRight = (TextView) v.findViewById(R.id.nav_item_right_text);
                img.setImageResource(list_img[i]);
                tvLeft.setText(list_text_left[i]);
                tvRight.setText(list_text_right[i]);
                return v;
            }
        });
        lv.setDivider(null);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, FlashPsw.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, TimeCorrect.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, About.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, Recommend.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, Feedback.class));
                        break;
                }
            }
        });
    }
    //DP转PX像素单位
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    //设置首页样式
    private void setIndex() {
        top.setBackgroundResource(R.drawable.index_jianbian);
        topTv.setText("userName");
        topTv.setTextColor(Color.WHITE);
        topRimg.setVisibility(View.VISIBLE);
        topRimg.setBackgroundResource(R.mipmap.accountpage_message);
        topRimg.getLayoutParams().width = dip2px(getApplicationContext(), 26);
        topRimg.getLayoutParams().height = dip2px(getApplicationContext(), 18);
        index.setImageResource(R.mipmap.tab_icon_account_pressed);
        topR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Emile",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //设置工具页样式
    private void setUtil() {
        top.setBackgroundColor(Color.WHITE);
        topTv.setText("工具");
        topTv.setTextColor(Color.BLACK);
        topRimg.setVisibility(View.VISIBLE);
        topRimg.setBackgroundResource(R.mipmap.arrow_down);
        topRimg.getLayoutParams().width = dip2px(getApplicationContext(), 20);
        topRimg.getLayoutParams().height = dip2px(getApplicationContext(), 10);
        util.setImageResource(R.mipmap.tab_icon_utils_pressed);
        topR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null == mPopupWindow || !mPopupWindow.isShowing()) {
                    LayoutInflater mLayoutInflater = (LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View music_popunwindwow = mLayoutInflater.inflate(R.layout.util_popwindow, null);
                    LinearLayout help = (LinearLayout)music_popunwindwow.findViewById(R.id.pop_window_help);
                    LinearLayout get = (LinearLayout)music_popunwindwow.findViewById(R.id.pop_window_get);
                    help.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            mPopupWindow.dismiss();
                        }
                    });
                    get.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "暂无验证请求", Toast.LENGTH_SHORT).show();
                            mPopupWindow.dismiss();
                        }
                    });
                    mPopupWindow = new PopupWindow(
                            music_popunwindwow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                    mPopupWindow.showAtLocation(findViewById(R.id.main), Gravity.RIGHT | Gravity.TOP, 0,dip2px(getApplicationContext(),70));
                }else if (null != mPopupWindow && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                    if (null == mPopupWindow) {
                        Log.e("MainActivity", "null == mPopupWindow");
                    }
                }
            }
        });
    }
    //设置探索页样式
    private void setMore() {
        top.setBackgroundColor(Color.WHITE);
        topTv.setText("探索");
        topTv.setTextColor(Color.BLACK);
        topRimg.setVisibility(View.GONE);
        more.setImageResource(R.mipmap.tab_icon_more_pressed);
        topR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
    //扫码方法调用
    public void util_saoma(View v){
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLeft();
        setIndex();
        manager = getSupportFragmentManager();

        if (savedInstanceState != null) { // “内存重启”时调用
            //获取“内存重启”时保存的索引下标
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT,0);
            //注意，添加顺序要跟下面添加的顺序一样！！！！
            fragments.removeAll(fragments);
            fragments.add(manager.findFragmentByTag(0+""));
            fragments.add(manager.findFragmentByTag(1+""));
            fragments.add(manager.findFragmentByTag(2+""));
            //恢复fragment页面
            restoreFragment();
        }else{      //正常启动时调用
            fragments.add(new FragmentIndex());
            fragments.add(new FragmentUtil());
            fragments.add(new FragmentMore());
            showFragment();
        }

        index.setOnClickListener(this);
        util.setOnClickListener(this);
        more.setOnClickListener(this);
        //点击头像打开menu
        topL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sm.openMenu();
                menu();
            }
        });
        //如果当前menu是打开状态单击mainView执行closeMenu
        main_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sm.getIsOpen()){
                    sm.closeMenu();
                    menu();
                }
            }
        });
        //监听滑动事件判断menu状态
        sm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                sm.onTouchEvent(motionEvent);
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    menu();
                    if (null != mPopupWindow && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                        if (null == mPopupWindow) {
                            Log.e("MainActivity", "null == mPopupWindow");
                        }
                    }
                }
                return true;
            }
        });

        LinearLayout fr = (LinearLayout)findViewById(R.id.main_fragment);
        fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mPopupWindow && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                    if (null == mPopupWindow) {
                        Log.e("MainActivity", "null == mPopupWindow");
                    }
                }
            }
        });
    }
    //底部tab点击事件
    @Override
    public void onClick(View view) {
        index.setImageResource(R.mipmap.tab_icon_account_normal);
        util.setImageResource(R.mipmap.tab_icon_utils_normal);
        more.setImageResource(R.mipmap.tab_icon_more_normal);
        switch (view.getId()) {
            case R.id.tab_index:
                currentIndex = 0;
                setIndex();
                break;
            case R.id.tab_util:
                currentIndex = 1;
                setUtil();
                break;
            case R.id.tab_more:
                currentIndex = 2;
                setMore();
                break;
            default:
                break;
        }
        showFragment();

        if (null != mPopupWindow && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            if (null == mPopupWindow) {
                Log.e("MainActivity", "null == mPopupWindow");
            }
        }
    }
    //“内存重启”时保存当前的fragment名字
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_FRAGMENT,currentIndex);
        super.onSaveInstanceState(outState);
    }
    //使用show() hide()切换页面
    // 显示fragment
    private void showFragment(){
        FragmentTransaction transaction = manager.beginTransaction();
        //如果之前没有添加过
        if(!fragments.get(currentIndex).isAdded()){
            transaction
                    .hide(currentFragment)
                    .add(R.id.main_fragment,fragments.get(currentIndex),""+currentIndex);
            //第三个参数为添加当前的fragment时绑定一个tag
        }else{
            transaction
                    .hide(currentFragment)
                    .show(fragments.get(currentIndex));
        }
        currentFragment = fragments.get(currentIndex);
        transaction.commit();
    }
    //恢复fragment
    private void restoreFragment(){
        FragmentTransaction mBeginTreansaction = manager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if(i == currentIndex){
                mBeginTreansaction.show(fragments.get(i));
            }else{
                mBeginTreansaction.hide(fragments.get(i));
            }
        }
        mBeginTreansaction.commit();
        //把当前显示的fragment记录下来
        currentFragment = fragments.get(currentIndex);
    }
    //menu打开关闭事件
    private void menu(){
        if (sm.getIsOpen()){
            main.clearFocus();
            main.setFocusable(false);
            main_view.clearFocus();
            main_view.setFocusable(true);
            main_view.setVisibility(View.VISIBLE);
        }else{
            main.clearFocus();
            main.setFocusable(true);
            main_view.clearFocus();
            main_view.setFocusable(false);
            main_view.setVisibility(View.INVISIBLE);
        }
    }
    //再次返回键退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (sm.getIsOpen()){
                sm.closeMenu();
                menu();
            }else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
