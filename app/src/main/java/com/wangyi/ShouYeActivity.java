package com.wangyi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fragment.Frag1;
import com.fragment.ShouYeFrag;
import com.fragment.WoDeFrag;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

public class ShouYeActivity extends AppCompatActivity {


    private ShouYeFrag sy;
    private WoDeFrag wd;
    private Button bt_1;
    private Button bt_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_ye);

        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);

        //实例fragment
        sy = new ShouYeFrag();
        wd = new WoDeFrag();

        FrameLayout fl= (FrameLayout) findViewById(R.id.fl);
        //添加
        getSupportFragmentManager().beginTransaction().add(R.id.fl, sy).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, wd).commit();

        //默认展示
        getSupportFragmentManager().beginTransaction().show(sy).commit();
        getSupportFragmentManager().beginTransaction().hide(wd).commit();
    }
    public void shouye(View view)
    {
        bt_1.setTextColor(Color.RED);
        bt_2.setTextColor(Color.BLACK);
        getSupportFragmentManager().beginTransaction().show(sy).commit();
        getSupportFragmentManager().beginTransaction().hide(wd).commit();
    }
    public void wode(View view)
    {
        bt_1.setTextColor(Color.BLACK);
        bt_2.setTextColor(Color.RED);
        getSupportFragmentManager().beginTransaction().show(wd).commit();
        getSupportFragmentManager().beginTransaction().hide(sy).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
}
