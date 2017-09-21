package com.wangyi;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.adapter.LiXianAdapter;
import com.bean.Catogray;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class LiXianctivity extends AppCompatActivity implements View.OnClickListener {

    private List<Catogray> list;
    private MySqLiteOpenHelper helper;
    private Button bt_xiazai;
    private RecyclerView rlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_xianctivity);

        initView();
        initData();
    }
    /**
     *初始化控件
     */
    private void initView()
    {
        //实例数据库
        helper=new MySqLiteOpenHelper(this);
        bt_xiazai = (Button) findViewById(R.id.bt_xiazai);//按钮
        rlv = (RecyclerView) findViewById(R.id.rlv_item);
        bt_xiazai.setOnClickListener(this);//点击按钮进行下载页面
    }
    /**
     *添加数据
     */
    private void initData()
    {
        list = new ArrayList<>();
        Catogray bean=new Catogray();
        bean.type="top";
        bean.name="头条";
        list.add(bean);

        bean=new Catogray();
        bean.type="yule";
        bean.name="娱乐";
        list.add(bean);

        bean=new Catogray();
        bean.type="shehui";
        bean.name="社会";
        list.add(bean);

        bean=new Catogray();
        bean.type="tiyu";
        bean.name="体育";
        list.add(bean);
        //实例适配器
        LiXianAdapter adapter=new LiXianAdapter(list,this);
        //线性布局管理器（让数据进行列表展示）
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.setAdapter(adapter);//设置适配器
        //适配器的点击事件
        adapter.setOnItemClickListener(new LiXianAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos, View view)
            {
                //获取控件
                CheckBox checkbox = view.findViewById(R.id.checkbox);
                //Bean类
                Catogray c=list.get(pos);
                //判断checkbox是否选中
                if(checkbox.isChecked())//是
                {
                    checkbox.setChecked(false);//把checkbox状态改为 false
                    c.state=false;
                }
                else
                {
                    checkbox.setChecked(true);
                    c.state=true;
                }
                //修改原有list的数据，根据pos，设置新的对象，然后更新list
                list.set(pos,c);
            }
        });
    }

    /**
     * 响应点击事件
     * 进行离线下载
     * @param view
     */
    @Override
    public void onClick(View view)
    {
        Toast.makeText(LiXianctivity.this,"下载",Toast.LENGTH_SHORT).show();
        if(list!=null&&list.size()>0)
        {
            for (Catogray c:list)
            {
                //判断是否是选中状态，如果选中则执行下载操作
                if(c.state)
                {
                    loadData(c.type);
                    System.out.println("====走这里了了="+c.type);
                }
            }
        }
        //打印，显示 state 状态
        for (Catogray catogray:list)
        {
            System.out.println("===state==" + catogray.state);
        }
    }

    /**
     * 根据type进行数据请求下载
     * @param type
     */
    private void loadData(final String type)
    {
        RequestParams rp=new RequestParams(Api.GET_URL);
        rp.addParameter("key",Api.KEY);
        rp.addParameter("type",type);
        x.http().get(rp,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result)
            {
                System.out.println("======请求数据成功======"+result);
                cunchu(type,result);
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
        });
    }
    /**
     * 数据库存储
     */
    private void cunchu(String type, String result)
    {
        SQLiteDatabase sd=helper.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("type",type);
        values.put("result",result);
        sd.insert("wang",null,values);//表名，null，数据
        System.out.println("===数据库存储成功");
    }
}
