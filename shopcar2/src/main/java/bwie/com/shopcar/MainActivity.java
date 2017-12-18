package bwie.com.shopcar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import bwie.com.shopcar.adapter.MyAdapter;
import bwie.com.shopcar.bean.GoosBean;
import bwie.com.shopcar.eventbusevent.MessageEvent;
import bwie.com.shopcar.eventbusevent.PriceAndCountEvent;
import bwie.com.shopcar.presenter.MainPresenter;
import bwie.com.shopcar.view.IMainActivity;


public class MainActivity extends AppCompatActivity implements IMainActivity{

    private ExpandableListView mElv;
    private CheckBox mCheckbox2;
    /**
     * 0
     */
    private TextView mTvPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private int totalCount;
    private int totalPrice;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        initView();
        new MainPresenter(this).getGoods();
        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.selectAllGroup(mCheckbox2.isChecked());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) findViewById(R.id.checkbox2);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvNum = (TextView) findViewById(R.id.tv_num);
    }

    @Override
    public void showList(List<GoosBean.DataBean> groupList, List<List<GoosBean.DataBean.DatasBean>> childList) {
        adapter = new MyAdapter(this, groupList, childList);
        mElv.setAdapter(adapter);
        mElv.setGroupIndicator(null);
        //默认让其全部展开
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCheckbox2.setChecked(event.isChecked());
    }
    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        totalCount += event.getCount();
        totalPrice += event.getPrice();
        mTvNum.setText("结算(" + totalCount + ")");
        mTvPrice.setText(totalPrice + "");
    }
}
