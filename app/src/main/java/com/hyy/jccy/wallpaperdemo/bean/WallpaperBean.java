package com.hyy.jccy.wallpaperdemo.bean;

import java.io.Serializable;

/**
 * Created by zhouaili on 2016/4/22.
 */
public class WallpaperBean implements Serializable{
        public String id;
        public String title;
        public String thumbnail;//小图
        public String image;//
        public String preview;//预览图
        public String max;//最大图

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getImage() {
        return image;
    }

    public String getPreview() {
        return preview;
    }

    public String getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "WallpaperBean: "+ " id :" + id + " title :" + title + "thumbnail :"
                + thumbnail + "image :" + image + "preview :" + preview + "max :" + max;
    }
}
