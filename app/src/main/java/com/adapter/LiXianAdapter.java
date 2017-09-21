package com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bean.Catogray;
import com.wangyi.R;

import java.util.List;

/**
 * Created by lenovo on 2017/9/20.
 */

public class LiXianAdapter extends RecyclerView.Adapter<LiXianAdapter.MyViewHolder>{

    private List<Catogray> list;//集合
    private Context context;//上下文
    private OnItemClickListener onItemClickListener;//接口
    //构造器
    public LiXianAdapter(List<Catogray> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=View.inflate(context,R.layout.item_rlv,null);
        MyViewHolder holder=new MyViewHolder(view);

        //实现自己的回调接口（注意回调接口，哪个场景下使用，就在哪里设置一下，才能起作用）
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onItemClickListener.onItemClickListener((Integer) view.getTag(),view);
            }
        });
        return holder;
    }
    /**
     * 这个方法主要用于处理逻辑（绘制ui数据）
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.nametv.setText(list.get(position).name);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    /**
     * 自己定义的viewholder，继承RecyclerView自带的viewholder，优点是：可扩展，代码简介
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView nametv;
        private CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            nametv = itemView.findViewById(R.id.name);
            checkBox = itemView.findViewById(R.id.checkbox);

        }
    }
    /**
     * 供调用者调用的接口（所以声明为public）
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    /**
     * 条目点击事件接口（recyclerview本身不支持点击事件，必须要自己写）
     */
    public interface OnItemClickListener
    {
        void onItemClickListener(int pos,View view);
    }
}
