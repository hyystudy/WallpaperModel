package com.hyy.jccy.wallpaperdemo.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by miao on 2016/1/4.
 */
public class VolleyUtil {
    private static final String TAG = "VolleyUtil";
    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;

    public static void init(Context context){
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(context);
            LruImageCache lruImageCache = LruImageCache.instance(context);
            mImageLoader = new ImageLoader(mRequestQueue, lruImageCache);
        }
    }

    public static RequestQueue getQequestQueue(Context context){
        if (mRequestQueue == null){
            init(context);

        }
        return mRequestQueue;
    }

    public static ImageLoader getImageLoader(){
        if (mImageLoader != null){
            return mImageLoader;
        }else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }

    //取消Tag前缀为cancelPrefixStr的请求,
    public static void cancelRequestByPrefix(final String cancelPrefixStr, final String cancelExclude){
        if (mRequestQueue != null){
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    boolean result = false;
                    try {
                        String requestStr = request.getTag().toString();
                        if (requestStr != null && requestStr.startsWith(cancelPrefixStr) && !
                                cancelExclude.equals(requestStr)) {
                            result =  true;
                        }
                    }catch (Exception e){}
                    return result;
                }
            });
        }
    }
}
