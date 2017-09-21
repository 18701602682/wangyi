package com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.wangyi.R;

import java.util.Map;

/**
 * Created by lenovo on 2017/9/14.
 */

public class WoDeFrag extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView iv_touxiang;
    private TextView tv_denglul;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null)
        {
            view = View.inflate(getContext(), R.layout.wode_frag,null);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        iv_touxiang = view.findViewById(R.id.iv_touxaing);
        tv_denglul = view.findViewById(R.id.tv_denglu);
        tv_denglul.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                System.out.println("===qq用户名==222");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map)
            {
                String name = map.get("name");
                String url = map.get("iconurl");
                tv_denglul.setText(name);
                Glide.with(getActivity()).load(url).into(iv_touxiang);
                System.out.println("===qq用户名=="+name+"==头像=="+url);

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable)
            {
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i)
            {
            }
        });
    }

}
