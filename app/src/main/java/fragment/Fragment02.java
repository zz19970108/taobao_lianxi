package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import bwie.com.taobao_lianxi.R;
import fenlei.adapter.MyAdapter_left;
import fenlei.adapter.MyAdapter_right;
import fenlei.bean.LeftBean;
import fenlei.bean.RightBean;
import fenlei.bean.Right_zi_Bean;
import fenlei.presenter.MyPresenter;
import fenlei.view.fenlei_view;

/**
 * Created by dell on 2017/12/13.
 */

public class Fragment02 extends Fragment implements fenlei_view {

    MyPresenter mPresenter;
    private List<LeftBean.DataBean> dataBean;
    private MyAdapter_right myAdapter_right;
    private RecyclerView rv_left;
    private RecyclerView rv_right;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment02, container,false);

        mPresenter = new MyPresenter(this);
        mPresenter.action(0,1);

        rv_left = (RecyclerView) view.findViewById(R.id.type_rvleft);
        rv_right = (RecyclerView) view.findViewById(R.id.type_rvright);


//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
//        //得到WindowManager
//        WindowManager windowManager = getActivity().getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        //得到屏幕宽
//        int width = display.getWidth();
//        //将RecyclerView宽设置为屏幕宽的1/5
//        params.width = width * 1 / 5;
//        rv_left.setLayoutParams(params);
        //得到RecyclerView布局管理器
        LinearLayoutManager leftLayoutManager = new LinearLayoutManager(getActivity());
        //RecyclerView设置布局管理器
        rv_left.setLayoutManager(leftLayoutManager);
        //得到RecyclerView布局管理器
        LinearLayoutManager rightLayoutManager = new LinearLayoutManager(getActivity());
        //RecyclerView设置布局管理器
        rv_right.setLayoutManager(rightLayoutManager);

        return view;
    }

    @Override
    public void getData_left(LeftBean leftBean) {
        dataBean=leftBean.getData();
        MyAdapter_left myAdapter_left=new MyAdapter_left(getActivity(),dataBean);
        rv_left.setAdapter(myAdapter_left);
        myAdapter_left.setOnClickListener(new MyAdapter_left.OnClickListener() {
            @Override
            public Void OnClick(int position) {
                mPresenter.action(dataBean.get(position).getCid(),2);
                return null;
            }
        });
    }

    @Override
    public void getData_right(RightBean rightBean) {
        List<RightBean.DataBean> been=rightBean.getData();
        if (been.size()>0){
            myAdapter_right=new MyAdapter_right(getActivity(),been);
            rv_right.setAdapter(myAdapter_right);
            myAdapter_right.setOnClick(new MyAdapter_right.onClick() {
                @Override
                public void Click(int position) {
                    Toast.makeText(getActivity(),"我是右边布局的下标"+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void getData_right_zi(Right_zi_Bean ziBean) {

    }
}
