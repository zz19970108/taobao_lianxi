package com.bwie.shopcar.presenter;

import com.bwie.shopcar.bean.GoosBean;
import com.bwie.shopcar.model.MainModel;
import com.bwie.shopcar.net.OnNetListener;
import com.bwie.shopcar.view.IMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by XiaoYu
 * on 2017/12/16.
 */

public class MainPresenter {

    private final MainModel imainModel;
    private final IMainActivity iMainActivity;

    public MainPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        imainModel = new MainModel();
    }


    public void getGoods() {
        imainModel.getGoods(new OnNetListener<GoosBean>() {
            @Override
            public void onSuccess(GoosBean goosBean) {
                //List<GoosBean.DataBean> groupList, List<List<GoosBean.DataBean.DatasBean>> childList
                List<GoosBean.DataBean> dataBean = goosBean.getData();
                List<List<GoosBean.DataBean.DatasBean>> childList = new ArrayList<List<GoosBean.DataBean.DatasBean>>();
                for (int i = 0; i < dataBean.size(); i++) {
                    List<GoosBean.DataBean.DatasBean> datas = dataBean.get(i).getDatas();
                    childList.add(datas);
                }
                iMainActivity.showList(dataBean, childList);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
}
