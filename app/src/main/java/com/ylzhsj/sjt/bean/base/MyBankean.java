package com.ylzhsj.sjt.bean.base;

import java.io.Serializable;

/**
 * Created by vip on 2018/5/2.
 */

public class MyBankean implements Serializable{
    private String bankName;
    private String bankType;
    private String bank;

    public MyBankean(String bankName, String bankType, String bank) {
        this.bankName = bankName;
        this.bankType = bankType;
        this.bank = bank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
