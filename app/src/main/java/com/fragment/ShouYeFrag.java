package com.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import com.bean.TopBean;
import com.kson.slidingmenu.SlidingMenu;
import com.wangyi.LiXianctivity;
import com.wangyi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/9/14.
 */

public class ShouYeFrag extends Fragment implements View.OnClickListener {

    private View view;
    private List<TopBean> beanList;
    private List<Fragment> fragList;
    private TopBean bean;
    private TabLayout tabhost;
    private ViewPager vp;
    private List<String> list;
    private ImageView iv_you;
    private PopupWindow pop;
    private SlidingMenu menu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null)
        {
            view = View.inflate(getContext(), R.layout.shouye_frag,null);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initView();
        initMenu();
    }
    /**
     * 侧滑
     */
    private void initMenu()
    {
        menu = new SlidingMenu(getContext());
        //添加左侧滑
        menu.setMenu(R.layout.left);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_left,new LeftFrag()).commit();
        //设置显示模式，左、右或者两者都有
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //距离主页面的距离
        menu.setBehindOffsetRes(R.dimen.size);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.7f);
        //添加右侧滑
        menu.setSecondaryMenu(R.layout.right);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_right,new RightFrag()).commit();

        menu.attachToActivity(getActivity(),SlidingMenu.SLIDING_CONTENT);
    }
    /**
     *添加数据
     */
    private void initData()
    {
        list = new ArrayList<>();
        list.add("头条");
        list.add("娱乐");
        list.add("社会");
        list.add("体育");
        list.add("科技");
        list.add("财经");
        list.add("时尚");
        list.add("军事");
        /*
        beanList = new ArrayList<>();
        fragList = new ArrayList<>();

        bean = new TopBean();
        bean.id="top";
        bean.name="头条";
        bean.bool=true;
        beanList.add(bean);

        bean = new TopBean();
        bean.id="shehui";
        bean.name="社会";
        bean.bool=true;
        beanList.add(bean);

        bean = new TopBean();
        bean.id="beijing";
        bean.name="北京";
        bean.bool=true;
        beanList.add(bean);

        bean = new TopBean();
        bean.id="tiyu";
        bean.name="体育";
        bean.bool=true;
        beanList.add(bean);

        bean = new TopBean();
        bean.id="yule";
        bean.name="娱乐";
        bean.bool=true;
        beanList.add(bean);

        bean = new TopBean();
        bean.id="dujia";
        bean.name="独家";
        bean.bool=true;
        beanList.add(bean);

        bean = new TopBean();
        bean.id="keji";
        bean.name="科技";
        bean.bool=true;
        beanList.add(bean);

        bean = new TopBean();
        bean.id="shishang";
        bean.name="时尚";
        bean.bool=true;
        beanList.add(bean);

        fragList.add(new Frag1());
        fragList.add(new Frag1());
        fragList.add(new Frag1());
        fragList.add(new Frag1());
        fragList.add(new Frag1());
        fragList.add(new Frag1());
        fragList.add(new Frag1());
        fragList.add(new Frag1());
        //tabhost.diaplay(beanList,fragList);
        */
    }
    /**
     * 初始化控件
     */
    private void initView()
    {
        tabhost = view.findViewById(R.id.tabhost);
        vp = view.findViewById(R.id.vp);
        iv_you = (ImageView) view.findViewById(R.id.iv_you);
        ImageView iv_zuo= (ImageView) view.findViewById(R.id.iv_zuo);
        ImageView iv_tou= (ImageView) view.findViewById(R.id.iv_tou);
        ImageView top_img= (ImageView) view.findViewById(R.id.top_img);

        iv_zuo.setOnClickListener(this);
        iv_you.setOnClickListener(this);
        iv_tou.setOnClickListener(this);
        top_img.setOnClickListener(this);

        vp.setAdapter(new ShouYeAdapter(getActivity().getSupportFragmentManager()));
        tabhost.setupWithViewPager(vp);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.iv_zuo:
                //显示左菜单
                menu.showMenu();
                break;
            case R.id.iv_you:
                //构建一个popupwindow的布局
                View view1=View.inflate(getContext(),R.layout.pop_item,null);
                pop = new PopupWindow(view1,280,500,true);//创建PopupWindow对象，指定宽度和高度
                //距离点击出现pop控件的位置
                //pop.showAtLocation(view.findViewById(R.id.iv_you), Gravity.TOP,210,160);
                pop.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#eeeeee")));//设置背景颜色
                pop.setFocusable(true);//获取焦点
                pop.setOutsideTouchable(true);//设置可以触摸弹出框以外的区域
                pop.update();//刷新
                pop.showAsDropDown(iv_you, 0, 20);//下拉的方式显示，并且可以设置显示的位置
                RelativeLayout tianqi=view1.findViewById(R.id.tq);
                RelativeLayout lixian=view1.findViewById(R.id.lx);
                RelativeLayout sousou=view1.findViewById(R.id.ss);
                RelativeLayout saoyisao=view1.findViewById(R.id.sys);
                RelativeLayout yejian=view1.findViewById(R.id.yj);

                tianqi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        pop.dismiss();
                    }
                });
                lixian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        startActivity(new Intent(getActivity(),LiXianctivity.class));
                        pop.dismiss();
                    }
                });
                sousou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        pop.dismiss();
                    }
                });
                saoyisao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        pop.dismiss();
                    }
                });
                yejian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        pop.dismiss();
                    }
                });
                break;
            case R.id.iv_tou:
                //显示右菜单
                menu.showSecondaryMenu();
                break;
            case R.id.top_img:
                pindao();
                System.out.println("===走频道管理了");
                break;
        }
    }
    public void pindao()
    {

    }

    /**
     * 适配器
     */
    class ShouYeAdapter extends FragmentPagerAdapter
    {
        public ShouYeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            Fragment frag=null;
            switch(position)
            {
                case 0:
                    frag=new Frag1();
                    break;
                case 1:
                    frag=new Frag2();
                    break;
                case 2:
                    frag=new Frag3();
                    break;
                case 3:
                    frag=new Frag4();
                    break;
                case 4:
                    frag=new Frag5();
                    break;
                case 5:
                    frag=new Frag6();
                    break;
                case 6:
                    frag=new Frag7();
                    break;
                case 7:
                    frag=new Frag8();
                    break;
            }
            return frag;
        }

        @Override
        public int getCount()
        {
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return list.get(position);
        }
    }
}
