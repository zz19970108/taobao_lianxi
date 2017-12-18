package fenlei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bwie.com.taobao_lianxi.R;
import fenlei.bean.LeftBean;

/**
 * Created by dell on 2017/12/14.
 */

public class MyAdapter_left extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<LeftBean.DataBean> list;

    private OnClickListener OnClickListener;

    public interface OnClickListener{
        Void OnClick(int position);
    }

    public void setOnClickListener(OnClickListener OnClickListener){
        this.OnClickListener = OnClickListener;
    }

        public MyAdapter_left(Context context, List<LeftBean.DataBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.left_item, parent,false);
        MyViewholder mViewholder = new MyViewholder(mView);
        return mViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewholder mViewholder = (MyViewholder) holder;
        mViewholder.fText.setText(list.get(position).getName());
        mViewholder.fText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickListener.OnClick(position);
//                Toast.makeText(context,"下标"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewholder extends RecyclerView.ViewHolder {

        private final TextView fText;

        public MyViewholder(View itemView) {
            super(itemView);
            fText = itemView.findViewById(R.id.fText);

        }
    }
}