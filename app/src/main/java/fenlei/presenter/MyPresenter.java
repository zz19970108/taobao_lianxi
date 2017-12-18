package fenlei.presenter;

import fenlei.bean.LeftBean;
import fenlei.bean.RightBean;
import fenlei.bean.Right_zi_Bean;
import fenlei.model.MyModel_fenlei;
import fenlei.view.fenlei_view;

/**
 * Created by dell on 2017/12/15.
 */

public class MyPresenter {
    fenlei_view view;
    MyModel_fenlei modelFenlei;

    public MyPresenter(fenlei_view view) {
        this.view = view;
        this.modelFenlei = new MyModel_fenlei();
    }
    public void action(int cid,int i){
        if (i==1){
            modelFenlei.getFenlei(cid,i);
            modelFenlei.setOnFinsh(new MyModel_fenlei.OnFinsh() {
                @Override
                public void Finsh1(LeftBean leftBean) {
                    view.getData_left(leftBean);
                }

                @Override
                public void Finsh2(RightBean rightBean) {

                }

                @Override
                public void Finsh3(Right_zi_Bean ziBean) {

                }
            });
        }
        if (i==2){
            modelFenlei.getFenlei(cid, i);
            modelFenlei.setOnFinsh(new MyModel_fenlei.OnFinsh() {
                @Override
                public void Finsh1(LeftBean leftBean) {

                }

                @Override
                public void Finsh2(RightBean rightBean) {
                    view.getData_right(rightBean);
                }

                @Override
                public void Finsh3(Right_zi_Bean ziBean) {

                }
            });
        }
        if (i==3){
            modelFenlei.getFenlei(cid, i);
            modelFenlei.setOnFinsh(new MyModel_fenlei.OnFinsh() {
                @Override
                public void Finsh1(LeftBean leftBean) {

                }

                @Override
                public void Finsh2(RightBean rightBean) {

                }

                @Override
                public void Finsh3(Right_zi_Bean ziBean) {
                    view.getData_right_zi(ziBean);
                }
            });
        }
    }
}