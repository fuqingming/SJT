package com.ylzhsj.library.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HH
 * Date: 2017/11/21
 */

public class ApiStores {

    static final String urlVersion = "index/User/";


    /** 修改密码 */
    public static final String changePwd = urlVersion + "changePwd2";
    public static Map<String,Object> changePwd(String m_strPhone,String m_strMd5Pwd,String m_strPasswordBellow){
        Map<String,Object> map = new HashMap<>();
        map.put("u_telphone",m_strPhone);
        map.put("u_x_pwd",m_strMd5Pwd);
        map.put("u_y_pwd",m_strPasswordBellow);
        return map;
    }

    /** 发送验证码 */
    public static final String user_send_yzm = urlVersion + "user_send_yzm";
    public static Map<String,Object> user_send_yzm(String userPhone){
        Map<String,Object> map = new HashMap<>();
        map.put("u_telphone",userPhone);
        return map;
    }


}
