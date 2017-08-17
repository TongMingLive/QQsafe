package com.example.tong.qqsafe.FragmentMorePackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tong.qqsafe.BaseFragment;
import com.example.tong.qqsafe.FragmentUtilPackage.UtilGameLock;
import com.example.tong.qqsafe.Help;
import com.example.tong.qqsafe.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tong on 2016/12/5.
 */

public class FragmentMore extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_more, null);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.more_saoma, R.id.more_qqselect, R.id.more_jiance, R.id.more_toutiao, R.id.more_huodong})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.more_saoma:
                startActivity(new Intent(getActivity(),MoreSaoma.class));
                break;
            case R.id.more_qqselect:
                startActivity(new Intent(getActivity(),MoreQqSelect.class));
                break;
            case R.id.more_jiance:
                startActivity(new Intent(getActivity(),MoreWifi.class));
                break;
            case R.id.more_toutiao:
                intent.putExtra("url", "https://aq.qq.com/cn2/manage/mbtoken/sec_headline_list_index?source_id=3222");
                intent.putExtra("title", "安全头条");
                intent.setClass(getActivity(), Help.class);
                startActivity(intent);
                break;
            case R.id.more_huodong:
                intent.putExtra("url", "https://ui.ptlogin2.qq.com/cgi-bin/login?style=8&appid=523005422&s_url=http%3A%2F%2Faq.qq.com%2Fcn2%2Fsafe_service%2Fmy_activities%3Fsource_id%3D3013&low_login=0&hln_css=https%3A%2F%2Faq.qq.com%2Fv2%2Fimages%2Flogo_new.png&hln_custompage=1");
                intent.putExtra("title", "热门活动");
                intent.setClass(getActivity(), Help.class);
                startActivity(intent);
                break;
        }
    }
}
