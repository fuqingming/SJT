package com.ylzhsj.sjt.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.view.recyclerview.BaseRecyclerViewHolder;
import com.ylzhsj.sjt.R;
import com.ylzhsj.sjt.bean.base.MyJoinInBean;
import com.ylzhsj.sjt.bean.base.MyRecommentBean;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MyJoinInAdapter extends BaseRecyclerAdapter<MyJoinInBean> {

    @BindView(R.id.tv_title)
    TextView ivTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_time)
    TextView tvTime;

    public MyJoinInAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_my_join;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MyJoinInBean data, int position) {
        ivTitle.setText(data.getTitle());
        String strPhone = data.getPhone().substring(0, 3) + "****" + data.getPhone().substring(7, 11);
        tvName.setText(data.getName()+"("+strPhone+")");
        tvType.setText(data.getType());
        tvTime.setText(data.getTime());
    }

}
