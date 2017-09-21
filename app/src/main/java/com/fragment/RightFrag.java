package com.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bean.GxBean;
import com.liu.asus.clearcun.CliearUyils;
import com.wangyi.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import cn.jpush.android.api.JPushInterface;
import okhttp3.internal.Version;

/**
 * Created by lenovo on 2017/9/14.
 */

public class RightFrag extends Fragment implements View.OnClickListener {

    private String url="http://gdown.baidu.com/data/wisegame/f98d235e39e29031/baiduxinwen.apk";
    private int code;
    private ProgressDialog dialog;
    private View view;
    private TextView tv_qingchu;
    private boolean bool=false;
    private ImageView iv_tuisong;
    private RelativeLayout rl_gengxin;
    private RelativeLayout rl_qingli;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null)
        {
            view = View.inflate(getContext(), R.layout.right_item,null);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initdata();
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        Switch s=view.findViewById(R.id.s);
        iv_tuisong = view.findViewById(R.id.iv_tuisong);
        rl_gengxin = view.findViewById(R.id.rl_gengxin);
        tv_qingchu = view.findViewById(R.id.tv_qingchu);
        rl_qingli = view.findViewById(R.id.rl_qingli);

        rl_qingli.setOnClickListener(this);
        rl_gengxin.setOnClickListener(this);
        //tv_qingchu.setOnClickListener(this);
        iv_tuisong.setOnClickListener(this);

        //推送
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getActivity(),"打开",Toast.LENGTH_SHORT).show();
                    JPushInterface.resumePush(getActivity());
                }else{
                    Toast.makeText(getActivity(),"关闭",Toast.LENGTH_SHORT).show();
                    JPushInterface.stopPush(getActivity());
                }
            }
        });
    }

    /**
     * 响应点击事件
     * @param view
     */
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_qingli://清理缓存
                System.out.println("====走这里了==");
                AlertDialog.Builder b=new AlertDialog.Builder(getActivity());
                b.setTitle("清除缓存");
                b.setMessage("确定要清除吗？");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        CliearUyils.clearAllCache(getActivity());
                        try {

                            String s = CliearUyils.getdqSize(getActivity());
                            tv_qingchu.setText(s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                    }
                });
                b.create().show();
                break;
            case R.id.rl_gengxin: //检查更新
                AlertDialog.Builder b1=new AlertDialog.Builder(getActivity());
                b1.setTitle("版本更新");
                b1.setMessage("新版本，新功能，多多多");
                b1.setPositiveButton("现在升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        try {
                            PackageManager manager=getActivity().getPackageManager();
                            code=manager.getPackageInfo(getActivity().getPackageName(),0).versionCode;
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        //从网络获取的版本号
                        GxBean bean=new GxBean();
                        int newcode=bean.getCode();
                        bean.setUrl(url);
                        if(code<newcode)
                        {
                            loading();//版本升级
                        }
                    }
                });
                b1.setNegativeButton("暂不升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                    }
                });
                b1.create().show();
                break;
            case R.id.iv_tuisong://推送
                if(bool==false)
                {
                    bool=true;
                    iv_tuisong.setImageResource(R.drawable.select);
                }
                else
                {
                    bool=false;
                    iv_tuisong.setImageResource(R.drawable.img);
                }
                break;
        }
    }
    //清除缓存需要
    @Override
    public void onResume() {
        super.onResume();

        try {
            //CliearUyils.clearAllCache(getActivity());
            String s = CliearUyils.getdqSize(getActivity());
            tv_qingchu.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 版本更新的方法
     */
    private void loading()
    {
        RequestParams rp=new RequestParams(url);
        rp.setCancelFast(true);
        rp.setAutoResume(true);
        x.http().get(rp, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result)
            {
                dialog.dismiss();//弹框消失
                Toast.makeText(getContext(),"版本升级成功", Toast.LENGTH_SHORT).show();
                install(result);//安装新版本
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted()
            {
                dialog.show();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading)
            {
                if(dialog!=null && dialog.isShowing())
                {
                    dialog.setMax((int) total);
                    dialog.setProgress((int) current);
                }
            }
        });
    }

    /**
     * 弹框
     */
    private void initdata() {

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("正在更新");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

    }

    /**
     * 安装新版本
     */
    private void install(File file) {
        //调用系统安装器
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + file.getAbsolutePath()), "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
