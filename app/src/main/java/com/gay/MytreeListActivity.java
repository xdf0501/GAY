package com.gay;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Bean.CommentBean;
import com.Bean.MyinvilistBean;
import com.Constant;
import com.View.Main_messageFragment;
import com.adapter.CommentAdapter;
import com.adapter.MyinvilistAdapter;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;


/**
 * Created by 狄飞 on 2017/4/27.
 */

public class MytreeListActivity extends Activity implements HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private SharedPreferences sp=null;// 存放用户信息
    private int useid;
    private MyinvilistAdapter adapter;
    private ListView listView;
    List<MyinvilistBean.InviListBean.DatasBean> mytreelist = new ArrayList<MyinvilistBean.InviListBean.DatasBean>();
    private TextView title;
    private ImageView back;
    private String type="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytree);
        title=(TextView)findViewById(R.id.title_name1);
        back=(ImageView)findViewById(R.id.imageView2);
        listView=(ListView)findViewById(R.id.listView);
        title.setText("我种的树");
        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }

        });
        if(type.equals("1"))
        {
            title.setText("我种的树");
            run2();
        }else if(type.equals("2"))
        {
            title.setText("我收藏的树");
            run3();
        }
          run4();
    }
    private void run4()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//+ item点击事件监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MytreeListActivity.this, MapActivity.class);
                intent.putExtra("lon",mytreelist.get(position).getLon());
                intent.putExtra("lat",mytreelist.get(position).getLat());
                intent.putExtra("id",mytreelist.get(position).getId());
                intent.putExtra("hot",mytreelist.get(position).getHot());
                startActivity(intent);
            }
        });
    }
    private void run3()
    {
        sp=getSharedPreferences("test", MODE_PRIVATE);
        useid=sp.getInt("useid",0);
        RequestParams params = new RequestParams(MytreeListActivity.this);
        params.addFormDataPart("userid",useid);
        params.addFormDataPart("screenCondition",1);
        params.addFormDataPart("orderNum",1);
        params.addFormDataPart("orderMethod",1);
        params.addFormDataPart("keyword","");
        params.addFormDataPart("pageNum",1);
        params.addFormDataPart("pageSize",10);
        HttpRequest.post(Constant.getMyCollectInviList, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTTTdsaf",jsonObject.toString());
                MyinvilistBean myinvilistBean = JSONObject.parseObject(jsonObject.toString(),MyinvilistBean.class);
                mytreelist = myinvilistBean.getInviList().getDatas();
                adapter = new MyinvilistAdapter(MytreeListActivity.this, mytreelist);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            }
            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                Log.e("alertMsg failure", errorCode + msg);
            }
            @Override
            public void onStart() {
                super.onStart();
                //show  progressdialog
            }
            @Override
            public void onFinish() {
                super.onFinish();
                //hide progressdialog
            }
        });

    }
    private void run2()
    {
        sp=getSharedPreferences("test", MODE_PRIVATE);
        useid=sp.getInt("useid",0);
        RequestParams params = new RequestParams(MytreeListActivity.this);
        params.addFormDataPart("userid",useid);
        params.addFormDataPart("screenCondition",1);
        params.addFormDataPart("orderNum",1);
        params.addFormDataPart("orderMethod",1);
        params.addFormDataPart("keyword","");
        params.addFormDataPart("pageNum",1);
        params.addFormDataPart("pageSize",10);
        HttpRequest.post(Constant.getMyInviList, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTTTdsaf",jsonObject.toString());
                MyinvilistBean myinvilistBean = JSONObject.parseObject(jsonObject.toString(),MyinvilistBean.class);
                mytreelist = myinvilistBean.getInviList().getDatas();
                adapter = new MyinvilistAdapter(MytreeListActivity.this, mytreelist);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            }
            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                Log.e("alertMsg failure", errorCode + msg);
            }
            @Override
            public void onStart() {
                super.onStart();
                //show  progressdialog
            }
            @Override
            public void onFinish() {
                super.onFinish();
                //hide progressdialog
            }
        });

    }
    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }
}
