package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import bwie.com.taobao_lianxi.R;
import shouye.adapter.MyAdapter;
import shouye.bean.NewsBean;
import shouye.presenter.RecyclerviewPresenter;

/**
 * Created by dell on 2017/12/13.
 */

public class Fragment01 extends Fragment implements shouye.view.View{

    private XRecyclerView mRecyclerView;
    private List<NewsBean.DataBean> dataBean;
    private ImageLoader imageLoader;
    private RecyclerviewPresenter presenter;
    private XBanner xbanner;
    private XBanner xbanner2;
    private List<String> list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01, container,false);

        mRecyclerView = (XRecyclerView) view.findViewById(R.id.rcl);
        xbanner = (XBanner) view.findViewById(R.id.xbanner);
        xbanner2 = (XBanner) view.findViewById(R.id.xbanner2);

        presenter = new RecyclerviewPresenter(this);
        presenter.getContent();
        imageLoader = ImageLoader.getInstance();

        list.add("https://www.zhaoapi.cn/images/quarter/ad1.png");
        list.add("https://www.zhaoapi.cn/images/quarter/ad2.png");
        list.add("https://www.zhaoapi.cn/images/quarter/ad3.pn");
        list.add("https://www.zhaoapi.cn/images/quarter/ad4.png");
        xbanner.setData(list,null);
        // 设置XBanner的页面切换特效
        xbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        xbanner.setPageChangeDuration(1000);
        // XBanner适配数据
        xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
            }
        });

        xbanner2.setData(list,null);
        // 设置XBanner的页面切换特效
        xbanner2.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        xbanner2.setPageChangeDuration(1000);
        // XBanner适配数据
        xbanner2.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
        MyAdapter adapter=new MyAdapter(getActivity(),dataBean,imageLoader);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnClick(new MyAdapter.clickListener() {
            @Override
            public void onclickListener(int p) {
                Toast.makeText(getActivity(),"点击了id:"+p,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongclickListener(int p) {
                Toast.makeText(getActivity(),"长按了id:"+p,Toast.LENGTH_LONG).show();
            }
        });
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                Toast.makeText(getActivity(), "加载", Toast.LENGTH_SHORT).show();
                mRecyclerView.loadMoreComplete();
            }
        });
    }

    @Override
    public void Data(NewsBean bean) {
        dataBean=bean.getData();
        initView();
    }
}