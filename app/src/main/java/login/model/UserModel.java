package login.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import login.bean.User;
import login.bean.UserBean;
import okhttp3.Call;
import tools.RegApi;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * Created by dell on 2017/12/12.
 */

public class UserModel implements LoginModel{
    private OnFinsh onFinsh;

    public interface OnFinsh{
        void Finsh(UserBean userbean);
    }

    public void setOnFinsh(OnFinsh onFinsh){
        this.onFinsh = onFinsh;
    }


    @Override
    public void login(User user) {

        Map<String, String> map = new HashMap<>();
        map.put("mobile", user.getName());
        map.put("password", user.getPass());
        OkHttp3Utils.doPost(RegApi.LOGIN, map, new GsonObjectCallback<UserBean>() {
            @Override
            public void onUi(UserBean userbean) {
                if(onFinsh!=null){
                    onFinsh.Finsh(userbean);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}