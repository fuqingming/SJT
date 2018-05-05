package com.ylzhsj.sjt;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.util.Utils;
import cn.addapp.pickers.common.AppConfig;
import cn.addapp.pickers.util.LogUtils;

import com.bumptech.glide.Glide;
import com.ylzhsj.library.settings.MyApplication;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;

public class BaseApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);//shardPrefrences

        ISNav.getInstance().init(new ImageLoader() {//ImageSelector
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });

        //android-pickers
        LogUtils.setIsDebug(cn.addapp.pickers.wheelpicker.BuildConfig.DEBUG);
        if (!LogUtils.isDebug()) {
            android.util.Log.d(AppConfig.DEBUG_TAG, "logcat is disabled");
        }
    }
}