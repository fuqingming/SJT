package com.ylzhsj.sjt;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylzhsj.library.backhandler.OnTaskSuccessComplete;
import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.cache.AsyncImageLoader;
import com.ylzhsj.library.settings.AppSettings;
import com.ylzhsj.library.util.CleanMessageUtil;
import com.ylzhsj.library.util.Utils;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "AboutActivity";

    @BindView(R.id.tv_version)
    TextView m_tvVersion;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_about;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"关于我们",true);
        m_tvVersion.setText(Utils.getVersionCode(this));
    }

    @OnClick({R.id.ll_about,R.id.ll_privacy})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.ll_about:

                break;
            case R.id.ll_privacy:

                break;
        }
    }
}
