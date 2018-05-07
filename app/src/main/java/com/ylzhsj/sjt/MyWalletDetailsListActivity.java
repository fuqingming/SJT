package com.ylzhsj.sjt;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.ylzhsj.library.base.BaseListActivity;
import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.sjt.adapter.MyWalletAdapter;

public class MyWalletDetailsListActivity extends BaseListActivity {

    private MyWalletAdapter m_myWalletAdapter = new MyWalletAdapter();

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_common_list;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"钱包明细",true);
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter() {
        return m_myWalletAdapter;
    }

    @Override
    protected void initLayoutManager() {
        LinearLayoutManager m_linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
        mRecyclerView.setLoadMoreEnabled(false);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.one)
                .setColorResource(R.color.spliter_line_color)
                .build();

        mRecyclerView.addItemDecoration(divider);

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshView();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                if ( REQUEST_COUNT <= totalPage) {
                    mCurrentPage++;
                    requestData();
                    isRequestInProcess = true;
                } else {
                    mRecyclerView.setNoMore(true);
                }
            }
        });

        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it = new Intent(MyWalletDetailsListActivity.this,MyWalletDetailsActivity.class);
//                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
                startActivity(it);
            }

        });
    }

    protected void requestData(){

        executeOnLoadDataSuccess(DataUtil.initMyWallet(),true);
        executeOnLoadFinish();
//        HttpClient.get(ApiStores.changePwd,ApiStores.changePwd("","",""), new HttpCallback<ResponseBaseBean>() {//ResponseHallBean
//            @Override
//            public void OnSuccess(ResponseBaseBean response) {
//                if(response.getResult()){
//                    List<TeacherAnalysisBean> responseFragmentHallBeen = new ArrayList<>();
//                    responseFragmentHallBeen.addAll(response.getContent().getJuemi().getData());
//                    executeOnLoadDataSuccess(responseFragmentHallBeen);
//                }
//            }
//
//            @Override
//            public void OnFailure(String message) {
//                executeOnLoadDataError(null);
//            }
//
//            @Override
//            public void OnRequestStart() {
//            }
//
//            @Override
//            public void OnRequestFinish() {
//                executeOnLoadFinish();
//            }
//        });
    }
}
