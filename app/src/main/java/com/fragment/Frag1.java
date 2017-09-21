package com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adapter.FragAdapter;
import com.adapter.VpAdapter;
import com.bean.TopBean;
import com.utils.NewsDao;
import com.utils.NewsListInterface;
import com.wangyi.Api;
import com.wangyi.R;
import com.wangyi.XiangQingActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import view.xlistview.XListView;

/**
 * Created by lenovo on 2017/9/14.
 */

public class Frag1 extends Fragment implements NewsListInterface {

    private View view;
    private XListView xlv;
    private FragAdapter fa;
    private ArrayList<ImageView> listIVV=new ArrayList<>();//圆点集合
    int[] imgg={R.drawable.img_1,R.drawable.img_2,R.drawable.img_3};//轮播图片数组

    //handler倒计时跳转
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            int postion=vp_frag1.getCurrentItem();
            postion++;
            vp_frag1.setCurrentItem(postion);

            handler.sendEmptyMessageDelayed(0,3000);
        }
    };
    private LinearLayout ll_frag1;
    private ViewPager vp_frag1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null)
        {
            view = View.inflate(getContext(), R.layout.frag1,null);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initYuan();
        initData();
    }
    /**
     * 添加圆点
     */
    public void initYuan()
    {
        for (int i = 0; i < imgg.length; i++)
        {
            ImageView iv_frag1=new ImageView(getContext());
            if(i == 0)
            {
                iv_frag1.setImageResource(R.drawable.yuan1_red);
            }
            else
            {
                iv_frag1.setImageResource(R.drawable.yuan1_gray);
            }
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(20,20);
            lp.setMargins(5,0,5,10);
            ll_frag1.addView(iv_frag1,lp);
            listIVV.add(iv_frag1);
        }
    }


    private void initData()
    {
        String url=Api.GET_URL+Api.KEY+Api.TYPE1;
        NewsDao dao=new NewsDao();
        dao.get(url);
        dao.setNews(this);
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        xlv = view.findViewById(R.id.xlv);

        View hand=View.inflate(getActivity(),R.layout.handler,null);
        //xlistview头部
        vp_frag1 = hand.findViewById(R.id.vp_frag1);
        ll_frag1 = hand.findViewById(R.id.ll_frag1);
        xlv.addHeaderView(hand);

        xlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                TopBean.ResultBean.DataBean item= (TopBean.ResultBean.DataBean) fa.getItem(i-2);

                Intent intent=new Intent(getActivity(), XiangQingActivity.class);
                intent.putExtra("url",item.getUrl());
                startActivity(intent);
            }
        });

        //自动轮播（无限）的适配器(图片)
        VpAdapter va=new VpAdapter(getContext());
        vp_frag1.setAdapter(va);
        vp_frag1.setCurrentItem(100000);//默认值
        vp_frag1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                //自动轮播
                for (int i = 0; i < listIVV.size(); i++)
                {
                    if(i == position%3)
                    {
                        listIVV.get(i).setImageResource(R.drawable.yuan1_red);
                    }
                    else
                    {
                        listIVV.get(i).setImageResource(R.drawable.yuan1_gray);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //倒计时
        handler.sendEmptyMessageDelayed(0,3000);
    }

    @Override
    public void onNewsListFailure(Call call, IOException e) {

    }
    //成功
    @Override
    public void onNewsListResponse(Call call, final List<TopBean.ResultBean.DataBean> list)
    {
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run()
            {
                if(fa==null)
                {
                    fa = new FragAdapter(getActivity(),list);
                    xlv.setAdapter(fa);
                }
                else
                {
                    fa.notifyDataSetChanged();
                }
                xlv.stopRefresh();
                xlv.stopLoadMore();
            }
        });
    }
}
