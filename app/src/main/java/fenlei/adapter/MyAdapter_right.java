package fenlei.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bwie.com.taobao_lianxi.R;
import fenlei.bean.RightBean;

/**
 * Created by dell on 2017/12/14.
 */

public class MyAdapter_right extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<RightBean.DataBean> dataBean;
    onClick onClick;
    private List<RightBean.DataBean.ListBean> list;

    public interface onClick{
        void Click(int position);
    }

    public void setOnClick(onClick onClick) {
        this.onClick = onClick;
    }

    public MyAdapter_right(Context context, List<RightBean.DataBean> dataBean) {
        this.context = context;
        this.dataBean = dataBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.rtght_item, parent,false);
        MyViewHolder mViewholder = new MyViewHolder(mView);
        return mViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.rText.setText(dataBean.get(position).getName());
        list=dataBean.get(position).getList();
        myViewHolder.rvright_a.setLayoutManager(new GridLayoutManager(context,3));

        MyAdapter_right_zi myAdapter_right_zi=new MyAdapter_right_zi(context,list);
        myViewHolder.rvright_a.setAdapter(myAdapter_right_zi);
        myViewHolder.rText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.Click(position);
            }
        });
        myAdapter_right_zi.setOnClick(new MyAdapter_right_zi.onClick() {
            @Override
            public void Click(int position) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBean.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView rText;
        private final RecyclerView rvright_a;

        public MyViewHolder(View itemView) {
            super(itemView);
            rText = itemView.findViewById(R.id.rText);
            rvright_a = itemView.findViewById(R.id.rvright_a);

        }
    }
}