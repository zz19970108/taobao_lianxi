package shouye.model;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import shouye.bean.NewsBean;
import tools.RegApi;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * Created by dell on 2017/12/13.
 */

public class RecylerviewModel implements Model{

    OnFinsh onFinsh;

    public interface OnFinsh{
        void Finsh(NewsBean newsBean);
    }
    public void setOnFinsh(OnFinsh onFinsh) {
        this.onFinsh = onFinsh;
    }
    @Override
    public void GetData() {
        OkHttp3Utils.doGet(RegApi.RECYLER, new GsonObjectCallback<NewsBean>() {
            @Override
            public void onUi(NewsBean newsBean) {
                if (onFinsh != null) {
                    onFinsh.Finsh(newsBean);
                    Log.e("TAG",newsBean.getData().get(1).getName());
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}