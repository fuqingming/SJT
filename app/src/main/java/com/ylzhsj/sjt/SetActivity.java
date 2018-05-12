package com.ylzhsj.sjt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylzhsj.library.backhandler.OnTaskSuccessComplete;
import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.settings.AppSettings;
import com.ylzhsj.library.util.CleanMessageUtil;
import com.ylzhsj.library.util.Utils;
import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

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
        Utils.initCommonTitle(this,"个人设置",true);
        Glide.with(this).load( AppSettings.getHeadPic()).placeholder(R.mipmap.head_s).into(m_ivIcon);
        String strPhone = AppSettings.getPhone().substring(0, 3) + "****" + AppSettings.getPhone().substring(7, 11);
        m_tvPhone.setText(strPhone);
    }

    @OnClick({R.id.ll_icon,R.id.ll_nickname,R.id.btn_logout,R.id.ll_change_pwd,R.id.ll_clean,R.id.ll_about,R.id.ll_phone})
    public void onViewClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.ll_icon:
                openRadios();
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
                intent = new Intent(SetActivity.this,ChangePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_clean:
                Utils.showDialogClean(SetActivity.this,m_tvClean);
                break;
            case R.id.ll_about:
                intent = new Intent(SetActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_logout:
                Utils.showLogOutDialog(SetActivity.this,new OnTaskSuccessComplete()
                {
                    @Override
                    public void onSuccess(Object obj)
                    {
                        AppSettings.setAutoLogin(false);
                        AppSettings.setNickname("");
                        AppSettings.setPhone("");
                        AppSettings.setUserId("");
                        Utils.showToast(SetActivity.this,"已退出登陆！");
                        finish();
                    }
                });
                break;
            case R.id.ll_phone:
                intent = new Intent(SetActivity.this,ModifyPhoneActivity.class);
                startActivity(intent);
                break;
        }
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
    private void openRadios() {
        RxGalleryFinal
                .with(this)
                .image()
                .radio()
                .imageLoader(ImageLoaderType.FRESCO)
                .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
                    @SuppressLint("NewApi")
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        final String path = imageRadioResultEvent.getResult().getOriginalPath();
                        m_tvPhone.setText(path);
                        Utils.showToast(SetActivity.this,path);
                    }
                })
                .openGallery();
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
}
