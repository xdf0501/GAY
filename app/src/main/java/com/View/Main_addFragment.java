package com.View;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.Bean.InformationBean;
import com.Bean.SignBean;
import com.Constant;
import com.alibaba.fastjson.JSONObject;
import com.appbase.BaseFragment;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.gay.MainActivity;
import com.gay.R;
import com.gay.TreeActivity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.View.Main_addFragment;
import com.View.Main_messageFragment;
import com.View.Main_mineFragment;
import com.View.Main_pageFragment;
import com.View.Main_shopFragment;
import com.appbase.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.List;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


/**
 * Created by xiaomei on 2016/12/8.
 * 位置首页
 */

public class Main_addFragment extends BaseFragment implements HttpCycleContext {

    public static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 1;
    private Context context;//MainActivity的context
    View conuntView;//View
    private LinearLayout writer;
    private CallBackValue callBackValue;
    MainActivity activity8;
    private TextView time;
    private TextView time2;
    private LinearLayout qiandao;
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private SharedPreferences sp=null;// 存放用户信息
    private int useid;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        conuntView = View.inflate(getActivity(), R.layout.activity_add, null);
        conuntView.setLayoutParams(params);
        writer=(LinearLayout) conuntView.findViewById(R.id.fatie);
        time=(TextView)conuntView.findViewById(R.id.textView);
        time2=(TextView)conuntView.findViewById(R.id.textView2);
        qiandao=(LinearLayout)conuntView.findViewById(R.id.qiandao);



        run();
        return conuntView;

    }
    // 获取系统当前时间
    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        String mWay = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        String created = calendar.get(Calendar.YEAR) + "年"
                + (calendar.get(Calendar.MONTH) + 1) + "月"
                + calendar.get(Calendar.DAY_OF_MONTH) + "日";
        return created;
    }
    // 获取系统当前时间
    public static String getTime2() {
        Calendar calendar = Calendar.getInstance();
        String mWay = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        String created ="星期" + mWay;
        return created;
    }

    private void run()
    {

        writer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), TreeActivity.class);
                Bundle bundle = new Bundle();
                String str1 = "aaaaaa";
                bundle.putString("str1", str1);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);//这里采用startActivityForResult来做跳转，此处的0为一个依据，可以写其他的值，但一定要>=0
            }
        });
        time.setText(getTime());
        time2.setText(getTime2());
        qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run2();

            }
        });
    }
    private void run2()
    {
        sp=getActivity().getSharedPreferences("test", MODE_PRIVATE);
        useid=sp.getInt("useid",0);
        Log.e("AFDDSA",useid+"");
        RequestParams params = new RequestParams(Main_addFragment.this);
        params.addFormDataPart("uid",useid);
        HttpRequest.post(Constant.getsign, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTTTT",jsonObject.toString());
                SignBean signBean = JSONObject.parseObject(jsonObject.toString(), SignBean.class);
                Toast.makeText(getActivity(), signBean.getMsg() ,Toast.LENGTH_LONG).show();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
            {
                Bundle b=data.getExtras(); //data为B中回传的Intent
                String str=b.getString("str1");//str即为回传的值
                MainActivity activity=(MainActivity)getActivity();
                android.support.v4.app.FragmentManager fm =null;
                android.support.v4.app.FragmentTransaction ft=null;
                    fm =activity.getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    List<Fragment> fragments = fm.getFragments();
                    if (fragments != null) {
                        for (Fragment frag : fragments) {
                            if(frag!=null) {
                                ft.hide(frag);
                            }
                        }
                    }
                Fragment fg = null;
                fg = fm.findFragmentByTag("0");
                if (fg == null) {
                    fg = new Main_pageFragment();
                    ft.add(R.id.main_pager, fg, "0");
                }
            else {
                    ft.show(fg);
                }
                if (ft != null) {
                    ft.commit();
                }
                activity.tabLayout1.setBackgroundResource(R.color.title);
                activity.tabLayout3.setBackgroundResource(R.mipmap.add_01);
            }

                break;
            default:
                break;
        }
    }

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }


    public interface CallBackValue{

        public void SendMessageValue(String strValue);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        callBackValue =(CallBackValue) getActivity();
    }


}
