package com.ylzhsj.sjt.adapter;


import android.widget.TextView;

import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.view.recyclerview.BaseRecyclerViewHolder;
import com.ylzhsj.sjt.R;
import com.ylzhsj.sjt.bean.base.ExtarctHistoryBean;
import com.ylzhsj.sjt.bean.base.MyWalletBean;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class ExtractHistoryAdapter extends BaseRecyclerAdapter<ExtarctHistoryBean> {

    @BindView(R.id.tv_amount)
    TextView getTvAmount;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.tv_bank)
    TextView tvBank;

    public ExtractHistoryAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_extract_history;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, ExtarctHistoryBean data, int position) {
        getTvAmount.setText(data.getAmount());
        tvTime.setText(data.getTime());
        tvResult.setText(data.getResult());
        tvBank.setText(data.getBank());
    }

}
