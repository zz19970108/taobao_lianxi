package register.presenter;

import android.util.Log;

import register.bean.Bean;
import register.bean.RegBean;
import register.model.RegModel;
import register.view.RegView;

/**
 * Created by dell on 2017/12/12.
 */

public class RegPresenter {
    RegView regView;
    RegModel RegModel;

    public RegPresenter(RegView regView) {
        this.regView = regView;
        RegModel = new RegModel();
    }

    public void setRegView(Bean bean){
        RegModel.Reg(bean);
        RegModel.setOnFinishlisenter(new RegModel.OnFinishlisenter() {
            @Override
            public void OnFinish(RegBean regBean) {
                String code = regBean.getCode();
                Log.e("TAG",regBean.getMsg());
                Log.d("main---",code);
                if ("0".equals(code)) {
                    regView.RegSuccess();
                } else {
                    regView.RegFailed();
                }
            }
        });
    }
}