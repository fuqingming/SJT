package com.ylzhsj.sjt.adapter;


import com.ylzhsj.library.view.recyclerview.BaseRecyclerViewHolder;
import com.ylzhsj.sjt.R;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MessageAdapter extends BaseRecyclerAdapter<MessageBean> {



    public MessageAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_message_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MessageBean data, int position) {
    }

}
