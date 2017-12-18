package com.bwie.shopcar.view;

import com.bwie.shopcar.bean.GoosBean;

import java.util.List;

/**
 * author:Created by XiaoYu
 * on 2017/12/16.
 */

public interface IMainActivity {
    public void showList(List<GoosBean.DataBean> groupList, List<List<GoosBean.DataBean.DatasBean>> childList);
}
