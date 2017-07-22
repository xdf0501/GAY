package com.gay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Bean.LogBean;
import com.Bean.emailBean;
import com.Constant;
import com.alibaba.fastjson.JSONObject;
import com.appbase.BaseActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

import static com.gay.R.id.secret;

/**
 * Created by 狄飞 on 2017/3/12.
 */

public class LogonActivity extends BaseActivity implements HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private String type;
    private TextView title;
    private EditText email1;
    private EditText code2;
    private EditText secret1;
    private EditText secret3;
    private TextView send;
    private TextView ok;
    private String password="";
    private int code;
    private Timer timer = null;
    private GPSReplayTimerTask task = null;
    private int runtime = 1000;
    private int timeback=60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        init();

    }
        private void run2()
    {

        if(secret1.getText().toString().equals(secret3.getText().toString()))
        {
            if(secret1.getText().equals(""))
            {
                Toast.makeText(getApplicationContext(),"密码不能为空", Toast.LENGTH_SHORT).show();
            }
            else {
                password=secret1.getText().toString();
                RequestParams params = new RequestParams(LogonActivity.this);
                params.addFormDataPart("username",email1.getText().toString());
                params.addFormDataPart("password",password);
                params.addFormDataPart("code",code2.getText().toString());
                params.addFormDataPart("type",type);
                HttpRequest.post(Constant.register, params, new JsonHttpRequestCallback() {
                    @Override
                    protected void onSuccess(Headers headers, JSONObject jsonObject) {
                        super.onSuccess(headers, jsonObject);
                        Log.e("TTTTTgdfgfd",jsonObject.toString());
                        emailBean emailBean2= JSONObject.parseObject(jsonObject.toString(), emailBean.class);
                        if(emailBean2.isStatus())
                        {
                            finish();
                        }
                        Toast.makeText(getApplicationContext(),emailBean2.getMessage(), Toast.LENGTH_SHORT).show();


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

        }
        else {
            Toast.makeText(getApplicationContext(),"两次输入的密码不同", Toast.LENGTH_SHORT).show();
        }


    }
    private void init()
    {
        title=(TextView)findViewById(R.id.title_name1);
        email1=(EditText)findViewById(R.id.email1);
        email1.setText("@qq.com");
        code2=(EditText)findViewById(R.id.code2);
        secret1=(EditText)findViewById(R.id.secret1);
        secret3=(EditText)findViewById(R.id.secret3);
        send=(Button)findViewById(R.id.send);
        ok=(Button)findViewById(R.id.ok);
        Intent intent = getIntent();
        type=intent.getStringExtra("type");
        if(type.equals("0"))
        {
            title.setText("注册");
        }
        else if(type.equals("1")){
            title.setText("修改密码");
        }
        send.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(timeback==0)
            {
                timeback=60;
            }
            if(timeback==60)
            {
                run3();
            }
            else {
                Toast.makeText(getApplicationContext(),timeback+"秒之后再发送验证码", Toast.LENGTH_SHORT).show();
            }

        }
    });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          run2();

            }
        });
    }
    public class GPSReplayTimerTask extends TimerTask {
        public LogonActivity activity;

        public GPSReplayTimerTask(LogonActivity act) {
            activity = act;
        }

        @Override
        public void run() {
        }
    }
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 98:
                {
                    send.setText(timeback+"秒");
                    Log.e("POPO",timeback+"");

                    if(timeback==0)
                    {
                        send.setText("发送验证码");
                    }
                }
                    break;
            }
        }
    };
    public void startTimer()
    {
        if (timer == null) {
            timer = new Timer();
            task = new GPSReplayTimerTask(LogonActivity.this) {
                @Override
                public void run() {
                    if(timeback>0)
                    {

                        handler.sendEmptyMessage(98);
                        timeback--;
                    }


                }
            };
            timer.schedule(task, runtime, runtime);
            /*
			 * 第一个参数"new MyTask(event.getServletContext())": 是 TimerTask
			 * 类，在包：import java.util.TimerTask .使用者要继承该类，并实现 public void run()
			 * 方法，因为 TimerTask 类实现了 Runnable 接口。 第二个参数"0"的意思是:(0就表示无延迟)
			 * 当你调用该方法后，该方法必然会调用 TimerTask 类 TimerTask 类 中的 run()
			 * 方法，这个参数就是这两者之间时间的差值，也就是说，用户调用 schedule() 方法后，要等待这么长的时间才可以第一次执行
			 * run() 方法。 第三个参数"60*60*1000"的意思就是: (单位是毫秒60*60*1000为一小时)
			 * (单位是毫秒3*60*1000为三分钟) 第一次调用之后，从第二次开始每隔多长的时间调用一次 run() 方法。
			 */
        }
    }
    public void stopTimer() {
        if (timer != null) {
            task.cancel();
            timer.cancel();

            task = null;
            timer = null;
        }
    }

    private void run3()
    {

        RequestParams params = new RequestParams(LogonActivity.this);
        params.addFormDataPart("username",email1.getText().toString());
        params.addFormDataPart("type",type);
        HttpRequest.post(Constant.mail, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTTTT",jsonObject.toString());
                emailBean emailBean= JSONObject.parseObject(jsonObject.toString(), emailBean.class);
                Toast.makeText(getApplicationContext(),emailBean.getMessage(), Toast.LENGTH_SHORT).show();
                startTimer();
            }
            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                Log.e("alertMsg failure", errorCode + msg);;
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
////        sendeMail

    }
    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }
}
