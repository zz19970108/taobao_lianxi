package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bwie.com.taobao_lianxi.R;
import gouwuche.IView.IFragment03;
import gouwuche.adapter.CartAdapter;
import gouwuche.bean.CartBean;
import gouwuche.bean.CountPriceBean;
import gouwuche.custom.CartExpanableListview;
import gouwuche.presenter.CartPresenter;

/**
 * Created by dell on 2017/12/13.
 */

public class Fragment03 extends Fragment implements IFragment03,View.OnClickListener{

    private CartExpanableListview expanableListview;
    private String cartUrl = "http://120.27.23.105/product/getCarts?uid=3004";
    private CartPresenter cartPresenter;
    private Gson gson;
    private CartAdapter cartAdapter;
    private CheckBox check_all;
    private TextView text_total;
    private TextView text_buy;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                CountPriceBean countPriceBean = (CountPriceBean) msg.obj;

                //设置
                text_total.setText("合计:¥"+countPriceBean.getPrice());
                text_buy.setText("去结算("+countPriceBean.getCount()+")");
            }else  if (msg.what == 1){//改变全选
                boolean flag = (boolean) msg.obj;

                check_all.setChecked(flag);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment03, container,false);

        check_all = view.findViewById(R.id.check_all);
        text_total = view.findViewById(R.id.text_total);
        text_buy = view.findViewById(R.id.text_buy);
        expanableListview = view.findViewById(R.id.expanable_listview);

        //去掉默认的指示器
        expanableListview.setGroupIndicator(null);

        cartPresenter = new CartPresenter(this);
        gson = new Gson();

        //全选:...点击事件
        check_all.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //请求数据
        cartPresenter.getCartData(cartUrl);
    }

    @Override
    public void getSuccessCartData(String json) {
        //解析数据
        CartBean cartBean=gson.fromJson(json,CartBean.class);

        //一个是一级标题的数据
        List<CartBean.DataBean> listGroup = cartBean.getData();

        //所有子条目的数据
        List<List<CartBean.DataBean.ListBean>> listChilds = new ArrayList<>();
        for (int i=0;i<listGroup.size();i++){
            listChilds.add(listGroup.get(i).getList());
        }

        //设置适配器
        cartAdapter = new CartAdapter(getActivity(), listGroup, listChilds,handler);
        expanableListview.setAdapter(cartAdapter);

        //展开所有
        for (int i=0;i<listGroup.size();i++){
            expanableListview.expandGroup(i);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.check_all:
                cartAdapter.setIfCheckAll(check_all.isChecked());
                break;
        }
    }
}