package com.bean;

/**
 * Created by lenovo on 2017/9/16.
 * 版本更新的bean
 */

public class GxBean {

    private int code=200;
    private String url;

    public GxBean(int code, String url) {
        this.code = code;
        this.url = url;
    }

    public GxBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GxBean{" +
                "code=" + code +
                ", url='" + url + '\'' +
                '}';
    }
}
