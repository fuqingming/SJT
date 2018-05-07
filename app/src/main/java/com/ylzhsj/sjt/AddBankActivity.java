package com.ylzhsj.sjt;

import android.view.View;
import android.widget.EditText;
import com.ylzhsj.library.base.BaseAppCompatActivity;
import com.ylzhsj.library.util.RegexUtil;
import com.ylzhsj.library.util.Utils;
import butterknife.BindView;
import butterknife.OnClick;

public class AddBankActivity extends BaseAppCompatActivity {

    @BindView(R.id.et_name)
    EditText m_etName;
    @BindView(R.id.et_no)
    EditText m_etNo;
    @BindView(R.id.et_bank_no)
    EditText m_etBankNo;
    @BindView(R.id.et_phone)
    EditText m_etPhone;

    private String m_strBankNo;
    private String m_strMobile;
    private String m_strCertNo;
    private String m_strUserName;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_add_bank;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"添加银行卡",true);
    }

    // 检查输入项是否输入正确
    private boolean isInputValid()
    {
        m_strUserName = m_etName.getText().toString().trim();
        if(m_strUserName.isEmpty())
        {
            Utils.showToast(this, "请输入姓名");
            m_etName.requestFocus();
            return false;
        }else if(!RegexUtil.checkRealName(m_strUserName))
        {
            Utils.showToast(this, "请输入正确的姓名");
            m_etName.requestFocus();
            return false;
        }

        m_strCertNo = m_etNo.getText().toString().trim();
        if(m_strCertNo.isEmpty())
        {
            Utils.showToast(this, "请输入身份证号码");
            m_etNo.requestFocus();
            return false;
        }else if(!RegexUtil.checkCertNo(m_strCertNo))
        {
            Utils.showToast(this, "请输入正确的身份证号码");
            m_etNo.requestFocus();
            return false;
        }

        m_strBankNo = m_etBankNo.getText().toString().trim();
        if(m_strBankNo.isEmpty())
        {
            Utils.showToast(this, "请输入银行卡号");
            m_etBankNo.requestFocus();
            return false;
        }
        else if(!RegexUtil.checkBanckNo(m_strBankNo))
        {
            Utils.showToast(this, "请输入正确的银行卡号");
            m_etBankNo.requestFocus();
            return false;
        }

        m_strMobile = m_etPhone.getText().toString().trim();
        if(m_strMobile.isEmpty())
        {
            Utils.showToast(this, "请输入手机号码");
            m_etPhone.requestFocus();
            return false;
        }
        else if(!RegexUtil.checkMobile(m_strMobile))
        {
            Utils.showToast(this, "请输入正确的手机号码");
            m_etPhone.requestFocus();
            return false;
        }

        return true;
    }

    @OnClick({R.id.btn_next})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.btn_next:
                if(isInputValid()){

                }
                break;
        }
    }
}
