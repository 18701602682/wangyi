package com.utils;

import com.bean.TopBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/9/15.
 */

public class NewsDao{

    private NewsListInterface news;

    public void get(String url)
    {
        System.out.println("===url请求成功=="+url);
        OkHttpClient ok=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        Call call=new OkHttpClient().newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                news.onNewsListFailure(call,e);
                System.out.println("===失败==");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                    System.out.println("===成功==");
                    String result=response.body().string();
                    System.out.println("===数据请求成功=="+result);
                    Gson gson=new Gson();
                    TopBean bean=gson.fromJson(result,TopBean.class);
                    news.onNewsListResponse(call,bean.getResult().getData());

            }
        });
    }

    public void setNews(NewsListInterface news) {
        this.news = news;
    }
}
