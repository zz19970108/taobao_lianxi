package bwie.com.shopcar.view;

import java.util.List;

import bwie.com.shopcar.bean.GoosBean;

/**
 * author:Created by XiaoYu
 * on 2017/12/16.
 */

public interface IMainActivity {
    public void showList(List<GoosBean.DataBean> groupList, List<List<GoosBean.DataBean.DatasBean>> childList);
}
