package com.ylzhsj.sjt;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.ylzhsj.library.base.BaseListActivity;
import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.sjt.adapter.MyMessageAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class MyMessageActivity extends BaseListActivity {

    private MyMessageAdapter m_myMessageAdapter = new MyMessageAdapter();

    @BindView(R.id.tv_title_right)
    TextView m_tvTitleRight;
    @BindView(R.id.rl_select_type)
    RelativeLayout m_rlSetType;

    private boolean m_isEdit = false;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"我的消息",true,"编辑");
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter() {
        return m_myMessageAdapter;
    }

    @Override
    protected void initLayoutManager() {
        LinearLayoutManager m_linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
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
                Utils.showToast(MyMessageActivity.this,m_myMessageAdapter.getListData().get(position).isChecked()+"");
            }

        });
    }

    @OnClick({R.id.tv_title_right,R.id.tv_read,R.id.tv_delete})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_title_right:
                if(m_isEdit){
                    m_isEdit = false;
                    m_tvTitleRight.setText("编辑");
                    m_rlSetType.setVisibility(View.GONE);
                }else{
                    m_isEdit = true;
                    m_tvTitleRight.setText("取消");
                    m_rlSetType.setVisibility(View.VISIBLE);
                }

                if(m_myMessageAdapter.getListData().size() > 0){
                    for(int i = 0 ;i < m_myMessageAdapter.getListData().size() ; i ++){
                        m_myMessageAdapter.getListData().get(i).setVisible(m_isEdit);
                    }
                }

                m_myMessageAdapter.notifyDataSetChanged();

                break;
            case R.id.tv_read:
                m_isEdit = false;
                m_tvTitleRight.setText("编辑");
                m_rlSetType.setVisibility(View.GONE);

                break;
            case R.id.tv_delete:
                m_isEdit = false;
                m_tvTitleRight.setText("编辑");
                m_rlSetType.setVisibility(View.GONE);

                break;
        }
    }

    protected void requestData(){

        executeOnLoadDataSuccess(DataUtil.initMyMessage(),true);
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
