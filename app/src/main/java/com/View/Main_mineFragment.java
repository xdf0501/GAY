package com.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Bean.InformationBean;
import com.Constant;
import com.alibaba.fastjson.JSONObject;
import com.appbase.BaseFragment;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;
import com.gay.EveningActivity;
import com.gay.MytreeListActivity;
import com.gay.R;
import com.gay.SchoolActivity;
import com.gay.Tree2Activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

import static android.content.Context.MODE_PRIVATE;
import static com.gay.R.id.cloecttree;


/**
 * Created by xiaomei on 2016/12/8.
 * 位置首页
 */

public class Main_mineFragment extends BaseFragment implements HttpCycleContext {

    public static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 1;
    private Context context;//MainActivity的context
    View conuntView;//View
    private TextView title;
    private TextView shezhi;
    private TextView name;
    private TextView follow;
    private TextView concern;
    private TextView information;
    private TextView home;
    private TextView money;
    private ImageView photo;
    private TextView empiric;
    private Button change;
    private LinearLayout  mytree;
    private LinearLayout collectree;
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private SharedPreferences sp=null;// 存放用户信息
    private int useid;
    private ImageView sex;
    private InformationBean informationBean;









    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        conuntView = View.inflate(getActivity(), R.layout.mine, null);
        conuntView.setLayoutParams(params);
        title=(TextView) conuntView.findViewById(R.id.title_name);
        shezhi=(TextView) conuntView.findViewById(R.id.title_select);
        name=(TextView)conuntView.findViewById(R.id.textView15);
        concern=(TextView)conuntView.findViewById(R.id.textView17);
        follow=(TextView)conuntView.findViewById(R.id.textView16);
        money=(TextView)conuntView.findViewById(R.id.textView35);
        empiric=(TextView)conuntView.findViewById(R.id.textView19);
        photo=(ImageView) conuntView.findViewById(R.id.imageView18);
        change=(Button)conuntView.findViewById(R.id.button6);
        mytree=(LinearLayout)conuntView.findViewById(R.id.mytree);
        home=(TextView)conuntView.findViewById(R.id.textView199);
        information=(TextView)conuntView.findViewById(R.id.textView198);
        sex=(ImageView)conuntView.findViewById(R.id.imageView1);
        collectree=(LinearLayout)conuntView.findViewById(cloecttree);
        shezhi.setVisibility(View.GONE);
        run();
        run2();
//        run3();
        return conuntView;

    }

    private void run3()
    {
        //Bitmap bitmap = getLoacalBitmap("/aa/aa.jpg"); //从本地取图片
        Bitmap bitmap =getHttpBitmap("http://120.76.99.29:8080/SofoWS/files/0.png/0.png/0.png");
//从网上取图片
        photo .setImageBitmap(bitmap);	//设置Bitmap
    }
    public static Bitmap getHttpBitmap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {

            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setConnectTimeout(0);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    private void run2()
    {
        sp=getActivity().getSharedPreferences("test", MODE_PRIVATE);
        useid=sp.getInt("useid",0);
        Log.e("AFDDSA",useid+"");
        RequestParams params = new RequestParams(Main_mineFragment.this);
        params.addFormDataPart("uid",useid);
        HttpRequest.post(Constant.getMineInfo, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTTTT",jsonObject.toString());
                   informationBean = JSONObject.parseObject(jsonObject.toString(), InformationBean.class);
                   name.setText(informationBean.getNickname());
                   concern.setText("关注："+informationBean.getConcern());
                   follow.setText("粉丝："+informationBean.getFollowers());
                   money.setText("金币："+informationBean.getMoney());
                   empiric.setText("LV"+informationBean.getEmpiric());
                   information.setText(informationBean.getBrief());
                   home.setText(informationBean.getLocation());
                if(informationBean.getSex()==0)
                {
                    sex.setImageResource(R.mipmap.boy);
                }
                else {
                    sex.setImageResource(R.mipmap.girl);
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (resultCode)
        {
            case 19:
            {
                run2();
            }
            break;

        }
    }
    public void run()
    {

        title.setText("我的");
        shezhi.setText("设置");

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), EveningActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name1",informationBean.getUsername());
                bundle.putString("name", informationBean.getNickname());
                bundle.putInt("sex",informationBean.getSex());
                bundle.putString("adress",informationBean.getLocation());
                bundle.putString("birthday",informationBean.getBirthday());
                bundle.putString("brief",informationBean.getBrief());
                intent.putExtras(bundle);
                startActivityForResult(intent, 4);//这里采用startActivityForResu

            }
        });
        mytree.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), MytreeListActivity.class);
        intent.putExtra("type","1");
        startActivity(intent);

    }
});
        collectree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MytreeListActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);
            }
        });

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }
}
