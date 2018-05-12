package com.ylzhsj.sjt;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.ylzhsj.library.base.BaseListActivity;
import com.ylzhsj.library.base.BasePopListActivity;
import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.sjt.adapter.MoneyMakingHallAdapter;
import com.ylzhsj.sjt.adapter.MoneyTypeCheckedAdapter;
import com.ylzhsj.sjt.bean.base.MoneyMakingHallBean;
import com.ylzhsj.sjt.bean.base.MoneyMakingHallTypeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MoneyMakingHallActivity extends BasePopListActivity<MoneyMakingHallBean> {
    private static final String LOG_TAG = "AboutActivity";

    private MoneyMakingHallAdapter m_moneyMakingHallAdapter = new MoneyMakingHallAdapter();

    @BindView(R.id.cb_all_type)
    CheckBox m_cbAllType;
    @BindView(R.id.cb_order_by)
    CheckBox m_cbOrderBy;
    @BindView(R.id.cb_amount)
    CheckBox m_cbAmount;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_money_making_hall;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"赚钱大厅",true);
    }

    @Override
    protected BaseRecyclerAdapter<MoneyMakingHallBean> getListAdapter() {
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

        m_cbAllType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if(m_arrTeacherName.size() > 1){
                    filterTabToggleT(isChecked, m_cbAllType,DataUtil.initMoneyType(),new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

//                            m_strUrl = "t_id="+m_arrTeacherName.get(position).getT_id();
                            hidePopListView();
                            onRefreshView();
                        }
                    }, m_cbAllType, m_cbOrderBy,m_cbAmount);
//                }else{
//                    getTypeTeacher1(isChecked);
//                }

            }
        });

        m_cbOrderBy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if(m_arrTeacherName.size() > 1){
                filterTabToggleT(isChecked, m_cbOrderBy,DataUtil.initMoneyType(),new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

//                            m_strUrl = "t_id="+m_arrTeacherName.get(position).getT_id();
                        hidePopListView();
                        onRefreshView();
                    }
                }, m_cbOrderBy, m_cbAllType,m_cbAmount);
//                }else{
//                    getTypeTeacher1(isChecked);
//                }

            }
        });
    }

    @Override
    protected BaseAdapter setInitAdapter(List<MoneyMakingHallBean> bean) {
        return new MoneyTypeCheckedAdapter(this,bean);
    }

//    @Override
//    protected BaseAdapter setInitAdapter(List<MoneyMakingHallTypeBean> bean) {
//        return new MoneyTypeCheckedAdapter(this,bean);
//    }

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
