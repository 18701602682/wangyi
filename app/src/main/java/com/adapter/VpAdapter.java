package com.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.wangyi.R;


/**
 * Created by lenovo on 2017/8/18.
 */

public class VpAdapter extends PagerAdapter {

    int[] imgg={R.drawable.img_1,R.drawable.img_2,R.drawable.img_3};
    Context context;

    public VpAdapter(int[] imgg) {
        this.imgg = imgg;
    }

    public VpAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view=View.inflate(context, R.layout.frag1_img,null);

        ImageView iv_frag1=view.findViewById(R.id.iv_frag1);

        iv_frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position%imgg.length==0){
                    Toast.makeText(context,"position==="+position,Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        iv_frag1.setImageResource(imgg[position%imgg.length]);

        container.addView(view);

        return view;
    }
}
