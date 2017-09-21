package com.wangyi;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by lenovo on 2017/9/16.
 */

public class App extends Application{

    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        UMShareAPI.get(this);

        x.Ext.init(this);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
