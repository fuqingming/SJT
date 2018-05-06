package com.ylzhsj.library.util.imagetrans;

import android.content.Context;
import android.view.ViewGroup;

import com.ylzhsj.library.base.MyTransApplication;
import com.ylzhsj.library.util.imagetrans.view.RingLoadingView;

import it.liuting.imagetrans.listener.ProgressViewGet;

/**
 * Created by liuting on 18/3/19.
 */

public class MyProgressBarGet implements ProgressViewGet<RingLoadingView> {
    @Override
    public RingLoadingView getProgress(Context context) {
        RingLoadingView view = new RingLoadingView(context);
        view.setLayoutParams(new ViewGroup.LayoutParams(MyTransApplication.dpToPx(50), MyTransApplication.dpToPx(50)));
        return view;
    }

    @Override
    public void onProgressChange(RingLoadingView view, float progress) {
        view.setProgress(progress);
    }
}
