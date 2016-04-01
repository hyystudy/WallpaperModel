package com.hyy.jccy.wallpaperdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hyy.jccy.wallpaperdemo.fragment.subfragment.HotWallpaperFragment;
import com.hyy.jccy.wallpaperdemo.fragment.subfragment.LatestWallpaperFragment;

/**
 * Created by hyy on 2016/4/1.
 */
public class AppFragmentAdapter extends FragmentPagerAdapter {

    private String[] titles = {"最新","最热"};
    public AppFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new LatestWallpaperFragment();
        }
        return new HotWallpaperFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
