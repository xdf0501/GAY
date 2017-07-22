package com.gay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Bean.SchoolBean;
import com.Constant;
import com.View.Main_pageFragment;
import com.adapter.SchoolAdapter;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

/**
 * Created by 狄飞 on 2016/12/20.
 */
public class SchoolActivity extends Activity  implements HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private TextView title;
    private TextView shezhi;
    private ListView listView;
    private SchoolAdapter adapter;
    List<SchoolBean.ListsBean.DatasBean> childList = new ArrayList<SchoolBean.ListsBean.DatasBean>();
    RelativeLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        init();
        run();
        run2();
    }
    private void run2()
    {
        RequestParams params = new RequestParams(SchoolActivity.this);
        params.addFormDataPart("pageNum",1);
        params.addFormDataPart("pageSize",10);
        //  地址  参数  回调函数
        HttpRequest.get(Constant.getSchoolLists+"?", params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("FASD------->", jsonObject.toString());
                SchoolBean schoolBean = JSONObject.parseObject(jsonObject.toString(), SchoolBean.class);
                childList = schoolBean.getLists().getDatas();
                adapter = new SchoolAdapter(SchoolActivity.this, childList);
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
    private void init()
    {

        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        title=(TextView)findViewById(R.id.title_name);
        shezhi=(TextView)findViewById(R.id.title_select);
        back=(RelativeLayout)findViewById(R.id.back);
    }
    public void run()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//+ item点击事件监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                System.out.println("position" + position);
                childList.get(position).getSchoolId();
                Intent intent = new Intent(SchoolActivity.this, Main_pageFragment.class);
                intent.putExtra("schoolid", childList.get(position).getSchoolId());
                intent.putExtra("lon", childList.get(position).getSchoolLon());
                intent.putExtra("lat", childList.get(position).getSchoolLat());
                intent.putExtra("schoolname",childList.get(position).getSchoolName());
                setResult(20, intent);
                finish();


            }
        });
        title.setText("学校");
        shezhi.setText("确定");
        shezhi.setVisibility(View.GONE);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }

        });
        shezhi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("ok", "ok");
                setResult(RESULT_CANCELED, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                finish();
            }

        });
    }

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }
}
