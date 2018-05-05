package com.ylzhsj.sjt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylzhsj.library.backhandler.OnTaskSuccessComplete;
import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.cache.AsyncImageLoader;
import com.ylzhsj.library.settings.AppSettings;
import com.ylzhsj.library.util.CleanMessageUtil;
import com.ylzhsj.library.util.DirSettings;
import com.ylzhsj.library.util.FileUtil;
import com.ylzhsj.library.util.Upload;
import com.ylzhsj.library.util.Utils;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "SetActivity";

    private static final int REQUEST_LIST_CODE = 0;

    @BindView(R.id.tv_nickname)
    TextView m_tvNickname;
    @BindView(R.id.iv_icon)
    ImageView m_ivIcon;
    @BindView(R.id.tv_phone)
    TextView m_tvPhone;
    @BindView(R.id.tv_clean)
    TextView m_tvClean;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_set;
    }

    @Override
    protected void setUpView() {
        ButterKnife.bind(this);
        Utils.initCommonTitle(this,"个人设置",true);
        AsyncImageLoader.getInstace(this).loadBitmap(m_ivIcon, AppSettings.getHeadPic(), R.mipmap.head_s);
        String strPhone = AppSettings.getPhone().substring(0, 3) + "****" + AppSettings.getPhone().substring(7, 11);
        m_tvPhone.setText(strPhone);
    }

    @OnClick({R.id.ll_icon,R.id.ll_nickname,R.id.btn_logout,R.id.ll_change_pwd,R.id.ll_clean,R.id.ll_about})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.ll_icon:
                openRadio();
                break;
            case R.id.ll_nickname:
                Utils.showCommonDialogChangePwd(SetActivity.this,AppSettings.getNickname(),new OnTaskSuccessComplete()
                {
                    @Override
                    public void onSuccess(Object obj)
                    {
                        Utils.showToast(SetActivity.this,obj.toString());
                    }
                });
                break;

            case R.id.ll_change_pwd:
                Intent intent = new Intent(SetActivity.this,ChangePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_clean:
                Utils.showDialogClean(SetActivity.this,m_tvClean);
                break;
            case R.id.ll_about:

                break;
            case R.id.btn_logout:
                logout();
                break;
        }
    }

    private void logout() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        m_tvNickname.setText(AppSettings.getNickname());
        String lenth = CleanMessageUtil.getTotalCacheSize(SetActivity.this);
        m_tvClean.setText(lenth);
    }

    /**
     * 自定义单选
     */
    private void openRadio() {
        ISListConfig config = new ISListConfig.Builder()
                // 是否多选
                .multiSelect(false)
                .btnText("Confirm")
                // 确定按钮背景色
                //.btnBgColor(Color.parseColor(""))
                // 确定按钮文字颜色
                .btnTextColor(Color.WHITE)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#3F51B5"))
                // 返回图标ResId
                .title("Images")
                .titleColor(Color.WHITE)
                .titleBgColor(Color.parseColor("#3F51B5"))
                .allImagesText("All Images")
                .needCrop(true)
                .cropSize(1, 1, 200, 200)
                // 第一个是否显示相机
                .needCamera(true)
                // 最大选择图片数量
                .maxNum(9)
                .build();

        ISNav.getInstance().toListActivity(this, config, REQUEST_LIST_CODE);
    }

//    private ResponseChangeHeadBean transform(String response){
//        JSONObject jsonObject = null;
//        ResponseChangeHeadBean responseChangeHeadBean = new ResponseChangeHeadBean();
//        try {
//            jsonObject = new JSONObject(response);
//            boolean result = jsonObject.getBoolean("result");
//            String message = jsonObject.getString("message");
//            int code = jsonObject.getInt("code");
//            String content = jsonObject.getString("content");
//
//            JSONObject jsonObjectContent = new JSONObject(content);
//            String u_photo = jsonObjectContent.getString("u_photo");
//
//            responseChangeHeadBean.setCode(code);
//            responseChangeHeadBean.setResult(result);
//            responseChangeHeadBean.setMessage(message);
//            responseChangeHeadBean.setU_photo(u_photo);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return responseChangeHeadBean;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LIST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");
            Utils.showToast(this,pathList.get(0));
//            kProgressHUD.show();
//            String strUrl = "http://gxt.mqcll.cn/index/User/changePhoto&u_id="+ AppSettings.getUserId();
//            final String path = pathList.get(0);
//            File image = new File(path);
//            new Upload(image,SetActivity.this,kProgressHUD,new OnTaskSuccessComplete()
//            {
//                @Override
//                public void onSuccess(Object obj)
//                {
//                                ResponseChangeHeadBean responseChangeHeadBean = transform((String) obj);
//                                if(responseChangeHeadBean.getResult()){
//                                    Toast.makeText(MineActivity.this,responseChangeHeadBean.getMessage(),Toast.LENGTH_SHORT).show();
//                                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserIcon,responseChangeHeadBean.getU_photo());
//                                    m_ivIcon.setImageBitmap(finalBitmap);
//                                    EventBus.getDefault().post(finalBitmap);
//                                }
//                }
//            }).execute(strUrl);
        }
    }
}
