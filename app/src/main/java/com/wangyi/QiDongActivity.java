package com.wangyi;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QiDongActivity extends AppCompatActivity {

    private TextView tv_dao;
    private int i=3;


    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            i--;
            if(i>0)
            {
                tv_dao.setText(i+"s");
                handler.sendEmptyMessageDelayed(0,1000);
            }
           if(i==0)
            {
                tv_dao.setText(i+"s");
                Intent intent=new Intent(QiDongActivity.this,ShouYeActivity.class);
                startActivity(intent);

                finish();
            }
        }
    };
    private ImageView iv_qd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qi_dong);

        initView();
        initdata();
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        tv_dao = (TextView) findViewById(R.id.tv_dao);
        iv_qd = (ImageView) findViewById(R.id.iv_qd);
    }



    private void initdata() {
        int[] imgs={R.drawable.a2,R.drawable.img2_1,R.drawable.a5};
        int index=(int)(Math.random()*imgs.length);
        int rand = imgs[index];


        //给图片设置动画
        AlphaAnimation alpha=new AlphaAnimation(0.0f,2.0f);
        alpha.setDuration(4000);
        alpha.setFillAfter(true);
        //给图片赋值
        iv_qd.setImageResource(rand);
        iv_qd.startAnimation(alpha);

        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                handler.sendEmptyMessageDelayed(0,1000);
            }

            @Override
            public void onAnimationEnd(Animation animation) {//动画结束后

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}




