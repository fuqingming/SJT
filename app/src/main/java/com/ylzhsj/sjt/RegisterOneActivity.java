package com.ylzhsj.sjt;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.http.ApiStores;
import com.ylzhsj.library.http.HttpCallback;
import com.ylzhsj.library.http.HttpClient;
import com.ylzhsj.library.util.RegexUtil;
import com.ylzhsj.library.util.SmsSendCounter;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.library.util.alert.AlertUtils;
import com.ylzhsj.sjt.bean.response.ResponseBaseBean;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterOneActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "RegisterOneActivity";
    private static final int RESEND_VERIFY_CODE_SECOND = 60;
    private SmsSendCounter m_myCount = null;

    @BindView(R.id.et_phone)
    EditText m_etPhone;
    @BindView(R.id.et_verify_number)
    EditText m_etVerifyNumber;
    @BindView(R.id.tv_send_verify_code)
    TextView m_tvSendVerifyCode;

    private String m_strPhone;
    private String m_strVerifyNumber;
    private String m_strPhoneSend = "";

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_register_one;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"注册",true);
    }

    private boolean isPhoneCode(){
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

        return true;
    }

    @OnClick({R.id.tv_send_verify_code,R.id.tv_next})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_send_verify_code:
                if(isPhoneCode()){
                    callHttpForSendYzm(m_strPhone);
                }
                break;
            case R.id.tv_next:

                if(isInputValid()){
                    if(!m_strPhoneSend.equals(m_strPhone)){
                        Utils.showToast(RegisterOneActivity.this,"手机号已改变，请重新获取验证码");
                        return;
                    }
                    callHttpForRegister(m_strPhone,m_strVerifyNumber);
                }
                break;
        }
    }

    private void callHttpForRegister(final String userPhone,String code){
        String urlDataString = "?u_telphone="+userPhone+"&u_code="+code;
        HttpClient.get(ApiStores.user_send_yzm + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                if(response.getResult()){
                    Intent it = new Intent(RegisterOneActivity.this,RegisterTwoActivity.class);
                    it.putExtra("strPhone",m_strPhone);
                    startActivity(it);
                    finish();
                }else{
                    Utils.showToast(RegisterOneActivity.this,response.getMessage());
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

    private void callHttpForSendYzm(String userPhone){
        String urlDataString = "?u_telphone="+userPhone+"&type=1";
        HttpClient.get(ApiStores.user_send_yzm + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                Log.d("",response.toString());
                if(response.getResult()){
                    m_tvSendVerifyCode.setEnabled(false);
                    m_tvSendVerifyCode.setText(String.valueOf(RESEND_VERIFY_CODE_SECOND));
                    m_myCount = new SmsSendCounter(RegisterOneActivity.this,m_tvSendVerifyCode, RESEND_VERIFY_CODE_SECOND * 1000, 1000);
                    m_myCount.start();

                    m_strPhoneSend = m_strPhone;
                }else{
                    Utils.showToast(RegisterOneActivity.this,response.getMessage());
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
