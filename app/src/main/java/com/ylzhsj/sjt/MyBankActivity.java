package com.ylzhsj.sjt;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.ylzhsj.library.base.BaseListActivity;
import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.sjt.adapter.MyBankAdapter;
import com.ylzhsj.sjt.adapter.MyWalletAdapter;

import butterknife.OnClick;

public class MyBankActivity extends BaseListActivity {

    private MyBankAdapter m_myBankAdapter = new MyBankAdapter();

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_my_bank;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"我的银行卡",true);
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter() {
        return m_myBankAdapter;
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
//                Intent it = new Intent(MyBankActivity.this,MyWalletDetailsActivity.class);
//                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
//                startActivity(it);
            }

        });
    }

    @OnClick({R.id.tv_add_bank})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_add_bank:
                Intent it = new Intent(MyBankActivity.this,AddBankActivity.class);
//                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
                startActivity(it);
                break;
        }
    }

    protected void requestData(){

        executeOnLoadDataSuccess(DataUtil.initMyBank(),true);
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
