package com.ylzhsj.sjt;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.util.Utils;
import butterknife.BindView;
import butterknife.OnClick;

public class MyEwmActivity extends BaseAppCompatActivity {
    @BindView(R.id.tv_my_join)
    TextView m_tvMyJoin;
    @BindView(R.id.iv_ewm)
    ImageView m_tvEwm;


    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_my_ewm;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"专属二维码",true);
    }

    @OnClick({R.id.btn_share})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.btn_share:

                break;
        }
    }
}
