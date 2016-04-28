package com.hyy.jccy.wallpaperdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyy.jccy.wallpaperdemo.R;
import com.hyy.jccy.wallpaperdemo.WallpaperDetailActivity;
import com.hyy.jccy.wallpaperdemo.bean.WallpaperBean;

import java.util.List;

/**
 * Created by zhouaili on 2016/4/25.
 */
public class HotWallpaperRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<WallpaperBean> mList;
    private Context mContext;

    public HotWallpaperRecyclerAdapter(Context context,List<WallpaperBean> list){
        mContext = context;
        mList = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_hot_wallpaper_fragment,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final HotViewHolder hotViewHolder = (HotViewHolder) holder;
        Glide.with(mContext).load(mList.get(position).thumbnail).into(hotViewHolder.wallpaper);
        Log.e("onBindViewHolder", mList.get(position).thumbnail);

        hotViewHolder.wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WallpaperDetailActivity.class);
                intent.putExtra("position",position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class HotViewHolder extends RecyclerView.ViewHolder{

        private  ImageView wallpaper;

        public HotViewHolder(View itemView) {
            super(itemView);
            wallpaper = (ImageView) itemView.findViewById(R.id.walpaper);
        }
    }
}
