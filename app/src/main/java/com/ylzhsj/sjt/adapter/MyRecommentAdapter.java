package com.ylzhsj.sjt.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import com.ylzhsj.library.settings.AppSettings;
import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.view.recyclerview.BaseRecyclerViewHolder;
import com.ylzhsj.sjt.R;
import com.ylzhsj.sjt.bean.base.MyCommentBean;
import com.ylzhsj.sjt.bean.base.MyRecommentBean;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MyRecommentAdapter extends BaseRecyclerAdapter<MyRecommentBean> {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    public MyRecommentAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_my_recomment;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MyRecommentBean data, int position) {
        ivIcon.setImageResource(data.getIcon());
        tvName.setText(data.getName());
        String strPhone = data.getPhone().substring(0, 3) + "****" + data.getPhone().substring(7, 11);
        tvPhone.setText(strPhone);
    }

}
