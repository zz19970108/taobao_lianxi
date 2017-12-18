package login.pressnter;

import android.util.Log;

import login.bean.User;
import login.bean.UserBean;
import login.model.UserModel;
import login.view.LoginView;

/**
 * Created by dell on 2017/12/12.
 */

public class MyPersenter {
    private LoginView view;
    private UserModel userModel;

    public MyPersenter(LoginView view) {
        this.view = view;
        userModel = new UserModel();
    }

    public void setLogin(User uset){
        userModel.login(uset);
        userModel.setOnFinsh(new UserModel.OnFinsh() {
            @Override
            public void Finsh(UserBean userbean) {
                String code = userbean.getCode();
                Log.e("TAG",userbean.getMsg());
                Log.d("main---",code);
                if ("0".equals(code)) {
                    view.LoginSuccess();;
                } else {
                    view.LoginFailed();
                }
            }
        });
    }

}