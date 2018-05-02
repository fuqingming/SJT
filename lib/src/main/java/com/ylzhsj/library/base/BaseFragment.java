package com.ylzhsj.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ylzhsj.library.util.HUDProgressUtils;
import com.ylzhsj.library.http.HttpClient;
import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HH
 * Date: 2017/11/9
 */

public abstract class BaseFragment extends Fragment {
    private View mContentView;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    protected KProgressHUD kProgressHUD;
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mContentView != null)
        {
            ViewGroup vgParent = (ViewGroup) mContentView.getParent();
            if (vgParent != null)
            {
                vgParent.removeView(mContentView);
            }
            return mContentView;
        }
        mContentView = inflater.inflate(setLayoutResourceID(),container,false);

        mContentView = inflater.inflate(setLayoutResourceID(), null);
        mContext = getContext();
        mLayoutInflater = inflater;

        kProgressHUD = new HUDProgressUtils().showLoadingImage(getMContext());
        unbinder = ButterKnife.bind(this, getContentView());
        init();
        setUpView();
        setUpData();

        return mContentView;
    }

    protected abstract int setLayoutResourceID();
    protected void init(){
        HttpClient.init(getContext().getApplicationContext(),false);
    }

    protected void setUpView(){}

    protected void setUpData(){}

    protected View getContentView() {
        return mContentView;
    }

    public Context getMContext() {
        return mContext;
    }

    public LayoutInflater getMLayoutInflater() {
        return mLayoutInflater;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
