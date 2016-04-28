package com.hyy.jccy.wallpaperdemo.fragment.subfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hyy.jccy.wallpaperdemo.R;
import com.hyy.jccy.wallpaperdemo.adapter.HotWallpaperRecyclerAdapter;
import com.hyy.jccy.wallpaperdemo.bean.WallpaperBean;
import com.hyy.jccy.wallpaperdemo.model.WallpaperDao;
import com.hyy.jccy.wallpaperdemo.util.VolleyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyy on 2016/4/1.
 */
public class HotWallpaperFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private WallpaperDao mWallpaperDao;
    private List<WallpaperBean> mWallpaperBeans = new ArrayList<>();
    private String URL ="http://theme.abclauncher.com/v1/wallpapers/recently/a";
    private HotWallpaperRecyclerAdapter mHotWallpaperRecyclerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWallpaperDao = WallpaperDao.getInstance(getActivity());
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_wallpaper_layout, null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mHotWallpaperRecyclerAdapter = new HotWallpaperRecyclerAdapter(getActivity(), mWallpaperBeans);
        mRecyclerView.setAdapter(mHotWallpaperRecyclerAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    /**
     * 请求数据 初始化数据
     */
    private void initData() {
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ArrayList<WallpaperBean> wallpaperBeans = new ArrayList<>();
                try {
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++){
                        WallpaperBean wallpaperBean = mWallpaperDao.parseJsonData(data.getJSONObject(i));
                        wallpaperBeans.add(wallpaperBean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("xxx", jsonObject.toString());
                mWallpaperBeans.addAll(wallpaperBeans);
                if (mWallpaperBeans.size() >0){
                    Log.e("xxx","mWallpaperBeans size :" + mWallpaperBeans.size());
                    mWallpaperDao.addWallpaperToCache("init",mWallpaperBeans);
                    mHotWallpaperRecyclerAdapter.notifyDataSetChanged();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("xxx", volleyError.toString());
            }
        };


        mWallpaperDao.requestWallpaperDataByUrl(URL,listener,errorListener);
    }
}
