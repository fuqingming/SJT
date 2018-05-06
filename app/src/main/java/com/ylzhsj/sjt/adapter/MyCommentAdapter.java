package com.ylzhsj.sjt.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.view.recyclerview.BaseRecyclerViewHolder;
import com.ylzhsj.sjt.R;
import com.ylzhsj.sjt.bean.base.MoneyMakingHallBean;
import com.ylzhsj.sjt.bean.base.MyCommentBean;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MyCommentAdapter extends BaseRecyclerAdapter<MyCommentBean> {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_text)
    TextView tvText;

    public MyCommentAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_my_comment;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MyCommentBean data, int position) {
        ivIcon.setImageResource(data.getIcon());
        tvName.setText(data.getName());
        tvTime.setText(data.getTime());
        tvText.setText(data.getText());
    }

}
