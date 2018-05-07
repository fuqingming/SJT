package com.ylzhsj.sjt;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.util.Utils;

import butterknife.BindView;
import butterknife.OnClick;

public class ExtractActivity extends BaseAppCompatActivity {


    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_extract;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"我要提现",true);
    }

    @OnClick({R.id.tv_history})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_history:
                Intent it = new Intent(ExtractActivity.this,ExtractHistoryActivity.class);
                startActivity(it);
                break;
        }
    }

    @Override
    protected void setUpData() {
        //        HttpClient.get(ApiStores.changePwd,ApiStores.changePwd("","",""), new HttpCallback<ResponseBaseBean>() {//ResponseHallBean
//            @Override
//            public void OnSuccess(ResponseBaseBean response) {
//                if(response.getResult()){
//                    List<TeacherAnalysisBean> responseFragmentHallBeen = new ArrayList<>();
//                    responseFragmentHallBeen.addAll(response.getContent().getJuemi().getData());
//                    executeOnLoadDataSuccess(responseFragmentHallBeen);
//                }
//            }
//
//            @Override
//            public void OnFailure(String message) {
//                executeOnLoadDataError(null);
//            }
//
//            @Override
//            public void OnRequestStart() {
//            }
//
//            @Override
//            public void OnRequestFinish() {
//                executeOnLoadFinish();
//            }
//        });
    }
}
