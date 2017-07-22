package com.gay;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Bean.BaseBean;
import com.Bean.InformationBean;
import com.Constant;
import com.View.Main_mineFragment;
import com.alibaba.fastjson.JSONObject;

import java.util.Calendar;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

/**
 * Created by 狄飞 on 2017/4/8.
 */

public class EveningActivity extends Activity implements HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private EditText name;
    private TextView sex;
    private EditText adress;
    private TextView birthday;
    private String nickname;
    private String name1string;
    private String adressstring;
    private String birthdaystring;
    private String bridftring;
    private int sex2;
    private TextView name1;
    private EditText brief;
    private TextView title;
    private RelativeLayout back;
    private TextView sure;
    private SharedPreferences sp=null;// 存放用户信息
    private int useid;
    private PopupWindow popupWindow;
    private Context mContext = null;
    private TextView save1;
    private TextView cancel;
    private RadioGroup group1;
    private int sex3;
    private int start_y = 1970;
    private int start_m = 0;
    private int start_d = 0;
    private int nian=1970;
    private int yue=0;
    private int ri=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evening);
        mContext = this;
        sp=getSharedPreferences("test", MODE_PRIVATE);
        useid=sp.getInt("useid",0);
        Intent intent = getIntent();
        nickname = intent.getStringExtra("name1");
        name1string = intent.getStringExtra("name");
        sex2 = intent.getIntExtra("sex",0);
        adressstring = intent.getStringExtra("adress");
        birthdaystring = intent.getStringExtra("birthday");
        bridftring= intent.getStringExtra("brief");
        init();

    }
    private void run2()
    {
        RequestParams params = new RequestParams(EveningActivity.this);
        params.addFormDataPart("uid",useid);
        params.addFormDataPart("nickname",name.getText().toString());
        params.addFormDataPart("sex",sex2);
        params.addFormDataPart("location",adress.getText().toString());
        params.addFormDataPart("birthday",birthday.getText().toString());
        params.addFormDataPart("brief",brief.getText().toString());
        HttpRequest.post(Constant.improveinfo, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTTTT",jsonObject.toString());
                BaseBean improve = JSONObject.parseObject(jsonObject.toString(),BaseBean.class);
                if(improve.isStatus())
                {
                    Intent intent = new Intent();
                    intent.putExtra("ok", "ok");
                    setResult(19, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                    finish();
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
    private void init()
    {
        name=(EditText)findViewById(R.id.textView20);
        sex=(TextView)findViewById(R.id.textView17);
        adress=(EditText)findViewById(R.id.textView18);
        birthday=(TextView)findViewById(R.id.textView19);
        name1=(TextView)findViewById(R.id.textView16);
        brief=(EditText)findViewById(R.id.geren);
        back=(RelativeLayout)findViewById(R.id.back);
        title=(TextView)findViewById(R.id.title_name);
        sure=(TextView)findViewById(R.id.title_select);
        title.setText("基本信息");
        sure.setText("确定");
        name.setText(name1string);
        sex3=sex2;
        if(sex2==1)
        {
            sex.setText("女");
        }else
        {
            sex.setText("男");
        }
        adress.setText(adressstring);
        birthday.setText(birthdaystring);
        name1.setText(nickname);
        brief.setText(bridftring);
        if(birthdaystring.equals(""))
        {}
        else {
            nian=Integer.valueOf(birthdaystring.substring(0,4));
            yue=Integer.valueOf(birthdaystring.substring(5,7));
            ri=Integer.valueOf(birthdaystring.substring(8,10));
            Log.e("ZENMEHUIS",nian+"-"+yue+"-"+ri);
        }
        birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
                new DatePickerDialog(EveningActivity.this,
                        // 绑定监听器
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                start_y = year;
                                start_m = monthOfYear + 1;
                                start_d = dayOfMonth;
                                if(start_m<10&&start_d>=10)
                                {
                                    birthday.setText(start_y + "-0" + start_m + "-" + start_d );
                                }
                                else if(start_m<10&&start_d<10)
                                {
                                    birthday.setText(start_y + "-0" + start_m + "-0" + start_d );
                                } else if(start_m>=10&&start_d<10)
                                {
                                    birthday.setText(start_y + "-" + start_m + "-0" + start_d );
                                }else {
                                    birthday.setText(start_y + "-" + start_m + "-" + start_d );
                                }
                            }
                        },
                        // 设置初始日期
                       nian,yue-1,ri).show();
                       nian=start_y;
                      yue=start_m;
                       ri=start_d;
            }

        });
        back.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {
               finish();
           }

       });
        sure.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
          run2();
        }

    });

        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popView = LayoutInflater.from(mContext).inflate(
                        R.layout.pop_window2, null);
                View rootView = findViewById(R.id.root_main2); // 當前頁面的根佈局
                popupWindow = new PopupWindow(popView,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                // 设置弹出动画
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setFocusable(true);
                popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
                popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                // 顯示在根佈局的底部
                popupWindow.showAtLocation(rootView, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
                cancel = (TextView) popView.findViewById(R.id.textView5);
                save1 = (TextView) popView.findViewById(R.id.textView6);
                group1=(RadioGroup)popView.findViewById(R.id.group);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "未保存修改",
                                Toast.LENGTH_SHORT).show();
                        sex2=sex3;
                        popupWindow.dismiss();
                    }
                });
                if(sex2==1)
                {
                    group1.check(R.id.radioButton1);
                }else
                {
                    group1.check(R.id.radioButton);
                }
                group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.radioButton:
                            {
                                group1.check(R.id.radioButton);
                                sex2=0;
                            }
                            break;
                            case R.id.radioButton1:
                            {
                                group1.check(R.id.radioButton1);
                                sex2=1;
                            }
                            break;
                        }
                    }
                });
                save1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        if(sex2==1)
                        {
                            sex.setText("女");
                        }else
                        {
                            sex.setText("男");
                        }

                    }
                });
            }
        });
    }

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }
}
