package com.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wangyi.R;

/**
 * Created by lenovo on 2017/9/14.
 */

public class LeftFrag extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout ll_xinwen;
    private LinearLayout ll_dingyue;
    private LinearLayout ll_tupian;
    private LinearLayout ll_shipin;
    private LinearLayout ll_gentie;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null)
        {
            view = View.inflate(getContext(), R.layout.left_item,null);
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
        ll_xinwen = view.findViewById(R.id.ll_xinwen);
        ll_dingyue = view.findViewById(R.id.ll_dingyue);
        ll_tupian = view.findViewById(R.id.ll_tupian);
        ll_shipin = view.findViewById(R.id.ll_shipin);
        ll_gentie = view.findViewById(R.id.ll_gentie);

        ll_xinwen.setOnClickListener(this);
        ll_dingyue.setOnClickListener(this);
        ll_tupian.setOnClickListener(this);
        ll_shipin.setOnClickListener(this);
        ll_gentie.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.ll_xinwen:
                //ll_xinwen.setBackground(Color.GRAY);
                break;
        }
    }
}
