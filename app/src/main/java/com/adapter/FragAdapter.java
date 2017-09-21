package com.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bean.TopBean;
import com.bumptech.glide.Glide;
import com.wangyi.R;

import java.util.List;

/**
 * Created by lenovo on 2017/9/15.
 */

public class FragAdapter extends BaseAdapter{

    private int t1=0;
    private int t2=1;
    private int num=2;

    Context context;
    List<TopBean.ResultBean.DataBean> list;
    public FragAdapter(Context context, List<TopBean.ResultBean.DataBean> list)
    {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {

        TopBean.ResultBean.DataBean bean = list.get(position);
        if(bean.getThumbnail_pic_s02()!=null&&bean.getThumbnail_pic_s03()!=null)
        {
            return t2;
        }
        else
        {
            return t1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return num;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        int type=getItemViewType(i);

        ViewHolder1 holder1=null;
        ViewHolder2 holder2=null;
        if(view==null)
        {
            switch (type)
            {
                case 0:
                    holder1=new ViewHolder1();
                    view=View.inflate(context,R.layout.item1,null);
                    holder1.iv1_img=view.findViewById(R.id.iv1_img);
                    holder1.tv1_title=view.findViewById(R.id.tv1_title);
                    holder1.tv1_time=view.findViewById(R.id.tv1_time);
                    holder1.tv1_category=view.findViewById(R.id.tv1_category);

                    Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder1.iv1_img);
                    holder1.tv1_title.setText(list.get(i).getTitle());
                    holder1.tv1_time.setText(list.get(i).getDate());
                    holder1.tv1_category.setText(list.get(i).getCategory());

                    view.setTag(holder1);
                    break;
                case 1:
                    holder2=new ViewHolder2();
                    view=View.inflate(context,R.layout.item2,null);
                    holder2.tv2_title=view.findViewById(R.id.tv2_title);
                    holder2.tv2_category=view.findViewById(R.id.tv2_category);
                    holder2.iv2_img1=view.findViewById(R.id.iv2_img1);
                    holder2.iv2_img2=view.findViewById(R.id.iv2_img2);
                    holder2.iv2_img3=view.findViewById(R.id.iv2_img3);

                    holder2.tv2_title.setText(list.get(i).getTitle());
                    holder2.tv2_category.setText(list.get(i).getCategory());
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder2.iv2_img1);
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s02()).into(holder2.iv2_img2);
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s03()).into(holder2.iv2_img3);

                    view.setTag(holder2);
                    break;
            }
        }
        else
        {
            switch (type)
            {
                case 0:
                    holder1= (ViewHolder1) view.getTag();
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder1.iv1_img);
                    holder1.tv1_title.setText(list.get(i).getTitle());
                    holder1.tv1_time.setText(list.get(i).getDate());
                    holder1.tv1_category.setText(list.get(i).getCategory());
                    break;
                case 1:
                    holder2= (ViewHolder2) view.getTag();
                    holder2.tv2_title.setText(list.get(i).getTitle());
                    holder2.tv2_category.setText(list.get(i).getCategory());
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder2.iv2_img1);
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s02()).into(holder2.iv2_img2);
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s03()).into(holder2.iv2_img3);
                    break;
            }
        }
        return view;
    }
    class ViewHolder1
    {
        ImageView iv1_img;
        TextView tv1_title,tv1_time,tv1_category;
    }
    class ViewHolder2
    {
        ImageView iv2_img1,iv2_img2,iv2_img3;
        TextView tv2_title,tv2_category;
    }
}
