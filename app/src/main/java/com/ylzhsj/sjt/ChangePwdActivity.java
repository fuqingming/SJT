package com.ylzhsj.sjt;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.data.Const;
import com.ylzhsj.library.http.ApiStores;
import com.ylzhsj.library.http.HttpCallback;
import com.ylzhsj.library.http.HttpClient;
import com.ylzhsj.library.settings.AppSettings;
import com.ylzhsj.library.util.MD5;
import com.ylzhsj.library.util.RegexUtil;
import com.ylzhsj.library.util.Utils;
import com.ylzhsj.library.util.alert.AlertUtils;
import com.ylzhsj.sjt.bean.response.ResponseBaseBean;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePwdActivity extends BaseAppCompatActivity {

    @BindView(R.id.et_pwd_bellow)
    EditText m_etPwdBellow;
    @BindView(R.id.et_pwd_new)
    EditText m_etPwdNew;
    @BindView(R.id.et_pwd_again)
    EditText m_etPwdAgain;

    private String m_strMd5Pwd;
    private String m_strPasswordBellow;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_change_pwd;
    }

    protected void init(){

    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"修改密码",true);

    }

    // 检查输入项是否输入正确
    private boolean isInputValid() {

        String strPasswordBellow = m_etPwdBellow.getText().toString().trim();
        if(strPasswordBellow.isEmpty())
        {
            Utils.showToast(this, "请输入原密码");
            m_etPwdBellow.requestFocus();
            return false;
        }else if(strPasswordBellow.length() < Const.FieldRange.PASSWORD_MIN_LEN){
            Utils.showToast(this,"密码不能少于6位");
            m_etPwdBellow.requestFocus();
            return false;
        }

        String m_strPasswordNew = m_etPwdNew.getText().toString().trim();
        if(m_strPasswordNew.isEmpty())
        {
            Utils.showToast(this, "请输入新密码");
            m_etPwdNew.requestFocus();
            return false;
        }else if(m_strPasswordNew.length() < Const.FieldRange.PASSWORD_MIN_LEN){
            Utils.showToast(this,"新密码不能少于6位");
            m_etPwdNew.requestFocus();
            return false;
        }else if(!RegexUtil.checkPassword(m_strPasswordNew)){
            Utils.showToast(this,"输入6～18位数字字母组合");
            m_etPwdNew.requestFocus();
            return false;
        }

        String m_strPasswordAgain = m_etPwdAgain.getText().toString().trim();
        if(m_strPasswordAgain.isEmpty())
        {
            Utils.showToast(this, "请输入确认密码");
            m_etPwdAgain.requestFocus();
            return false;
        }else if(m_strPasswordAgain.length() < Const.FieldRange.PASSWORD_MIN_LEN){
            Utils.showToast(this,"确认密码不能少于6位");
            m_etPwdAgain.requestFocus();
            return false;
        }else if(!RegexUtil.checkPassword(m_strPasswordAgain)){
            Utils.showToast(this,"输入6～18位数字字母组合");
            m_etPwdAgain.requestFocus();
            return false;
        }

        if(!m_strPasswordNew.equals(m_strPasswordAgain)){
            Utils.showToast(this,"新密码与确认密码不一致");
            m_etPwdNew.requestFocus();
            return false;
        }

        if(strPasswordBellow.equals(m_strPasswordAgain)){
            Utils.showToast(this,"原密码与新密码一致");
            m_etPwdNew.requestFocus();
            return false;
        }

        m_strMd5Pwd = MD5.encode(m_strPasswordAgain);
        m_strPasswordBellow = MD5.encode(strPasswordBellow);
        return true;
    }

    @OnClick({R.id.tv_commit})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_commit:
                if(isInputValid()){
                    callHttpForChangePwd();
                }
                break;

        }
    }

    private void callHttpForChangePwd(){
        String m_strPhone = AppSettings.getPhone();
        HttpClient.get(ApiStores.changePwd ,ApiStores.changePwd(m_strPhone,m_strMd5Pwd,m_strPasswordBellow), new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                Log.d("",response.toString());

                if(response.getResult()){
                    Utils.showToast(ChangePwdActivity.this,"成功");
                }else{
                    Utils.showToast(ChangePwdActivity.this,response.getMessage());
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
