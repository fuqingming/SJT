package com.ylzhsj.sjt;

import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.util.Utils;

public class MoneyMakingHallActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "AboutActivity";



    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_money_making_hall;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"赚钱大厅",true);
    }
}
