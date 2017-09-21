package com.wangyi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adapter.YinDaoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页面
 */
public class YinDaoActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager vp_hua;
    private List<Integer> listImg;
    private LinearLayout ll_yuan;
    private Button bt1_tiao;
    private List<ImageView> listIv;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_dao);

        initImg();
        initView();
        initYuan();
    }

    /**
     * 添加圆点
     */
    private void initYuan()
    {
        //圆点的集合
        listIv = new ArrayList<>();
        for (int i = 0; i < listImg.size(); i++)
        {
            ImageView iv_yuan=new ImageView(this);
            if(i==0)
            {
                iv_yuan.setImageResource(R.drawable.yuan1_red);
            }
            else
            {
                iv_yuan.setImageResource(R.drawable.yuan1_gray);
            }
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(20,20);
            lp.setMargins(5,0,5,0);
            ll_yuan.addView(iv_yuan,lp);
            listIv.add(iv_yuan);
        }
    }

    /**
     * 添加图片
     */
    private void initImg()
    {
        //图片的集合
        listImg = new ArrayList<>();
        listImg.add(R.drawable.img1_1);
        listImg.add(R.drawable.img2_1);
        listImg.add(R.drawable.img3_1);
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        sp = getSharedPreferences("user",MODE_PRIVATE);

        vp_hua = (ViewPager) findViewById(R.id.vp_hua);
        ll_yuan = (LinearLayout) findViewById(R.id.ll_yuan);
        bt1_tiao = (Button) findViewById(R.id.bt1_tiao);

        vp_hua.setOnPageChangeListener(this);
        bt1_tiao.setOnClickListener(this);
        vp_hua.setAdapter(new YinDaoAdapter(this,listImg));
        if(sp.getBoolean("flag",false))
        {
            //状态为false，表示是第一次进
            Intent intent=new Intent(YinDaoActivity.this,QiDongActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     *三个重写方法是viewpager的
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 页面滑动状态改变时
     * @param position
     */
    @Override
    public void onPageSelected(int position)
    {
        if(position%3==2)
        {
            bt1_tiao.setVisibility(View.VISIBLE);//按钮显示
        }
        else
        {
            bt1_tiao.setVisibility(View.INVISIBLE);//按钮不显示
        }
        //圆点随着图片滑动而改变
        for (int i = 0; i < listIv.size(); i++)
        {
            if(i==position)
            {
                listIv.get(i).setImageResource(R.drawable.yuan1_red);
            }
            else
            {
                listIv.get(i).setImageResource(R.drawable.yuan1_gray);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 响应按钮的点击事件
     * @param view
     */
    @Override
    public void onClick(View view)
    {
        Intent intent=new Intent(YinDaoActivity.this,ShouYeActivity.class);
        startActivity(intent);
        //状态为true，表示不是第一次进
        sp.edit().putBoolean("flag",true).commit();

        finish();
    }
}
