package bwie.com.shopcar.model;

import bwie.com.shopcar.bean.GoosBean;
import bwie.com.shopcar.net.OnNetListener;

/**
 * author:Created by XiaoYu
 * on 2017/12/16.
 */

public interface IMainModedl {
    public void getGoods(OnNetListener<GoosBean> onNetListener);

}
