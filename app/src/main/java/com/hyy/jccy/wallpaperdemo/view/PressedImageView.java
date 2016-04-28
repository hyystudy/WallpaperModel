package com.hyy.jccy.wallpaperdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by hyy on 2016/4/25.
 */
public class PressedImageView extends ImageView {
    public PressedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setPressed(boolean pressed) {
        if (pressed){
            setAlpha(200);
        }else {
            setAlpha(255);
        }
        super.setPressed(pressed);
    }
}
