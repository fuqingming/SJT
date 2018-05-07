package com.ylzhsj.sjt.adapter;


import android.widget.TextView;

import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.view.recyclerview.BaseRecyclerViewHolder;
import com.ylzhsj.sjt.R;
import com.ylzhsj.sjt.bean.base.MyBankean;
import com.ylzhsj.sjt.bean.base.MyWalletBean;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MyBankAdapter extends BaseRecyclerAdapter<MyBankean> {

    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_type)
    TextView tvBankType;
    @BindView(R.id.tv_bank)
    TextView tvBank;

    public MyBankAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_my_bank;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MyBankean data, int position) {
        tvBankName.setText(data.getBankName());
        tvBankType.setText(data.getBankType());
        tvBank.setText(data.getBank());
    }

}
