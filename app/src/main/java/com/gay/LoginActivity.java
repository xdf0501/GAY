package com.gay;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Bean.LogBean;
import com.Bean.modelBean;
import com.Constant;
import com.alibaba.fastjson.JSONObject;
import com.appbase.BaseActivity;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

/**
 * Created by 狄飞 on 2017/3/12.
 */

public class LoginActivity extends BaseActivity implements HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private EditText account;
    private EditText secret;
    private Button login;
    private TextView zhuce;
    private TextView forgetsecret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        run();
    }


    private void init()
{
    account=(EditText)findViewById(R.id.zhanghao1);
    secret=(EditText)findViewById(R.id.mima1);
    login=(Button)findViewById(R.id.button7);
    zhuce=(TextView)findViewById(R.id.zhuce);
    forgetsecret=(TextView)findViewById(R.id.fogetmima);
    account.setText("920063048@qq.com");
    secret.setText("123456");


}
private void run()
{
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent();
//            intent.setClass(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
           run2();
        }
    });
    zhuce.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, LogonActivity.class);
            intent.putExtra("type", "0");
            startActivity(intent);
        }
    });
   forgetsecret.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent intent = new Intent();
           intent.setClass(LoginActivity.this, LogonActivity.class);
           intent.putExtra("type", "1");
           startActivity(intent);
       }
   });
}
    private void run2()
    {
//
//        Intent intent = new Intent();
//        intent.setClass(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//        toastShow("请输入帐号");
        Toast.makeText(LoginActivity.this, "login", Toast.LENGTH_SHORT).show();
        RequestParams params = new RequestParams(LoginActivity.this);
        params.addFormDataPart("username",account.getText().toString() );
        params.addFormDataPart("password",secret.getText().toString());
        HttpRequest.post(Constant.login, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTTTT",jsonObject.toString());
                LogBean logBean = JSONObject.parseObject(jsonObject.toString(), LogBean.class);
                if(logBean.isStatus())
                {
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    intent.putExtra("useid", logBean.getUid());

                    //实例化SharedPreferences对象（第一步）
                    SharedPreferences mySharedPreferences= getContext().getSharedPreferences("test",
                            Activity.MODE_PRIVATE);
//实例化SharedPreferences.Editor对象（第二步）
                    SharedPreferences.Editor editor = mySharedPreferences.edit();
//用putString的方法保存数据
                    editor.putInt("useid", logBean.getUid());
//提交当前数据
                    editor.commit();
                    startActivity(intent);
                }
                else {
                    toastShow("用户名或密码错误");

                }


//                modelBean.getIsok();
//                Log.e("alertMsg failure", modelBean.getIsok());

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
    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }
}
