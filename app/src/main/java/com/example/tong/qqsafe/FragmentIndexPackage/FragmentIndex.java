package com.example.tong.qqsafe.FragmentIndexPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tong.qqsafe.BaseFragment;
import com.example.tong.qqsafe.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tong on 2016/12/5.
 */

public class FragmentIndex extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_index, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.index_my_account, R.id.index_mb_sub, R.id.index_my_password, R.id.index_protec_sub})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.index_my_account:
                startActivity(new Intent(getActivity(),IndexMyFoot.class));
                break;
            case R.id.index_mb_sub:
                startActivity(new Intent(getActivity(),IndexMyMibao.class));
                break;
            case R.id.index_my_password:
                startActivity(new Intent(getActivity(),IndexMyPswd.class));
                break;
            case R.id.index_protec_sub:
                startActivity(new Intent(getActivity(),IndexMySafe.class));
                break;
        }
    }
}