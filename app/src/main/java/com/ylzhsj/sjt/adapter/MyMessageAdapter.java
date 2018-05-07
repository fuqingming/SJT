package com.ylzhsj.sjt.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.library.view.recyclerview.BaseRecyclerViewHolder;
import com.ylzhsj.sjt.R;
import com.ylzhsj.sjt.bean.base.MyMessageBean;
import butterknife.BindView;

public class MyMessageAdapter extends BaseRecyclerAdapter<MyMessageBean> {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.cb_delete)
    CheckBox cbDelete;

    public MyMessageAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_my_message;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder,final MyMessageBean data, final int position) {
        tvTime.setText(data.getTime());
        tvTitle.setText(data.getTitle());
        tvText.setText(data.getText());

        if(data.isVisible()){
            cbDelete.setVisibility(View.VISIBLE);
        }else{
            cbDelete.setVisibility(View.GONE);
        }

        cbDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.setChecked(isChecked);
            }
        });
    }

}
