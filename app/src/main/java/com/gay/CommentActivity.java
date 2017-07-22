package com.gay;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Bean.CommenallBean;
import com.Bean.MyinvilistBean;
import com.Constant;
import com.adapter.CommentallAdapter;
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

public class CommentActivity extends Activity implements HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private SharedPreferences sp=null;// 存放用户信息
    private int useid;
    private CommentallAdapter adapter;
    private ListView listView;
    List<CommenallBean.CommentListBean.DatasBean> commentlist = new ArrayList<CommenallBean.CommentListBean.DatasBean>();
    private TextView title;
    private ImageView back;
    private String type="0";
    private String treeid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytree);
        title=(TextView)findViewById(R.id.title_name1);
        back=(ImageView)findViewById(R.id.imageView2);
        listView=(ListView)findViewById(R.id.listView);
        title.setText("评论");
        Intent intent=getIntent();
        treeid=intent.getStringExtra("treeid");
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }

        });
            run2();


    }



    private void run2()
    {
        sp=getSharedPreferences("test", MODE_PRIVATE);
        useid=sp.getInt("useid",0);
        RequestParams params = new RequestParams(CommentActivity.this);
        params.addFormDataPart("invitationid",treeid);
        params.addFormDataPart("orderNum",0);
        params.addFormDataPart("pageNum",1);
        params.addFormDataPart("pageSize",10);
        HttpRequest.post(Constant.getCommentList, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTSDFaf",jsonObject.toString());
                CommenallBean commenallBean = JSONObject.parseObject(jsonObject.toString(),CommenallBean.class);
                commentlist = commenallBean.getCommentList().getDatas();
                adapter = new CommentallAdapter(CommentActivity.this, commentlist);
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
