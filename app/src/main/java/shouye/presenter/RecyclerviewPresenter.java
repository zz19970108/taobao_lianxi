package shouye.presenter;


import shouye.bean.NewsBean;
import shouye.model.RecylerviewModel;
import shouye.view.View;

/**
 * Created by dell on 2017/12/13.
 */

public class RecyclerviewPresenter {
    View view;
    RecylerviewModel recylerviewModel;

    public RecyclerviewPresenter(View view) {
        this.view = view;
        this.recylerviewModel=new RecylerviewModel();
    }

    public void getContent(){
        recylerviewModel.GetData();
        recylerviewModel.setOnFinsh(new RecylerviewModel.OnFinsh() {
            @Override
            public void Finsh(NewsBean newsBean) {
                view.Data(newsBean);
            }
        });
    }
}