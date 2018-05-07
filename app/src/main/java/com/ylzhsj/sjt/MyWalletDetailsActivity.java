package com.ylzhsj.sjt;

import android.widget.TextView;

import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.util.Utils;

import butterknife.BindView;

public class MyWalletDetailsActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_details_type)
    TextView m_tvDetailsType;
    @BindView(R.id.tv_amount)
    TextView m_tvAmount;
    @BindView(R.id.tv_time)
    TextView m_tvTime;
    @BindView(R.id.tv_pay_type)
    TextView m_tvPayType;
    @BindView(R.id.tv_type)
    TextView m_tvType;
    @BindView(R.id.tv_text)
    TextView m_tvText;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_my_wallet_details;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"钱包明细",true);
        m_tvDetailsType.setText("悬赏保障金");
        m_tvAmount.setText("+10.00");
        m_tvTime.setText("2018.04.17  10：22");
        m_tvPayType.setText("支付宝");
        m_tvType.setText("退款");
        m_tvText.setText("退款-减肥-减肥-活动保证金");
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
