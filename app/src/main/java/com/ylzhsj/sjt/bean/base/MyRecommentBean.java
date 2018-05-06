package com.ylzhsj.sjt.bean.base;

import java.io.Serializable;

/**
 * Created by vip on 2018/5/2.
 */

public class MyRecommentBean implements Serializable{
    private int icon;
    private String name;
    private String phone;

    public MyRecommentBean(int icon, String name, String phone) {
        this.icon = icon;
        this.name = name;
        this.phone = phone;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
