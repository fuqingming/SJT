
package com.ylzhsj.sjt.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.ylzhsj.sjt.R;
import com.ylzhsj.sjt.bean.base.HallBean;
import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.view.recyclerview.BaseRecyclerViewHolder;
import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class FragmentHallAdapter extends BaseRecyclerAdapter<HallBean> {

    @BindView(R.id.nameTextView)
    TextView nameTextView;
    @BindView(R.id.imageView)
    ImageView imageView;

    public FragmentHallAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_fragment_hall_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, final HallBean data, final int position) {
        nameTextView.setText(data.getName());
        imageView.setImageResource(data.getIcon());
    }

}