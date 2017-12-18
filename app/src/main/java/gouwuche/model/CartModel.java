package gouwuche.model;

import android.util.Log;

import java.io.IOException;

import gouwuche.net.CommonUtils;
import gouwuche.net.OkHttp3Util;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by dell on 2017/12/16.
 */

public class CartModel{
    private ICartModel iCartModel;

    public CartModel(ICartModel iCartModel) {
        this.iCartModel = iCartModel;
    }

    public void getCartData(final String cartUrl) {
        //获取数据
        OkHttp3Util.doGet(cartUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(cartUrl,e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String json=response.body().string();
                    //返回数据到主线程
                    CommonUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            iCartModel.getSuccessCartJson(json);
                        }
                    });
                }
            }
        });
    }
}