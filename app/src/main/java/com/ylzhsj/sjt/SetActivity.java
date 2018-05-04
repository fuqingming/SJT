package com.ylzhsj.sjt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.SPUtils;
import com.ylzhsj.library.backhandler.OnTaskSuccessComplete;
import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.cache.AsyncImageLoader;
import com.ylzhsj.library.settings.AppSettings;
import com.ylzhsj.library.util.CleanMessageUtil;
import com.ylzhsj.library.util.DirSettings;
import com.ylzhsj.library.util.FileUtil;
import com.ylzhsj.library.util.Upload;
import com.ylzhsj.library.util.Utils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

public class SetActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "SetActivity";

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
        RxGalleryFinal
                .with(this)
                .image()
                .radio()
                .imageLoader(ImageLoaderType.FRESCO)
                .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
                    @SuppressLint("NewApi")
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        kProgressHUD.show();
                        String strUrl = "http://gxt.mqcll.cn/index/User/changePhoto&u_id="+ AppSettings.getUserId();
                        final String path = imageRadioResultEvent.getResult().getOriginalPath();

                        // 压缩图片
                        Bitmap bitmap = Utils.centerSquareScaleBitmap(BitmapFactory.decodeFile(path),SetActivity.this);

                        // 如果有必要，对图片进行旋转
                        int nDegree = Utils.readPictureDegree(path);
                        if(nDegree != 0)
                        {
                            bitmap = Utils.rotateBitmap(bitmap, nDegree);
                        }

                        // 保存图片
                        FileUtil.creatDirsIfNeed(DirSettings.getAppCacheDir());
                        if(!Utils.saveBitmap(bitmap, DirSettings.getAppCacheDir(), "myself_tmp_head_pic.png"))
                        {
                            Utils.showToast(SetActivity.this, "保存图片失败");
                        }

                        File image = new File(DirSettings.getAppCacheDir()+"myself_tmp_head_pic.png");
                        final Bitmap finalBitmap = bitmap;
                        new Upload(image,SetActivity.this,kProgressHUD,new OnTaskSuccessComplete()
                        {
                            @Override
                            public void onSuccess(Object obj)
                            {
//                                ResponseChangeHeadBean responseChangeHeadBean = transform((String) obj);
//                                if(responseChangeHeadBean.getResult()){
//                                    Toast.makeText(MineActivity.this,responseChangeHeadBean.getMessage(),Toast.LENGTH_SHORT).show();
//                                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserIcon,responseChangeHeadBean.getU_photo());
//                                    m_ivIcon.setImageBitmap(finalBitmap);
//                                    EventBus.getDefault().post(finalBitmap);
//                                }
                            }
                        }).execute(strUrl);
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
