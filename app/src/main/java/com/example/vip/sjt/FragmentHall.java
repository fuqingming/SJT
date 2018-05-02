package com.example.vip.sjt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.example.vip.sjt.adapter.FragmentHallAdapter;
import com.example.vip.sjt.bean.base.HallBean;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.ylzhsj.library.base.BaseListFragment;
import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.util.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * */
public class FragmentHall extends BaseListFragment<HallBean> {

	private FragmentHallAdapter m_fragmentTrainAdapter= new FragmentHallAdapter();
	private List<HallBean> m_hallBean;

	private enum FunctionIndex{RENOVATION, BUILDING, REDUCE_WEIGHT ,
							   DESIGNER_ENTREPRENEURSHIP , MANAGER_ENTREPRENEURSHIP , QUIT_SMOKING,
							   QUIT_DRINKING , GIVE_UP_GAMBLING , INFORMATION
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_hall;
	}

	@Override
	public void initView() {
		super.initView();
		Utils.initCommonTitle(getContentView(),"赏金堂");

		m_hallBean = new ArrayList<>();

		m_hallBean.add(new HallBean("装修量房",R.mipmap.zhuangxiu));
		m_hallBean.add(new HallBean("买建材",R.mipmap.maijiancai));
		m_hallBean.add(new HallBean("减肥",R.mipmap.jianfei));
		m_hallBean.add(new HallBean("设计师创业",R.mipmap.shejishichuangye));
		m_hallBean.add(new HallBean("项目经理创业",R.mipmap.xiangmujinglichuangye));
		m_hallBean.add(new HallBean("戒烟",R.mipmap.jieyan));
		m_hallBean.add(new HallBean("戒酒",R.mipmap.jiejiu));
		m_hallBean.add(new HallBean("戒赌",R.mipmap.jiedu));
		m_hallBean.add(new HallBean("资讯与建议",R.mipmap.zixunyujianyi));
	}

	@Override
	protected BaseRecyclerAdapter<HallBean> getListAdapter() {
		return m_fragmentTrainAdapter;
	}

	@Override
	public void initData() {
		super.initData();
	}

	@Override
	protected void initLayoutManager() {
		GridLayoutManager mgr = new GridLayoutManager(getMContext(), 3);
		mRecyclerView.setLayoutManager(mgr);

		mRecyclerView.setLoadMoreEnabled(false);
		View header = LayoutInflater.from(getMContext()).inflate(R.layout.common_fragment_hall_head,mRecyclerView, false);

		mRecyclerViewAdapter.addHeaderView(header);
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
				switch (FunctionIndex.values()[position])
				{
					case RENOVATION:
					{

					}
					break;

					case BUILDING:
					{

					}
					break;

					case REDUCE_WEIGHT:
					{

					}
					break;

					case DESIGNER_ENTREPRENEURSHIP:
					{

					}
					break;

					case MANAGER_ENTREPRENEURSHIP:
					{

					}
					break;

					case QUIT_SMOKING:
					{

					}
					break;

					case QUIT_DRINKING:
					{

					}
					break;

					case GIVE_UP_GAMBLING:
					{

					}
					break;

					case INFORMATION:
					{

					}
					break;

					default:
						break;
				}
			}

		});
	}

	protected void requestData(){
		executeOnLoadDataSuccess(m_hallBean);
		executeOnLoadFinish();
//		HttpClient.get(ApiStores.banner, new HttpCallback<ResponseHallBean>() {//ResponseHallBean
//			@Override
//			public void OnSuccess(ResponseHallBean response) {
//				if(response.getResult()){
//
//					if(response.getContent().getIndex_icon().size() > 0){
//						Glide.with(getMContext()).load( response.getContent().getIndex_icon().get(0).getI_img()).placeholder(R.mipmap.station_pic).into(m_ivMore);
//						m_tvMore.setText(response.getContent().getIndex_icon().get(0).getI_title());
//						m_strMoreUrl = response.getContent().getIndex_icon().get(0).getI_url();
//						m_llMore.setVisibility(View.VISIBLE);
//					}else{
//						m_strMoreUrl = "";
//						m_llMore.setVisibility(View.INVISIBLE);
//					}
//
//					List<ResponseHallBean> responseFragmentHallBeen = new ArrayList<>();
//					responseFragmentHallBeen.add(response);
//					executeOnLoadDataSuccess(responseFragmentHallBeen);
//
//				}
//			}
//
//			@Override
//			public void OnFailure(String message) {
//				executeOnLoadDataError(null);
//			}
//
//			@Override
//			public void OnRequestStart() {
//			}
//
//			@Override
//			public void OnRequestFinish() {
//				executeOnLoadFinish();
//			}
//		});
	}
}