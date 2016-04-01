package com.hyy.jccy.wallpaperdemo.fragment.subfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyy.jccy.wallpaperdemo.R;

/**
 * Created by hyy on 2016/4/1.
 */
public class HotWallpaperFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_wallpaper_layout, null);
        return view;
    }
}
