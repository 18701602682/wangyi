package com.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wangyi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/9/14.
 * 引导页的adapter
 */

public class YinDaoAdapter extends PagerAdapter {

    Context context;
    List<Integer> list=new ArrayList<>();

    public YinDaoAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view=View.inflate(context, R.layout.yindao_item,null);
        ImageView iv_yindao=view.findViewById(R.id.iv_yindao);
        iv_yindao.setImageResource(list.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
