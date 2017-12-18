package fenlei.model;

import java.io.IOException;

import okhttp3.Call;
import fenlei.bean.LeftBean;
import fenlei.bean.RightBean;
import fenlei.bean.Right_zi_Bean;
import tools.RegApi;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * Created by dell on 2017/12/15.
 */

public class MyModel_fenlei implements Model_fenlei{

    OnFinsh onFinsh;

    public interface OnFinsh{
        void Finsh1(LeftBean leftBean);
        void Finsh2(RightBean rightBean);
        void Finsh3(Right_zi_Bean ziBean);
    }

    public void setOnFinsh(OnFinsh onFinsh) {
        this.onFinsh = onFinsh;
    }

    @Override
    public void getFenlei(int cid, int i) {
        if(i == 1){
            OkHttp3Utils.doGet(RegApi.Left, new GsonObjectCallback<LeftBean>() {
                @Override
                public void onUi(LeftBean leftBean) {
                    onFinsh.Finsh1(leftBean);
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });
        }
        if(i == 2){
            OkHttp3Utils.doGet(RegApi.Right+"?cid="+cid, new GsonObjectCallback<RightBean>() {
                @Override
                public void onUi(RightBean rightBean) {
                    onFinsh.Finsh2(rightBean);
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });
        }

        if(i == 3){
            OkHttp3Utils.doGet(RegApi.Right_zi + "?pscid=" + cid, new GsonObjectCallback<Right_zi_Bean>() {
                @Override
                public void onUi(Right_zi_Bean ziBean) {
                    onFinsh.Finsh3(ziBean);
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });
        }
    }
}