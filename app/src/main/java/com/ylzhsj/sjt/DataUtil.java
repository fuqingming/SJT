package com.ylzhsj.sjt;

import com.ylzhsj.sjt.bean.base.MyCommentBean;
import com.ylzhsj.sjt.bean.base.MyRecommentBean;

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
}
