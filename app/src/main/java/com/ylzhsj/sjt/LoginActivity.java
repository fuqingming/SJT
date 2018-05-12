package com.ylzhsj.sjt;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.data.Const;
import com.ylzhsj.library.http.ApiStores;
import com.ylzhsj.library.http.HttpCallback;
import com.ylzhsj.library.http.HttpClient;
import com.ylzhsj.library.util.MD5;
import com.ylzhsj.library.util.RegexUtil;
import com.ylzhsj.library.util.SmsSendCounter;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.library.util.alert.AlertUtils;
import com.ylzhsj.sjt.bean.response.ResponseBaseBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录界面
 * @author 付庆明
 *
 */
public class LoginActivity extends BaseAppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int RESEND_VERIFY_CODE_SECOND = 60;
    private SmsSendCounter m_myCount = null;

    @BindView(R.id.et_phone)
    EditText m_etPhone;
    @BindView(R.id.et_password)
    EditText m_etPassword;
    @BindView(R.id.et_verify_number)
    EditText m_etVerifyNumber;
    @BindView(R.id.tv_send_verify_code)
    TextView m_tvSendVerifyCode;
    @BindView(R.id.tv_login_type)
    TextView m_tvLoginType;
    @BindView(R.id.rl_login_yzm)
    RelativeLayout m_rlLoginYzm;
    @BindView(R.id.cb_see_pwd)
    CheckBox m_cbSeePwd;
    @BindView(R.id.rl_pwd)
    RelativeLayout m_rlPwd;
    private String m_strPhone;
    private String m_strMd5Pwd;
    private String m_strPhoneSend = "";
    private String m_strVerifyNumber;
    private boolean m_isLoginType = false;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setUpView() {
        ButterKnife.bind(this);
        Utils.initCommonTitle(this,"会员登录",true);
        m_etPhone.requestFocus();

        m_cbSeePwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    m_etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    m_etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                m_etPassword.setSelection(m_etPassword.getText().length());
            }
        });
    }

    @OnClick({R.id.tv_login,R.id.tv_fast_register,R.id.tv_forget_password,R.id.tv_login_type,R.id.tv_send_verify_code})
    public void onViewClick(View view){
        Intent it;
        switch (view.getId()){
            case R.id.tv_login:
                if(isInputValid()){
                    if(m_isLoginType){
                        callHttpForSendYzm(m_strPhone);
                    }else{
                        callHttpForLogin(m_strPhone,m_strMd5Pwd);
                    }
                }

                break;
            case R.id.tv_fast_register:
                it = new Intent(LoginActivity.this,RegisterOneActivity.class);
                startActivity(it);
                break;
            case R.id.tv_forget_password:
                it = new Intent(LoginActivity.this,ForgetPwdOneActivity.class);
                startActivity(it);
                break;
            case R.id.tv_login_type:
                if(m_isLoginType){
                    m_isLoginType = false;
                    m_tvLoginType.setText("短信登陆");
                    m_rlLoginYzm.setVisibility(View.GONE);
                    m_rlPwd.setVisibility(View.VISIBLE);
                }else{
                    m_isLoginType = true;
                    m_tvLoginType.setText("密码登陆");
                    m_rlLoginYzm.setVisibility(View.VISIBLE);
                    m_rlPwd.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_send_verify_code:
                if(isPhoneValid()){
                    callHttpForSendYzm(m_strPhone);
                }
                break;
        }
    }

    private boolean isPhoneValid(){
        m_strPhone = m_etPhone.getText().toString().trim();
        if(m_strPhone.isEmpty())
        {
            m_etPhone.requestFocus();
            return false;
        }
        else if(m_strPhone.length() < 11)
        {
            m_etPhone.requestFocus();
            return false;
        }
        else if(!RegexUtil.checkMobile(m_strPhone))
        {
            m_etPhone.requestFocus();
            return false;
        }
        return true;
    }

    // 检查输入项是否输入正确
    private boolean isInputValid() {

        m_strPhone = m_etPhone.getText().toString().trim();
        if(m_strPhone.isEmpty())
        {
            Utils.showToast(this, "请输入手机号码");
            m_etPhone.requestFocus();
            return false;
        }
        else if(m_strPhone.length() < 11)
        {
            Utils.showToast(this, "手机号码需要11位长度");
            m_etPhone.requestFocus();
            return false;
        }
        else if(!RegexUtil.checkMobile(m_strPhone))
        {
            Utils.showToast(this, "请输入正确的手机号码");
            m_etPhone.requestFocus();
            return false;
        }

        if(m_isLoginType){
            if("".equals(m_strPhoneSend)){
                Utils.showToast(this,"请获取验证码！");
                return false;
            }

            // 验证码
            m_strVerifyNumber = m_etVerifyNumber.getText().toString().trim();
            if(m_strVerifyNumber.isEmpty())
            {
                Utils.showToast(this, "请输入验证码");
                m_etVerifyNumber.requestFocus();
                return false;
            }
            else if(m_strVerifyNumber.length() < 4)
            {
                Utils.showToast(this, "验证码为4位");
                m_etVerifyNumber.requestFocus();
                return false;
            }
        }else{
            String m_strPassword = m_etPassword.getText().toString().trim();
            if(m_strPassword.isEmpty())
            {
                Utils.showToast(this, "请输入密码");
                m_etPassword.requestFocus();
                return false;
            }else if(m_strPassword.length() < Const.FieldRange.PASSWORD_MIN_LEN){
                Utils.showToast(this,"密码不能少于6位");
                m_etPassword.requestFocus();
                return false;
            }else if(!RegexUtil.checkPassword(m_strPassword)){
                Utils.showToast(this,"输入6～18位数字字母组合");
                m_etPassword.requestFocus();
                return false;
            }
            m_strMd5Pwd = MD5.encode(m_strPassword);
        }

        return true;
    }

    private void callHttpForLogin(String userPhone,String strPassword){

//        String urlDataString = "?u_telphone="+userPhone+"&pwd="+strPassword;
//        HttpClient.get(ApiStores.user_send_login + urlDataString, new HttpCallback<ResponseLoginBean>() {
//            @Override
//            public void OnSuccess(ResponseLoginBean response) {
//                Log.d("",response.toString());
//                if(response.getResult()){
//                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserId, response.getContent().getInfo().getU_id());
//                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserIcon, response.getContent().getInfo().getU_photo());
//                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserTelphone, response.getContent().getInfo().getU_telphone());
//                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserNickame, response.getContent().getInfo().getU_name());
//                    login(response.getContent().getInfo().getU_pwd());
//                }else{
//                    Utils.showToast(LoginActivity.this,response.getMessage());
//                    kProgressHUD.dismiss();
//                }
//            }
//
//            @Override
//            public void OnFailure(String message) {
//                messageCenter("错误",message);
//                kProgressHUD.dismiss();
//            }
//
//            @Override
//            public void OnRequestStart() {
//                kProgressHUD.show();
//            }
//
//            @Override
//            public void OnRequestFinish() {
//
//            }
//        });
    }

    private void callHttpForSendYzm(String userPhone){
        String urlDataString = "?u_telphone="+userPhone+"&type=1";
        HttpClient.get(ApiStores.user_send_yzm + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                if(response.getResult()){
                    m_tvSendVerifyCode.setEnabled(false);
                    m_tvSendVerifyCode.setText(String.valueOf(RESEND_VERIFY_CODE_SECOND));
                    m_myCount = new SmsSendCounter(LoginActivity.this,m_tvSendVerifyCode, RESEND_VERIFY_CODE_SECOND * 1000, 1000);
                    m_myCount.start();

                    m_strPhoneSend = m_strPhone;
                }else{
                    Utils.showToast(LoginActivity.this,response.getMessage());
                }
            }

            @Override
            public void OnFailure(String message) {
                messageCenter("错误",message);
            }

            @Override
            public void OnRequestStart() {
                kProgressHUD.show();
            }

            @Override
            public void OnRequestFinish() {
                kProgressHUD.dismiss();
            }
        });
    }

    private void messageCenter(String title,String message){
        AlertUtils.MessageAlertShow(this, title, message);
    }

    private void stopMyCount()
    {
        if (m_myCount != null)
        {
            m_myCount.cancel();
            m_myCount = null;
        }

        m_tvSendVerifyCode.setEnabled(true);
        m_tvSendVerifyCode.setText("发送验证码");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMyCount();
    }
}
