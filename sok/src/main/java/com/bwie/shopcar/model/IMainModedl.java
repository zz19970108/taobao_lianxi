package com.bwie.shopcar.model;

import com.bwie.shopcar.bean.GoosBean;
import com.bwie.shopcar.net.OnNetListener;

/**
 * author:Created by XiaoYu
 * on 2017/12/16.
 */

public interface IMainModedl {
    public void getGoods(OnNetListener<GoosBean> onNetListener);

}
