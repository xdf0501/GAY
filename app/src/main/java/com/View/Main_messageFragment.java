package com.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.Bean.CommentBean;
import com.Bean.InformationBean;
import com.Bean.SchoolBean;
import com.Constant;
import com.adapter.CommentAdapter;
import com.adapter.SchoolAdapter;
import com.alibaba.fastjson.JSONObject;
import com.appbase.BaseFragment;
import com.gay.R;
import com.gay.SchoolActivity;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by xiaomei on 2016/12/8.
 * 位置首页
 */

public class Main_messageFragment extends BaseFragment implements HttpCycleContext {

    public static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 1;
    private Context context;//MainActivity的context
    View conuntView;//View
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private SharedPreferences sp=null;// 存放用户信息
    private int useid;
    private Button choose;
    private int choose1=0;
    List<CommentBean.CommentListBean.DatasBean> commentList = new ArrayList<CommentBean.CommentListBean.DatasBean>();
    private CommentAdapter adapter;
    private ListView listView;
    private int ismy=0;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        conuntView = View.inflate(getActivity(), R.layout.message, null);
        conuntView.setLayoutParams(params);
        choose=(Button)conuntView.findViewById(R.id.button3);
        listView=(ListView)conuntView.findViewById(R.id.listView);
        choose1=0;
        ismy=1;
        run2(ismy);
        run();

        return conuntView;

    }
    private void run()
    {

        choose.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(choose1==0)
        {
            choose.setText("评论我的");
            choose1=1;
            ismy=2;
            run2(ismy);
        }
        else {
            choose.setText("我评论的");
            choose1=0;
            ismy=1;
            run2(ismy);
        }

    }
});
    }

    private void run2(int kind)
    {
        sp=getActivity().getSharedPreferences("test", MODE_PRIVATE);
        useid=sp.getInt("useid",0);
        RequestParams params = new RequestParams(Main_messageFragment.this);
        params.addFormDataPart("userid",useid);
        params.addFormDataPart("kind",kind);
        params.addFormDataPart("pageNum",1);
        params.addFormDataPart("pageSize",10);
        HttpRequest.post(Constant.getCommentAboutMeList, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTTTT",jsonObject.toString());
                CommentBean commentBean = JSONObject.parseObject(jsonObject.toString(),CommentBean.class);
                commentList = commentBean.getCommentList().getDatas();
                adapter = new CommentAdapter(getContext(), commentList);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }
}
