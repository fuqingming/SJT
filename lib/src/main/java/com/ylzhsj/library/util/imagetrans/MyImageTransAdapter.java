package com.ylzhsj.library.util.imagetrans;

import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.ylzhsj.library.R;
import com.ylzhsj.library.base.MyTransApplication;
import com.ylzhsj.library.util.imagetrans.view.RoundPageIndicator;

import it.liuting.imagetrans.ImageTransAdapter;


/**
 * Created by liuting on 17/6/15.
 */

public class MyImageTransAdapter extends ImageTransAdapter {
    private View view;
    private View topPanel;
    private RoundPageIndicator bottomPanel;
    private boolean isShow = true;

    @Override
    protected View onCreateView(View parent, ViewPager viewPager, final DialogInterface dialogInterface) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_trans_adapter, null);
        topPanel = view.findViewById(R.id.top_panel);
        bottomPanel = (RoundPageIndicator) view.findViewById(R.id.page_indicator);
        view.findViewById(R.id.top_panel_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInterface.cancel();
            }
        });
        topPanel.setTranslationY(-MyTransApplication.dpToPx(56));
        bottomPanel.setTranslationY(MyTransApplication.dpToPx(80));
        bottomPanel.setViewPager(viewPager);
        return view;
    }

    @Override
    public void onPullRange(float range) {
        topPanel.setTranslationY(-MyTransApplication.dpToPx(56) * range * 4);
        bottomPanel.setTranslationY(MyTransApplication.dpToPx(80) * range * 4);
    }

    @Override
    public void onPullCancel() {
        showPanel();
    }

    @Override
    protected void onOpenTransStart() {
        showPanel();
    }

    @Override
    protected void onOpenTransEnd() {

    }

    @Override
    protected void onCloseTransStart() {
        hiddenPanel();
    }

    @Override
    protected void onCloseTransEnd() {
        TileBitmapDrawable.clearCache();
    }

    @Override
    protected boolean onClick(View v,int pos) {
        if (isShow) {
            showPanel();
        } else {
            hiddenPanel();
        }
        isShow = !isShow;

        return true;
    }

    @Override
    protected void onLongClick(View v,int pos) {
        Toast.makeText(view.getContext(), "long click", Toast.LENGTH_SHORT).show();
    }

    public void hiddenPanel() {
        topPanel.animate().translationY(-MyTransApplication.dpToPx(56)).setDuration(200).start();
        bottomPanel.animate().translationY(MyTransApplication.dpToPx(80)).setDuration(200).start();
    }

    public void showPanel() {
        topPanel.animate().translationY(0).setDuration(200).start();
        bottomPanel.animate().translationY(0).setDuration(200).start();
    }

}
