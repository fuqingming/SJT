package com.ylzhsj.library.view.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    protected Context mContext;
    protected List<T> mDatas;

    public BaseRecyclerViewAdapter(Context context, List<T> datas){
        mContext = context;
        mDatas = datas;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(getContentView(viewType),parent,false);
        return new BaseRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ButterKnife.bind(this, holder.getView());
        covert(holder,mDatas.get(position),position);

    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    protected abstract int getContentView(int viewType);

    protected abstract void covert(BaseRecyclerViewHolder holder, T data, int position);

    protected OnSelectClickListener m_ListenerSelectFragment = null;

    public interface OnSelectClickListener
    {
        void OnSelectClick(int position);
    }

    public void onSelectFragmentClickListener(OnSelectClickListener listener)
    {
        m_ListenerSelectFragment = listener;
    }
}
