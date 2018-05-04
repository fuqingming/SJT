package com.ylzhsj.sjt;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.data.Const;
import com.ylzhsj.library.http.ApiStores;
import com.ylzhsj.library.http.HttpCallback;
import com.ylzhsj.library.http.HttpClient;
import com.ylzhsj.library.util.MD5;
import com.ylzhsj.library.util.RegexUtil;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.library.util.alert.AlertUtils;
import com.ylzhsj.sjt.bean.response.ResponseBaseBean;
import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPwdTwoActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "ForgetPwdTwoActivity";
    @BindView(R.id.tv_phone)
    TextView m_tvPhone;
    @BindView(R.id.et_password)
    EditText m_etPassword;
    @BindView(R.id.cb_see_pwd)
    CheckBox m_cbSeePwd;

    private String m_strPhone;
    private String m_strMd5Pwd;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_forget_pwd_two;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"找回密码",true);
        m_strPhone = getIntent().getStringExtra("strPhone");
        String strPhone = m_strPhone.substring(0, 3) + "****" + m_strPhone.substring(7, 11);
        m_tvPhone.setText("用户："+strPhone);

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

    // 检查输入项是否输入正确
    private boolean isInputValid() {
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

        return true;
    }

    @OnClick({R.id.tv_next})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_next:

                if(isInputValid()){
                    callHttpForRegister(m_strPhone,m_strMd5Pwd);
                }
                break;
        }
    }

    private void callHttpForRegister(final String userPhone,String code){
        String urlDataString = "?u_telphone="+userPhone+"&u_code="+code;
        HttpClient.get(ApiStores.user_register + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                if(response.getResult()){
                    finish();
                }else{
                    Utils.showToast(ForgetPwdTwoActivity.this,response.getMessage());
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
}
