package shouye.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bwie.com.taobao_lianxi.R;
import shouye.bean.NewsBean;

/**
 * Created by dell on 2017/12/13.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<NewsBean.DataBean> dataBean;
    ImageLoader loader;
    clickListener clickListener;

    public interface clickListener{
        void onclickListener(int p);
        void onLongclickListener(int p);
    }
    public void setOnClick(clickListener clickListener) {
        this.clickListener = clickListener;
    }

    public MyAdapter(Context context, List<NewsBean.DataBean> dataBean, ImageLoader loader) {
        this.context = context;
        this.dataBean = dataBean;
        this.loader = loader;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        NewsBean.DataBean bean = dataBean.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.data.setText(bean.getName());
        loader.displayImage(bean.getIcon(),myViewHolder.img);
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onclickListener(position);
            }
        });
        myViewHolder.ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clickListener.onLongclickListener(position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataBean.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private  ImageView img;
        private  TextView data;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll = (LinearLayout)itemView.findViewById(R.id.ll);
            img = (ImageView) itemView.findViewById(R.id.img);
            data = (TextView) itemView.findViewById(R.id.data);
        }
    }
}