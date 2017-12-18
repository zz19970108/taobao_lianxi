package fenlei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bwie.com.taobao_lianxi.R;
import fenlei.bean.RightBean;

/**
 * Created by dell on 2017/12/15.
 */

public class MyAdapter_right_zi extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<RightBean.DataBean.ListBean> list;

    onClick onClick;

    public interface onClick{
        void Click(int position);
    }

    public void setOnClick(onClick onClick){
        this.onClick = onClick;
    }

    public MyAdapter_right_zi(Context context, List<RightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.right_zi_item, parent,false);
        MyViewHolder mViewholder = new MyViewHolder(mView);
        return mViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder mViewHolder = (MyViewHolder) holder;
        mViewHolder.rText_a.setText(list.get(position).getName());
        mViewHolder.rText_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.Click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView rText_a;

        public MyViewHolder(View itemView) {
            super(itemView);
            rText_a = itemView.findViewById(R.id.rText_a);
        }
    }
}
