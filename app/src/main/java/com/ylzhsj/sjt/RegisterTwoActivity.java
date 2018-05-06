package com.ylzhsj.sjt;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class RegisterTwoActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "RegisterTowActivity";

    @BindView(R.id.tv_phone)
    TextView m_tvPhone;
    @BindView(R.id.et_password)
    EditText m_etPwd;
    @BindView(R.id.et_text)
    EditText m_etNickname;
    @BindView(R.id.et_join)
    EditText m_etJoin;

    private String m_strPhone;
    private String m_strPassword;
    private String m_strNickname;
    private String m_strJoinNum;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_register_two;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"注册",true);
        m_strPhone = getIntent().getStringExtra("strPhone");
        m_tvPhone.setText("用户：" + m_strPhone);
    }

    // 检查输入项是否输入正确
    private boolean isInputValid() {
        m_strPassword = m_etPwd.getText().toString().trim();
        if(m_strPassword.isEmpty())
        {
            Utils.showToast(this, "请输入密码");
            m_etPwd.requestFocus();
            return false;
        }else if(m_strPassword.length() < Const.FieldRange.PASSWORD_MIN_LEN){
            Utils.showToast(this,"密码不能少于6位");
            m_etPwd.requestFocus();
            return false;
        }else if(!RegexUtil.checkPassword(m_strPassword)){
            Utils.showToast(this,"输入6～18位数字字母组合");
            m_etPwd.requestFocus();
            return false;
        }

        m_strNickname = m_etNickname.getText().toString().trim();
        if (m_strNickname.isEmpty()) {
            Utils.showToast(this, "昵称不能为空");
            m_etNickname.requestFocus();
            return false;
        }
        if(m_strNickname.length() < 4){
            Utils.showToast(this, "昵称不能少于4个字符");
            m_etNickname.requestFocus();
            return false;
        }

        m_strJoinNum = m_etJoin.getText().toString().trim();
        if (m_strJoinNum.isEmpty()) {
            Utils.showToast(this, "邀请码不能为空");
            m_etJoin.requestFocus();
            return false;
        }

        m_strPassword = MD5.encode(m_strPassword);

        return true;
    }

    @OnClick({R.id.tv_next})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_next:

                if(isInputValid()){
                    callHttpForRegister(m_strPhone,m_strPassword,m_strNickname,m_strJoinNum);
                }
                break;
        }
    }

    private void callHttpForRegister(final String userPhone,String strPassword,String strNickname,String strJoinNum){
        String urlDataString = "?u_telphone="+userPhone+"&strPassword="+strPassword+"&strNickname="+strNickname+"&strJoinNum="+strJoinNum;
        HttpClient.get(ApiStores.user_send_yzm + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                if(response.getResult()){

                }else{
                    Utils.showToast(RegisterTwoActivity.this,response.getMessage());
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
