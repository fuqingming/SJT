package com.ylzhsj.sjt;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ylzhsj.library.base.BaseFragment;
import com.ylzhsj.library.settings.AppSettings;
import com.ylzhsj.library.view.error.ErrorLayout;
import com.ylzhsj.sjt.adapter.FragmentHallAdapter;
import com.ylzhsj.sjt.adapter.ModuleSelectionAdapter;
import com.ylzhsj.sjt.bean.base.HallBean;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.ylzhsj.library.base.BaseListFragment;
import com.ylzhsj.library.util.BaseRecyclerAdapter;
import com.ylzhsj.library.util.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 *
 *
 * */
public class FragmentHall extends BaseFragment {

	@BindView(R.id.gridview_functions)
	GridView m_gridView;
	private List<Map<String, Object>> m_listData;
	private ModuleSelectionAdapter m_adapter;

	private int[] m_arrIcon = { R.mipmap.zhuangxiu, R.mipmap.maijiancai, R.mipmap.jianfei,
			R.mipmap.shejishichuangye, R.mipmap.xiangmujinglichuangye, R.mipmap.jieyan,
			R.mipmap.jiejiu, R.mipmap.jiedu, R.mipmap.zixunyujianyi };

	private String[] m_arrText = { "装修量房", "买建材", "减肥",
								   "设计师创业", "项目经理创业", "戒烟",
								   "戒酒", "戒赌", "资讯与建议" };

	private enum FunctionIndex{RENOVATION, BUILDING, REDUCE_WEIGHT ,
							   DESIGNER_ENTREPRENEURSHIP , MANAGER_ENTREPRENEURSHIP , QUIT_SMOKING,
							   QUIT_DRINKING , GIVE_UP_GAMBLING , INFORMATION
	}

	@Override
	protected int setLayoutResourceId() {
		return R.layout.fragment_hall;
	}

	@Override
	public void init() {
		super.init();
		Utils.initCommonTitle(getContentView(),"赏金堂");
	}

	@Override
	protected void setUpView() {
		// 换算gridview中item高度
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int nWidth = (dm.widthPixels - 2)/3;

		// 获取数据
		m_listData = getData(m_arrIcon, m_arrText);
		m_adapter = new ModuleSelectionAdapter(getMContext(), m_listData, nWidth);

		m_gridView.setAdapter(m_adapter);

		m_gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

		m_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				if(!AppSettings.isAutoLogin()){
					Intent it = new Intent(getMContext(),LoginActivity.class);
					startActivity(it);
					return;
				}
				Intent it ;
				switch (FunctionIndex.values()[position])
				{
					case RENOVATION:
					{
						it = new Intent(getMContext(),MoneyMakingHallActivity.class);
						startActivity(it);
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

	private List<Map<String, Object>> getData(int[] arrIcon, String[] arrText){
		ArrayList<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();

		for(int i = 0; i < arrIcon.length; i ++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("icon", arrIcon[i]);
			map.put("text", arrText[i]);
			listData.add(map);
		}

		return listData;
	}
}