package register.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import register.bean.Bean;
import register.bean.RegBean;
import tools.RegApi;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * Created by dell on 2017/12/12.
 */

public class RegModel implements BeanModel{
    OnFinishlisenter OnFinishlisenter;

    public interface OnFinishlisenter{
        void OnFinish(RegBean regBean);
    }

    public void setOnFinishlisenter(OnFinishlisenter OnFinishlisenter){
        this.OnFinishlisenter = OnFinishlisenter;
    }

    @Override
    public void Reg(Bean bean) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", bean.getName());
        map.put("password", bean.getPass());
        OkHttp3Utils.doPost(RegApi.REG, map, new GsonObjectCallback<RegBean>(){

            @Override
            public void onUi(RegBean bean) {
                if(OnFinishlisenter!=null){
                    OnFinishlisenter.OnFinish(bean);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}