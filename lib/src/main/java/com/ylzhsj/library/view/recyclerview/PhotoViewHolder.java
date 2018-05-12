package com.ylzhsj.library.view.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by vip on 2018/5/9.
 */

class PhotoViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    public PhotoViewHolder(View itemView) {
        super(itemView);
    }

    public <E extends View> E get(int id) {
        View childView = views.get(id);
        if (null == childView) {
            childView = itemView.findViewById(id);
            views.put(id, childView);
        }
        return (E) childView;
    }
}
