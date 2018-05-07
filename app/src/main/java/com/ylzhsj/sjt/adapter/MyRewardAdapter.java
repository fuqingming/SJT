package com.ylzhsj.sjt.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.view.recyclerview.BaseRecyclerViewHolder;
import com.ylzhsj.sjt.R;
import com.ylzhsj.sjt.bean.base.MoneyMakingHallBean;
import com.ylzhsj.sjt.bean.base.MyRewardBean;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MyRewardAdapter extends BaseRecyclerAdapter<MyRewardBean> {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_text)
    TextView tvText;

    public MyRewardAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_my_reward;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MyRewardBean data, int position) {
        ivIcon.setImageResource(data.getIcon());
        tvName.setText(data.getName());
        tvAddress.setText(data.getAddress());
        tvAmount.setText(data.getAmount());
        tvTime.setText(data.getTime());
        tvTitle.setText(data.getTitle());
        tvText.setText(data.getText());
    }

}
