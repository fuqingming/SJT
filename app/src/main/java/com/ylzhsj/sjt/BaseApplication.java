package com.ylzhsj.sjt;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.widget.ImageView;

import com.blankj.utilcode.util.Utils;
import cn.addapp.pickers.common.AppConfig;
import cn.addapp.pickers.util.LogUtils;
import cn.finalteam.rxgalleryfinal.utils.ModelUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.ylzhsj.library.settings.MyApplication;

public class BaseApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);//shardPrefrences

        //android-pickers
        LogUtils.setIsDebug(cn.addapp.pickers.wheelpicker.BuildConfig.DEBUG);
        if (!LogUtils.isDebug()) {
            android.util.Log.d(AppConfig.DEBUG_TAG, "logcat is disabled");
        }

        MobSDK.init(this, this.getAppkey(), this.getAppSecret());//shareSDK

        //图片选择
        ModelUtils.setDebugModel(true);
        Fresco.initialize(this);
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config.build());
    }

    public BaseApplication() {//shareSDK
    }
    protected String getAppkey() {//shareSDK
        return null;
    }
    protected String getAppSecret() {//shareSDK
        return null;
    }

    @Override
    protected void attachBaseContext(Context base) {//cannot exceed 64K
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }
}