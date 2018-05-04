package com.ylzhsj.sjt;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.blankj.utilcode.util.Utils;
import cn.addapp.pickers.common.AppConfig;
import cn.addapp.pickers.util.LogUtils;
import cn.finalteam.rxgalleryfinal.utils.ModelUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.ylzhsj.library.settings.MyApplication;

public class BaseApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);//shardPrefrences
        //图片选择
        ModelUtils.setDebugModel(true);
        Fresco.initialize(this);
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        ImageLoader.getInstance().init(config.build());

        //android-pickers
        LogUtils.setIsDebug(cn.addapp.pickers.wheelpicker.BuildConfig.DEBUG);
        if (!LogUtils.isDebug()) {
            android.util.Log.d(AppConfig.DEBUG_TAG, "logcat is disabled");
        }
    }
}