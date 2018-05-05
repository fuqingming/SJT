package com.ylzhsj.sjt.bean.response;

import com.ylzhsj.sjt.bean.base.HallBean;
import com.ylzhsj.sjt.bean.base.MoneyMakingHallBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseMoneyMakingHallBean extends ResponseBaseBean {

    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        private List<MoneyMakingHallBean> moneyMakingHallBeanList;

        public List<MoneyMakingHallBean> getMoneyMakingHallBeanList() {
            return moneyMakingHallBeanList;
        }

        public void setMoneyMakingHallBeanList(List<MoneyMakingHallBean> moneyMakingHallBeanList) {
            this.moneyMakingHallBeanList = moneyMakingHallBeanList;
        }
    }


}
