package com.ylzhsj.sjt;

import com.ylzhsj.sjt.bean.base.ExtarctHistoryBean;
import com.ylzhsj.sjt.bean.base.MyBankean;
import com.ylzhsj.sjt.bean.base.MyCommentBean;
import com.ylzhsj.sjt.bean.base.MyMessageBean;
import com.ylzhsj.sjt.bean.base.MyRecommentBean;
import com.ylzhsj.sjt.bean.base.MyRewardBean;
import com.ylzhsj.sjt.bean.base.MyWalletBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/4/9.
 */

public class DataUtil {



    public static List<MyCommentBean> initMyComment(){
        List<MyCommentBean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new MyCommentBean(R.mipmap.head_s,"戒烟-孙杰戒烟","2018-02-05","戒烟了吗"));
        videoPlayBackBeans.add(new MyCommentBean(R.mipmap.head_s,"戒烟-孙杰戒烟","2018-02-05","结果如何"));
        videoPlayBackBeans.add(new MyCommentBean(R.mipmap.head_s,"戒烟-孙杰戒烟","2018-02-05","谈股论期谈股论期谈股论期谈股论期谈股论期谈股论期谈股论期谈股论期谈股论期谈股论期谈股论期"));
        videoPlayBackBeans.add(new MyCommentBean(R.mipmap.head_s,"戒烟-孙杰戒烟","2018-02-05","谈股论期"));
        videoPlayBackBeans.add(new MyCommentBean(R.mipmap.head_s,"戒烟-孙杰戒烟","2018-02-05","谈股论期"));
        return videoPlayBackBeans;
    }

    public static List<MyRecommentBean> initMyRecomment(){
        List<MyRecommentBean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new MyRecommentBean(R.mipmap.head_s,"孙杰","13386174433"));
        videoPlayBackBeans.add(new MyRecommentBean(R.mipmap.head_s,"孙杰","18516549999"));
        return videoPlayBackBeans;
    }

    public static List<MyMessageBean> initMyMessage(){
        List<MyMessageBean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new MyMessageBean("2018年4月17日 10时20分","减肥","审核通过",false,false));
        videoPlayBackBeans.add(new MyMessageBean("2018年4月17日 10时20分","减肥","已有用户接受您发布的任务",false,false));
        return videoPlayBackBeans;
    }
    public static List<MyRewardBean> initMyReward(){
        List<MyRewardBean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new MyRewardBean(R.mipmap.head_s,"孙杰","上海浦东新区","1000","2018-02-05 13:13","[装修量房]-家庭装修量房招标","婚房装修，预算有限"));
        videoPlayBackBeans.add(new MyRewardBean(R.mipmap.head_s,"孙杰","上海浦东新区","1000","2018-02-05 13:13","[装修量房]-家庭装修量房招标","婚房装修，预算有限"));
        videoPlayBackBeans.add(new MyRewardBean(R.mipmap.head_s,"孙杰","上海浦东新区","1000","2018-02-05 13:13","[装修量房]-家庭装修量房招标","婚房装修，预算有限"));
        return videoPlayBackBeans;
    }
    public static List<MyWalletBean> initMyWallet(){
        List<MyWalletBean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new MyWalletBean("悬赏保证金","2018-04-02","+10.00"));
        videoPlayBackBeans.add(new MyWalletBean("悬赏保证金","2018-04-02","+10.00"));
        videoPlayBackBeans.add(new MyWalletBean("悬赏保证金","2018-04-02","+10.00"));
        videoPlayBackBeans.add(new MyWalletBean("悬赏保证金","2018-04-02","+10.00"));
        videoPlayBackBeans.add(new MyWalletBean("悬赏保证金","2018-04-02","+10.00"));
        videoPlayBackBeans.add(new MyWalletBean("悬赏保证金","2018-04-02","+10.00"));
        videoPlayBackBeans.add(new MyWalletBean("悬赏保证金","2018-04-02","+10.00"));
        videoPlayBackBeans.add(new MyWalletBean("悬赏保证金","2018-04-02","+10.00"));

        return videoPlayBackBeans;
    }
    public static List<MyBankean> initMyBank(){
        List<MyBankean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new MyBankean("工商银行","储蓄卡","**** **** **** 5566"));
        videoPlayBackBeans.add(new MyBankean("工商银行","储蓄卡","**** **** **** 5566"));
        videoPlayBackBeans.add(new MyBankean("工商银行","储蓄卡","**** **** **** 5566"));

        return videoPlayBackBeans;
    }
    public static List<ExtarctHistoryBean> initExtractHistory(){
        List<ExtarctHistoryBean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));
        videoPlayBackBeans.add(new ExtarctHistoryBean("-100.00","2018-04-18","成功","浦发银行（尾号9988）"));

        return videoPlayBackBeans;
    }
}
