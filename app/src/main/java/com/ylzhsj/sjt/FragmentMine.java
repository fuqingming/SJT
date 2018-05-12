package com.ylzhsj.sjt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylzhsj.library.base.BaseFragment;
import com.ylzhsj.library.settings.AppSettings;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.sjt.adapter.ModuleSelectionAdapter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 *
 * */
public class FragmentMine extends BaseFragment {

	@BindView(R.id.iv_icon)
	ImageView m_ivIcon;
	@BindView(R.id.tv_name)
	TextView m_tvName;

	@BindView(R.id.iv_title_right)
	ImageView m_ivRight;
	@BindView(R.id.gridview_functions)
	GridView m_gridView;
	private List<Map<String, Object>> m_listData;
	private ModuleSelectionAdapter m_adapter;

	private int[] m_arrIcon = { R.mipmap.wodexuanshang, R.mipmap.wodepinglun, R.mipmap.wodexiaoxi,
								R.mipmap.wodeqianbao, R.mipmap.zhuanshuerweima, R.mipmap.tuijiandehaoyou,
								R.mipmap.chuangyejiameng, R.mipmap.wodekehu,0};

	private String[] m_arrText = { "我的悬赏", "我的评论", "我的消息",
								   "我的钱包", "专属二维码", "推荐的好友",
								   "创业加盟", "我的客户","" };

	private enum FunctionIndex{MY_OFFER_A_REWARD , MY_COMMENT, MY_MESSAGE ,
							   MY_WALLET , MY_EWM , MY_RECOMMEND,
							   JOIN_IN , MY_CUSTOMER,NOTHING
	}

	@Override
	protected int setLayoutResourceId() {
		return R.layout.fragment_mine;
	}

	@Override
	public void init() {
		super.init();
		Utils.initCommonTitle(getContentView(),"我的");
		EventBus.getDefault().register(this);
		m_ivRight.setImageResource(R.mipmap.icon_setting);
	}

	@Override
	protected void setUpView() {
		super.setUpView();
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
					case MY_OFFER_A_REWARD:
					{
						it = new Intent(getMContext(),MyRewardActivity.class);
						startActivity(it);
					}
					break;

					case MY_COMMENT:
					{
						it = new Intent(getMContext(),MyCommentActivity.class);
						startActivity(it);
					}
					break;

					case MY_MESSAGE:
					{
						it = new Intent(getMContext(),MyMessageActivity.class);
						startActivity(it);
					}
					break;

					case MY_WALLET:
					{
						it = new Intent(getMContext(),MyWalletActivity.class);
						startActivity(it);
					}
					break;

					case MY_EWM:
					{
						it = new Intent(getMContext(),MyEwmActivity.class);
						startActivity(it);
					}
					break;

					case MY_RECOMMEND:
					{
						it = new Intent(getMContext(),MyRecommentActivity.class);
						startActivity(it);
					}
					break;

					case JOIN_IN:
					{
						it = new Intent(getMContext(),MyJoinInActivity.class);
						startActivity(it);
					}
					break;

					case MY_CUSTOMER:
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

	@OnClick({R.id.iv_title_right})
	public void onViewClick(View view){
		switch (view.getId()){
			case R.id.iv_title_right:
				if(!AppSettings.isAutoLogin()){
					Intent it = new Intent(getMContext(),LoginActivity.class);
					startActivity(it);
					return;
				}
				Intent it = new Intent(getMContext(),SetActivity.class);
				startActivity(it);
				break;
		}
	}

	@Override
	public void onResume() {
		super.onResume();
//		AsyncImageLoader.getInstace(getMContext()).loadBitmap(m_ivIcon,AppSettings.getHeadPic());
		if(AppSettings.isAutoLogin()){
//			AsyncImageLoader.getInstace(getMContext()).loadBitmap(m_ivIcon, AppSettings.getHeadPic(), R.mipmap.head_s);
//			Glide.with(getMContext()).load( AppSettings.getHeadPic())
//					.placeholder(R.drawable.place_holder)
//					.transform(new CustomTransform(getMContext(), ScaleType.FIT_XY))
//					.into(m_ivIcon);
			Glide.with(getMContext()).load(AppSettings.getHeadPic()).placeholder(R.mipmap.head_s).into(m_ivIcon);
			final List<String> images = new ArrayList<>();
			images.add(AppSettings.getHeadPic());
			m_ivIcon.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
				Utils.enlargeImage(getMContext(),m_ivIcon,images,0);
				}
			});

			m_tvName.setText(AppSettings.getNickname());
		}else{
			m_tvName.setText("立即登陆");
			m_ivIcon.setImageResource(R.mipmap.head_s);
		}
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEventBus(Bitmap bitmap){
		m_ivIcon.setImageBitmap(bitmap);
	}
}