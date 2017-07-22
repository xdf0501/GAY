package com.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.Bean.BuyBean;
import com.Bean.MyskinBean;
import com.Bean.ShopBean;
import com.Constant;
import com.alibaba.fastjson.JSONObject;
import com.appbase.BaseFragment;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.gay.R;
import com.gay.SchoolActivity;

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

public class Main_shopFragment extends BaseFragment implements HttpCycleContext {

    public static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 1;
    private Context context;//MainActivity的context
    View conuntView;//View
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private SharedPreferences sp=null;// 存放用户信息
    private int useid;
    private ImageView tree1;
    private TextView treename1;
    private ImageView tree2;
    private TextView treename2;
    private ImageView tree3;
    private TextView treename3;
    private ImageView tree4;
    private TextView treename4;
    private ImageView tree5;
    private TextView treename5;
    private ImageView tree6;
    private TextView treename6;
    private ImageView tree7;
    private TextView treename7;
    private ImageView tree8;
    private TextView treename8;
    ShopBean shopBean;
    private Button button;
    private Button button2;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        conuntView = View.inflate(getActivity(), R.layout.shop, null);
        conuntView.setLayoutParams(params);
        tree1=(ImageView)conuntView.findViewById(R.id.imageView1);
        treename1=(TextView)conuntView.findViewById(R.id.text1);
        tree2=(ImageView)conuntView.findViewById(R.id.imageView2);
        treename2=(TextView)conuntView.findViewById(R.id.text2);
        tree3=(ImageView)conuntView.findViewById(R.id.imageView3);
        treename3=(TextView)conuntView.findViewById(R.id.text3);
        tree4=(ImageView)conuntView.findViewById(R.id.imageView4);
        treename4=(TextView)conuntView.findViewById(R.id.text4);
        tree5=(ImageView)conuntView.findViewById(R.id.imageView5);
        treename5=(TextView)conuntView.findViewById(R.id.text5);
        tree6=(ImageView)conuntView.findViewById(R.id.imageView6);
        treename6=(TextView)conuntView.findViewById(R.id.text6);
        tree7=(ImageView)conuntView.findViewById(R.id.imageView7);
        treename7=(TextView)conuntView.findViewById(R.id.text7);
        tree8=(ImageView)conuntView.findViewById(R.id.imageView8);
        treename8=(TextView)conuntView.findViewById(R.id.text8);
        button=(Button)conuntView.findViewById(R.id.button);
        button2=(Button)conuntView.findViewById(R.id.button2);
        run();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tree1.setVisibility(View.VISIBLE);
                tree2.setVisibility(View.VISIBLE);
                tree3.setVisibility(View.VISIBLE);
                tree4.setVisibility(View.VISIBLE);
                tree5.setVisibility(View.VISIBLE);
                tree6.setVisibility(View.VISIBLE);
                tree7.setVisibility(View.VISIBLE);
                tree8.setVisibility(View.VISIBLE);
                run();
            }
        });

        tree1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopBean.getData().size()>=1)
                {
                    showDialog("是否花"+shopBean.getData().get(0).getSkinPrice()+"购买"+shopBean.getData().get(0).getSkinName(),"",
                           "", 0);
                }

            }
        });
        tree2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopBean.getData().size()>=2)
                {
                    showDialog("是否花"+shopBean.getData().get(1).getSkinPrice()+"购买"+shopBean.getData().get(1).getSkinName(),"",
                            "", 1);
                }
            }
        });
        tree3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopBean.getData().size()>=3)
                {
                    showDialog("是否花"+shopBean.getData().get(2).getSkinPrice()+"购买"+shopBean.getData().get(2).getSkinName(),"",
                            "", 2);
                }
            }
        });
        tree4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopBean.getData().size()>=4)
                {
                    showDialog("是否花"+shopBean.getData().get(3).getSkinPrice()+"购买"+shopBean.getData().get(3).getSkinName(),"",
                            "",3);
                }
            }
        });
        tree5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopBean.getData().size()>=5)
                {
                    showDialog("是否花"+shopBean.getData().get(4).getSkinPrice()+"购买"+shopBean.getData().get(4).getSkinName(),"",
                            "", 4);
                }
            }
        });
        tree6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopBean.getData().size()>=6)
                {
                    showDialog("是否花"+shopBean.getData().get(5).getSkinPrice()+"购买"+shopBean.getData().get(5).getSkinName(),"",
                            "",5);
                }
            }
        });
        tree7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopBean.getData().size()>=7)
                {
                    showDialog("是否花"+shopBean.getData().get(6).getSkinPrice()+"购买"+shopBean.getData().get(6).getSkinName(),"",
                            "", 6);
                }
            }
        });
        tree8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopBean.getData().size()>=8)
                {
                    showDialog("是否花"+shopBean.getData().get(7).getSkinPrice()+"购买"+shopBean.getData().get(7).getSkinName(),"",
                            "", 7);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tree1.setVisibility(View.GONE);
                tree2.setVisibility(View.GONE);
                tree3.setVisibility(View.GONE);
                tree4.setVisibility(View.GONE);
                tree5.setVisibility(View.GONE);
                tree6.setVisibility(View.GONE);
                tree7.setVisibility(View.GONE);
                tree8.setVisibility(View.GONE);
                treename1.setText("");
                treename2.setText("");
                treename3.setText("");
                treename4.setText("");
                treename5.setText("");
                treename6.setText("");
                treename7.setText("");
                treename8.setText("");
                run2();
            }
        });
        return conuntView;

    }
  private  void showDialog(final String messageStr, final String useridStr, final String customerStr ,final int dialogFlag){
        final AlertDialog.Builder builder = new AlertDialog.Builder(
               getContext());

        builder.setMessage(messageStr);
        builder.setPositiveButton("是",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                RequestParams params = new RequestParams(Main_shopFragment.this);
                params.addFormDataPart("uid",useid=sp.getInt("useid",0));
                params.addFormDataPart("skinId",shopBean.getData().get(dialogFlag).getSkinId());
                params.addFormDataPart("money",shopBean.getData().get(dialogFlag).getSkinPrice());
                HttpRequest.post(Constant.buySkins, params, new JsonHttpRequestCallback() {
                    @Override
                    protected void onSuccess(Headers headers, JSONObject jsonObject) {
                        super.onSuccess(headers, jsonObject);
                        Log.e("-1222-------->", jsonObject.toString());
                       BuyBean buyBean = JSONObject.parseObject(jsonObject.toString(), BuyBean.class);
                        Toast.makeText(getContext(), buyBean.getMsg(),
                                Toast.LENGTH_SHORT).show();

                    }



                    @Override
                    public void onFailure(int errorCode, String msg) {
                        super.onFailure(errorCode, msg);
                        Log.e("alertMsg failure", errorCode + msg);

                    }

                    @Override
                    public void onStart() {
                        super.onStart();

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                        //hide progressdialog
                    }
                });


            }
        });
        builder.setNegativeButton("否",null);
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void run()
    {
        sp=getActivity().getSharedPreferences("test", MODE_PRIVATE);
        RequestParams params = new RequestParams(Main_shopFragment.this);
        HttpRequest.post(Constant.showSkins, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTTTT",jsonObject.toString());
                shopBean = JSONObject.parseObject(jsonObject.toString(), ShopBean.class);

                for (int i=0;i<shopBean.getData().size();i++)
                {
                    int bd=0;
                    if(shopBean.getData().get(i).getSkinId()==1)
                    {
                        bd = R.mipmap.theme1;
                    }
                    else if(shopBean.getData().get(i).getSkinId()==2)
                    {
                        bd = R.mipmap.theme2;
                    }
                    else if(shopBean.getData().get(i).getSkinId()==3)
                    {
                        bd = R.mipmap.theme3;
                    }
                    else if(shopBean.getData().get(i).getSkinId()==4)
                    {
                        bd = R.mipmap.theme4;
                    }
                    else if(shopBean.getData().get(i).getSkinId()==5)
                    {
                        bd = R.mipmap.theme5;
                    }
                    else if(shopBean.getData().get(i).getSkinId()==6)
                    {
                        bd = R.mipmap.shoptree;
                    }
                    else if(shopBean.getData().get(i).getSkinId()==7)
                    {
                        bd = R.mipmap.shoptree;
                    }
                    else if(shopBean.getData().get(i).getSkinId()==8)
                    {
                        bd = R.mipmap.shoptree;
                    }
                    if(i==0)
                    {


                        tree1.setImageResource(bd);
                        treename1.setText(shopBean.getData().get(i).getSkinName());
                    }
                    else if(i==1)
                    {
                        tree2.setImageResource(bd);
                        treename2.setText(shopBean.getData().get(i).getSkinName());
                    }
                    else if(i==2)
                    {
                        tree3.setImageResource(bd);
                        treename3.setText(shopBean.getData().get(i).getSkinName());
                    }
                    else if(i==3)
                    {
                        tree4.setImageResource(bd);
                        treename4.setText(shopBean.getData().get(i).getSkinName());
                    }
                    else if(i==4)
                    {
                        tree5.setImageResource(bd);
                        treename5.setText(shopBean.getData().get(i).getSkinName());
                    }
                    else if(i==5)
                    {
                        tree6.setImageResource(bd);
                        treename6.setText(shopBean.getData().get(i).getSkinName());
                    }
                    else if(i==6)
                    {
                        tree7.setImageResource(bd);
                        treename7.setText(shopBean.getData().get(i).getSkinName());
                    }
                    else if(i==7)
                    {
                        tree8.setImageResource(bd);
                        treename8.setText(shopBean.getData().get(i).getSkinName());
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

    private void run2()
    {
        sp=getActivity().getSharedPreferences("test", MODE_PRIVATE);
        RequestParams params = new RequestParams(Main_shopFragment.this);
        params.addFormDataPart("userid",useid);
        params.addFormDataPart("pageNum",1);
        params.addFormDataPart("pageSize",10);
        HttpRequest.post(Constant.getSkinListByuserid, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTTTT",jsonObject.toString());
                MyskinBean myskinBean = JSONObject.parseObject(jsonObject.toString(), MyskinBean.class);
                for(int i=0;i<myskinBean.getSkinList().getDatas().size();i++)
               {
                   int bd=0;
                   if(myskinBean.getSkinList().getDatas().get(i).getSkinId()==1)
                   {
                       bd = R.mipmap.theme1;
                   }
                   else if(myskinBean.getSkinList().getDatas().get(i).getSkinId()==2)
                   {
                       bd = R.mipmap.theme2;
                   }
                   else if(myskinBean.getSkinList().getDatas().get(i).getSkinId()==3)
                   {
                       bd = R.mipmap.theme3;
                   }
                   else if(myskinBean.getSkinList().getDatas().get(i).getSkinId()==4)
                   {
                       bd = R.mipmap.theme4;
                   }
                   else if(myskinBean.getSkinList().getDatas().get(i).getSkinId()==5)
                   {
                       bd = R.mipmap.theme5;
                   }
                   else if(myskinBean.getSkinList().getDatas().get(i).getSkinId()==6)
                   {
                       bd = R.mipmap.shoptree;
                   }
                   else if(myskinBean.getSkinList().getDatas().get(i).getSkinId()==7)
                   {
                       bd = R.mipmap.shoptree;
                   }
                       else if(myskinBean.getSkinList().getDatas().get(i).getSkinId()==8)
                  {
                   bd = R.mipmap.shoptree;
                  }
                   if(i==0)
                   {
                       tree1.setVisibility(View.VISIBLE);
                       tree1.setImageResource(bd);
                       treename1.setText(myskinBean.getSkinList().getDatas().get(i).getSkinName());
                    }
                   else if(i==1)
                   {
                       tree2.setVisibility(View.VISIBLE);
                       tree2.setImageResource(bd);
                       treename2.setText(myskinBean.getSkinList().getDatas().get(i).getSkinName());
                   }
                   else if(i==2)
                   {
                       tree3.setVisibility(View.VISIBLE);
                       tree3.setImageResource(bd);
                       treename3.setText(myskinBean.getSkinList().getDatas().get(i).getSkinName());
                   }
                   else if(i==3)
                   {
                       tree4.setVisibility(View.VISIBLE);
                       tree4.setImageResource(bd);
                       treename4.setText(myskinBean.getSkinList().getDatas().get(i).getSkinName());
                   }
                   else if(i==4)
                   {
                       tree5.setVisibility(View.VISIBLE);
                       tree5.setImageResource(bd);
                       treename5.setText(myskinBean.getSkinList().getDatas().get(i).getSkinName());
                   }
                   else if(i==5)
                   {
                       tree6.setVisibility(View.VISIBLE);
                       tree6.setImageResource(bd);
                       treename6.setText(myskinBean.getSkinList().getDatas().get(i).getSkinName());
                   }
                   else if(i==6)
                   {
                       tree7.setVisibility(View.VISIBLE);
                       tree7.setImageResource(bd);
                       treename7.setText(myskinBean.getSkinList().getDatas().get(i).getSkinName());
                   }
                   else if(i==7)
                   {
                       tree8.setVisibility(View.VISIBLE);
                       tree8.setImageResource(bd);
                       treename8.setText(myskinBean.getSkinList().getDatas().get(i).getSkinName());
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
