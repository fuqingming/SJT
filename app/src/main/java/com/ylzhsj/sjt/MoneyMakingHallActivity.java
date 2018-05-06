package com.ylzhsj.sjt;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.ylzhsj.library.base.BaseListActivity;
import com.ylzhsj.library.http.ApiStores;
import com.ylzhsj.library.http.HttpCallback;
import com.ylzhsj.library.http.HttpClient;
import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.sjt.adapter.MoneyMakingHallAdapter;
import com.ylzhsj.sjt.bean.base.MoneyMakingHallBean;
import com.ylzhsj.sjt.bean.response.ResponseBaseBean;

import java.util.ArrayList;
import java.util.List;

public class MoneyMakingHallActivity extends BaseListActivity {
    private static final String LOG_TAG = "AboutActivity";

    private MoneyMakingHallAdapter m_moneyMakingHallAdapter = new MoneyMakingHallAdapter();

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_money_making_hall;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"赚钱大厅",true);
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter() {
        return m_moneyMakingHallAdapter;
    }

    @Override
    protected void initLayoutManager() {
        LinearLayoutManager m_linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
        View m_headerBanner = LayoutInflater.from(this).inflate(R.layout.common_money_making_hall_head,mRecyclerView, false);
        mRecyclerViewAdapter.addHeaderView(m_headerBanner);
        mRecyclerView.setLoadMoreEnabled(false);
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
//                Intent it = new Intent(this,NewsWebViewActivity.class);
//                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
//                startActivity(it);
            }

        });
    }

    protected void requestData(){

        List<MoneyMakingHallBean> moneyMakingHallBeans = new ArrayList<>();
        executeOnLoadDataSuccess(moneyMakingHallBeans,true);
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
