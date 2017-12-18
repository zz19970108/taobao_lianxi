package com.bwie.shopcar.model;

import android.os.Handler;
import android.os.Looper;

import com.bwie.shopcar.bean.GoosBean;
import com.bwie.shopcar.net.Api;
import com.bwie.shopcar.net.HttpUtils;
import com.bwie.shopcar.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by XiaoYu
 * on 2017/12/16.
 */

public class MainModel implements IMainModedl{
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void getGoods( final OnNetListener<GoosBean> onNetListener) {
        HttpUtils.getHttpUtils().doGet(Api.url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final GoosBean goosBean = new Gson().fromJson(string, GoosBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(goosBean);
                    }
                });
            }
        });
    }
}
