package com.hyy.jccy.wallpaperdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyy.jccy.wallpaperdemo.R;

/**
 * Created by hyy on 2016/4/1.
 */
public class FavoriteFragment extends android.support.v4.app.Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_layout, null);
        return view;
    }
}
