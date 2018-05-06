package com.ylzhsj.sjt.adapter;


import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.view.recyclerview.BaseRecyclerViewHolder;
import com.ylzhsj.sjt.R;
import com.ylzhsj.sjt.bean.base.MoneyMakingHallBean;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MoneyMakingHallAdapter extends BaseRecyclerAdapter<MoneyMakingHallBean> {



    public MoneyMakingHallAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_message_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MoneyMakingHallBean data, int position) {
    }

}
