package com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adapter.FragAdapter;
import com.bean.TopBean;
import com.utils.NewsDao;
import com.utils.NewsListInterface;
import com.wangyi.Api;
import com.wangyi.R;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import view.xlistview.XListView;

/**
 * Created by lenovo on 2017/9/14.
 */

public class Frag2 extends Fragment implements NewsListInterface {

    private View view;
    private XListView xlv;
    private FragAdapter fa;

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
        initData();
    }

    private void initData()
    {
        String url= Api.GET_URL+Api.KEY+Api.TYPE2;
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
