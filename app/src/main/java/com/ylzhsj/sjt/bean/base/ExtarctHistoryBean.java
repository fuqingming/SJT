package com.ylzhsj.sjt.bean.base;

import java.io.Serializable;

/**
 * Created by vip on 2018/5/2.
 */

public class ExtarctHistoryBean implements Serializable{
    private String amount;
    private String time;
    private String result;
    private String bank;

    public ExtarctHistoryBean(String amount, String time, String result, String bank) {
        this.amount = amount;
        this.time = time;
        this.result = result;
        this.bank = bank;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
