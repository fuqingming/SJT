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
import com.ylzhsj.library.settings.AppSettings;
import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.sjt.adapter.MyCommentAdapter;
import com.ylzhsj.sjt.adapter.MyWalletAdapter;

public class MyWalletActivity extends BaseListActivity {

    private MyWalletAdapter m_myWalletAdapter = new MyWalletAdapter();

    private LinearLayout m_llDetails;
    private LinearLayout m_llBank;
    private LinearLayout m_llExtract;
    private TextView m_tvAmount;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_common_list;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"我的钱包",true);
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter() {
        return m_myWalletAdapter;
    }

    @Override
    protected void initLayoutManager() {
        LinearLayoutManager m_linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
        View llHeader = LayoutInflater.from(this).inflate(R.layout.common_my_wallet_head,mRecyclerView, false);
        m_llDetails = llHeader.findViewById(R.id.ll_details);
        m_llBank = llHeader.findViewById(R.id.ll_bank);
        m_llExtract = llHeader.findViewById(R.id.ll_extract);
        m_tvAmount = llHeader.findViewById(R.id.tv_amount);
        mRecyclerViewAdapter.addHeaderView(llHeader);
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
                Intent it = new Intent(MyWalletActivity.this,MyWalletDetailsActivity.class);
//                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
                startActivity(it);
            }

        });

        //钱包明细
        m_llDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MyWalletActivity.this,MyWalletDetailsListActivity.class);
//                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
                startActivity(it);
            }
        });

        //银行卡
        m_llBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MyWalletActivity.this,MyBankActivity.class);
//                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
                startActivity(it);
            }
        });

        //提现
        m_llExtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MyWalletActivity.this,ExtractActivity.class);
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
