package com.hyy.jccy.wallpaperdemo.model;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.hyy.jccy.wallpaperdemo.bean.WallpaperBean;
import com.hyy.jccy.wallpaperdemo.util.VolleyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhouaili on 2016/4/22.
 */
public class WallpaperDao {
    private HashMap<String,List<WallpaperBean>> mWallpaperCache = new HashMap<>();
    private static Context sContext;
    private static WallpaperDao wallpaperDao;

    public WallpaperDao(){}

    public static WallpaperDao getInstance(Context context){
        sContext = context;

        if (wallpaperDao == null){
            wallpaperDao = new WallpaperDao();
        }

        return wallpaperDao;
    }

    public List<WallpaperBean> getWallpaperCache(String tag){
        return  mWallpaperCache.get(tag);
    }

    public void addWallpaperToCache(String tag,List<WallpaperBean> list){
        if (!TextUtils.isEmpty(tag)){
            mWallpaperCache.put(tag,list);
        }
    }


    public WallpaperBean parseJsonData(JSONObject jsonObject){
        WallpaperBean wallpaperBean = new WallpaperBean();
        try {
            wallpaperBean.id = jsonObject.getString("id");
            wallpaperBean.title = jsonObject.getString("title");
            JSONArray images = jsonObject.getJSONArray("images");
            for (int i = 0; i < images.length(); i++){
                JSONObject imagesJSONObject = images.getJSONObject(i);
                String type = imagesJSONObject.getString("type");
                switch (type){
                    case "thumbnail":
                        wallpaperBean.thumbnail = imagesJSONObject.getString("url");
                        break;
                    case "image":
                        wallpaperBean.image = imagesJSONObject.getString("url");
                        break;
                    case "preview":
                        wallpaperBean.preview = imagesJSONObject.getString("url");
                        break;
                    case "max":
                        wallpaperBean.max = imagesJSONObject.getString("url");
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return wallpaperBean;
    }

    public void requestWallpaperDataByUrl(String url ,Response.Listener listener,Response.ErrorListener errorListener){
        RequestQueue qequestQueue = VolleyUtil.getQequestQueue(sContext);

        JsonObjectRequest wallpaperBeanJsonRequest = new JsonObjectRequest(url, null, listener, errorListener);
        qequestQueue.add(wallpaperBeanJsonRequest);
    }

}
