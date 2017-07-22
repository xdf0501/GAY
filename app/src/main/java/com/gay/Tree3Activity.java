package com.gay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Bean.BaseBean;
import com.Bean.CollectBean;
import com.Bean.MytieziBean;
import com.Bean.ThemeBean;
import com.Constant;
import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

/**
 * Created by 狄飞 on 2016/11/6.
 */
public class Tree3Activity extends Activity implements HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private ImageView back;
    private TextView title;
    private ImageView headpicture;
    private TextView name;
    private TextView content;
    private TextView topic;
    private TextView time;
    private String treeid;
    private String  schoolname;
    private ImageView shoucang;
    private SharedPreferences sp=null;// 存放用户信息
    private int useid;
    private EditText comment;
    private ImageView fasong;
    private Boolean iscollect;
    private TextView Comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_tree3);
        init();
        run();
        run2();
    }
    private void run4()
    {
        RequestParams params = new RequestParams(Tree3Activity.this);
        params.addFormDataPart("uid",useid);
        params.addFormDataPart("inId",treeid);
        params.addFormDataPart("theme",1);
        params.addFormDataPart("content",comment.getText().toString());
        HttpRequest.post(Constant.addComments, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("nrwrwrx",jsonObject.toString());
               BaseBean comment1 = JSONObject.parseObject(jsonObject.toString(),BaseBean.class);
                if(comment1.isStatus())
                {
                    comment.setText("");
                    InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(comment.getWindowToken(), 0);
                }

            }
            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                Log.e("alertMsg failure", errorCode + msg);
                ;
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
    private void run3()
    {
        RequestParams params = new RequestParams(Tree3Activity.this);
        params.addFormDataPart("userid",useid);
        params.addFormDataPart("invitationid",treeid);
        HttpRequest.post(Constant.collect, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("nrwrwrx",jsonObject.toString());
                CollectBean collectBean = JSONObject.parseObject(jsonObject.toString(),CollectBean.class);
                if(collectBean.isStatus())
                {
                    if(iscollect)
                    {
                        Toast.makeText(Tree3Activity.this, collectBean.getMsg() ,Toast.LENGTH_LONG).show();
                        shoucang.setImageResource(R.mipmap.shoucang2);
                        iscollect=!iscollect;
                    }
                    else {
                        Toast.makeText(Tree3Activity.this, collectBean.getMsg() ,Toast.LENGTH_LONG).show();
                        shoucang.setImageResource(R.mipmap.shoucang1);
                        iscollect=!iscollect;
                    }

                }

            }
            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                Log.e("alertMsg failure", errorCode + msg);
                ;
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
        back=(ImageView)findViewById(R.id.imageView2);
        title=(TextView)findViewById(R.id.title_name1);
        headpicture=(ImageView)findViewById(R.id.imageView11);
        name=(TextView)findViewById(R.id.textView4);
        topic=(TextView)findViewById(R.id.textView);
        content=(TextView)findViewById(R.id.textView6);
        time=(TextView)findViewById(R.id.textView5);
        shoucang=(ImageView)findViewById(R.id.imageView87);
        comment=(EditText)findViewById(R.id.editText2);
        fasong=(ImageView)findViewById(R.id.imageView8);
        Comment=(TextView)findViewById(R.id.Comment);
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(comment.getWindowToken(), 0);
        Intent intent=getIntent();
        treeid=intent.getStringExtra("id");
        schoolname=intent.getStringExtra("school");
        sp=getSharedPreferences("test", MODE_PRIVATE);
        useid=sp.getInt("useid",0);
        Log.e("schoolname",schoolname);
        Log.e("werqwe",treeid);
    }
    private void run2()
    {
        RequestParams params = new RequestParams(Tree3Activity.this);
        params.addFormDataPart("invitationid",treeid);
        params.addFormDataPart("userid",useid);
        HttpRequest.get(Constant.getThemeInvitation+"?", params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("ncvnbx",jsonObject.toString());
                ThemeBean themeBean = JSONObject.parseObject(jsonObject.toString(),ThemeBean.class);
                if(themeBean.isStatus())
                {
                    name.setText(schoolname);
                    topic.setText(themeBean.getThemeInvitation().getTopic());
                    content.setText("       "+themeBean.getThemeInvitation().getContent());
                    DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    time.setText(format.format(themeBean.getThemeInvitation().getCreatetime()));
                    title.setText("校园主题帖");
                    if(themeBean.isIfCollect())
                    {
                        shoucang.setImageResource(R.mipmap.shoucang1);
                        iscollect=themeBean.isIfCollect();
                    }
                    else {
                        shoucang.setImageResource(R.mipmap.shoucang2);
                        iscollect=themeBean.isIfCollect();
                    }



                }
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

    private void run()
    {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run3();

            }
        });
        fasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run4();

            }
        });
        Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tree3Activity.this, CommentActivity.class);
                intent.putExtra("treeid",treeid);
                startActivity(intent);

            }
        });
    }

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }
}
