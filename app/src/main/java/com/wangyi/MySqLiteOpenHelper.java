package com.wangyi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2017/09/04
 * Description:数据库帮助类
 */
public class MySqLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "data";
    private static final int VERSION = 1;

    public MySqLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table WY(type varchar,content text)";
        sqLiteDatabase.execSQL(sql);

        sqLiteDatabase.execSQL("create table wang(type varchar,json text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
