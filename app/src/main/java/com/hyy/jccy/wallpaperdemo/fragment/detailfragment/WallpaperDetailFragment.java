package com.hyy.jccy.wallpaperdemo.fragment.detailfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hyy.jccy.wallpaperdemo.R;
import com.hyy.jccy.wallpaperdemo.bean.WallpaperBean;

import java.io.Serializable;

/**
 * Created by zhouaili on 2016/4/25.
 */
public class WallpaperDetailFragment extends Fragment {

    private WallpaperBean mWallpaperBean;
    private ImageView preview;

    public static WallpaperDetailFragment getInstance(WallpaperBean wallpaperBean){
        Bundle bundle = new Bundle();
        bundle.putSerializable("wallpaperBean", wallpaperBean);
        WallpaperDetailFragment wallpaperDetailFragment = new WallpaperDetailFragment();
        wallpaperDetailFragment.setArguments(bundle);

        return  wallpaperDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mWallpaperBean = (WallpaperBean) bundle.getSerializable("wallpaperBean");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallpaper_detail_layout, null);
        preview =
                (ImageView) view.findViewById(R.id.preview);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Glide.with(this).load(mWallpaperBean.preview).into(preview);
    }
}
