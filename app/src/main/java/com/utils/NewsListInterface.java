package com.utils;

import com.bean.TopBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by lenovo on 2017/9/15.
 */

public interface NewsListInterface {

    void onNewsListFailure(Call call, IOException e);
    void onNewsListResponse(Call call, List<TopBean.ResultBean.DataBean> list);
}
