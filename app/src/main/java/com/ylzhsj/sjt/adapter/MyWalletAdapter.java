package com.ylzhsj.sjt.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.view.recyclerview.BaseRecyclerViewHolder;
import com.ylzhsj.sjt.R;
import com.ylzhsj.sjt.bean.base.MyCommentBean;
import com.ylzhsj.sjt.bean.base.MyWalletBean;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MyWalletAdapter extends BaseRecyclerAdapter<MyWalletBean> {

    @BindView(R.id.tv_title)
    TextView tvTlitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_amount)
    TextView tvAmount;

    public MyWalletAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_my_wallet;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MyWalletBean data, int position) {
        tvTlitle.setText(data.getTitle());
        tvTime.setText(data.getTime());
        tvAmount.setText(data.getAmount());
    }

}
