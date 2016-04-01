package com.hyy.jccy.wallpaperdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyy.jccy.wallpaperdemo.R;
import com.hyy.jccy.wallpaperdemo.adapter.AppFragmentAdapter;

/**
 * Created by hyy on 2016/4/1.
 */
public class AppFragment extends android.support.v4.app.Fragment {
    private static TabLayout sTabs;
    private ViewPager mViewpager;

    public static AppFragment newInstance(TabLayout tabLayout){
        AppFragment appFragment = new AppFragment();
        sTabs =tabLayout;
        return appFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("xxxx","onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_layout, null);
        return view;
    }

    private void initView(View view) {
        mViewpager = (ViewPager) view.findViewById(R.id.viewpager);
        AppFragmentAdapter appFragmentAdapter = new AppFragmentAdapter(getFragmentManager());
        mViewpager.setAdapter(appFragmentAdapter);
        sTabs.setupWithViewPager(mViewpager);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("xxxx","onResume");
        View view = getView();
        initView(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("xxxx", "onDestroy");
    }
}
