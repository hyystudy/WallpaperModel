package com.hyy.jccy.wallpaperdemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hyy.jccy.wallpaperdemo.bean.WallpaperBean;
import com.hyy.jccy.wallpaperdemo.fragment.detailfragment.WallpaperDetailFragment;
import com.hyy.jccy.wallpaperdemo.model.WallpaperDao;

import java.util.List;

/**
 * Created by zhouaili on 2016/4/25.
 */
public class WallpaperDetailActivity extends AppCompatActivity {

    private WallpaperDao mWallpaperDao;
    private List<WallpaperBean> mWallpaperBeanList;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_detail_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.theme_ic_arrow_back_white_24dp);
        mToolbar.setBackgroundColor(Color.TRANSPARENT);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mWallpaperDao = WallpaperDao.getInstance(this);
        mWallpaperBeanList = mWallpaperDao.getWallpaperCache("init");
        int position = getIntent().getIntExtra("position", 0);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewpager.setCurrentItem(position);


    }

    public class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return WallpaperDetailFragment.getInstance(mWallpaperBeanList.get(position));
        }

        @Override
        public int getCount() {
            return mWallpaperBeanList.size();
        }
    }

}
