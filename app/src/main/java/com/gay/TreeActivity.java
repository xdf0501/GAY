package com.gay;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Bean.BaseBean;
import com.Bean.LogBean;
import com.Constant;
import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

import static android.R.attr.x;

/**
 * Created by 狄飞 on 2016/11/6.
 */
public class TreeActivity extends Activity implements HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private ImageView photo;
    private RelativeLayout back;
    private TextView title;
    private TextView seed;
    private ImageView type2;
    private  String imageString="";
    private Context mContext = null;
    private ImageView phototext;
    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;   //这里的IMAGE_CODE是自己任意定义的
    private String path;
    private TextView type;
    private TextView save1;
    private TextView cancel;
    private TextView ri;
    private TextView xingqi;
    private PopupWindow popupWindow;
    private RadioGroup group1;
    private RadioGroup group2;
    private int typenumber=1;
    private Bitmap bitmap;
    private int shifou=0;
    private String typename="私密";
    private ImageView delete;
    private EditText write;
    private EditText titlename;
    private SharedPreferences sp=null;// 存放用户信息
    private int useid;
    private Double lon;
    private Double lat;
    private String[] photozu={};

    public TreeActivity() throws MalformedURLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        mContext = this;
        init();
        run();


//        try {
//            bitmap=getBitmap("http://182.254.243.200:8080/NightSleep/files/images/skins/11111.png");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        saveBitmapToSharedPreferences(bitmap);
//        bitmap=stringtoBitmap(imageString);
//        photo.setImageBitmap(bitmap);
//        stringtoBitmap("http://182.254.243.200:8080/NightSleep/files/images/skins/11111.png")
    }

    private void init()
    {
        back=(RelativeLayout)findViewById(R.id.back);
        title=(TextView)findViewById(R.id.title_name);
        seed=(TextView)findViewById(R.id.title_select);
        photo=(ImageView)findViewById(R.id.imageView21);
        phototext=(ImageView)findViewById(R.id.imageView3);
        type=(TextView)findViewById(R.id.textView3);
        type2=(ImageView)findViewById(R.id.imageView4);
        delete=(ImageView)findViewById(R.id.imageView7);
        write=(EditText)findViewById(R.id.editText);
        titlename=(EditText)findViewById(R.id.editText2);
        ri=(TextView)findViewById(R.id.textView);
        xingqi=(TextView)findViewById(R.id.textView2);
        ri.setText(getTime());
        xingqi.setText(getTime2());
        sp=this.getSharedPreferences("test", MODE_PRIVATE);
        useid=sp.getInt("useid",0);
        lat=Double.valueOf(sp.getString("lat",""));
        lon=Double.valueOf(sp.getString("lon",""));

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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    write.setText("");
                    titlename.setText("");
                    photo.setVisibility(View.GONE);
                    imageString="";

            }
        });

        type2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popView = LayoutInflater.from(mContext).inflate(
                        R.layout.pop_window, null);
                View rootView = findViewById(R.id.root_main); // 當前頁面的根佈局
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
                group2=(RadioGroup)popView.findViewById(R.id.group2);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "未保存修改",
                                Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.radioButton:
                            {
                                group2.clearCheck();
                                group1.check(R.id.radioButton);
                                typenumber=1;
                                typename="隐私";
                            }
                                break;
                            case R.id.radioButton1:
                            {
                                group2.clearCheck();
                                group1.check(R.id.radioButton1);
                                typenumber=2;
                                typename="文学";
                            }
                                break;
                            case R.id.radioButton2:
                            {
                                group2.clearCheck();
                                group1.check(R.id.radioButton2);
                                typenumber=3;
                                typename="幽默";
                            }
                                break;
                            case R.id.radioButton5:
                            {
                                group2.clearCheck();
                                group1.check(R.id.radioButton5);
                                typenumber=4;
                                typename="影视";
                            }
                                break;
                        }
                    }
                });
                group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.radioButton66:
                            {
                                group1.clearCheck();
                                group2.check(R.id.radioButton66);
                                typenumber=5;
                                typename="动漫";
                            }
                                break;
                            case R.id.radioButton61:
                            {
                                group1.clearCheck();
                                group2.check(R.id.radioButton61);
                                typenumber=6;
                                typename="人物";
                            }
                                break;
                            case R.id.radioButton62:
                            {
                                group1.clearCheck();
                                group2.check(R.id.radioButton62);
                                typenumber=7;
                                typename="体育";
                            }
                                break;
                            case R.id.radioButton65:
                            {
                                group1.clearCheck();
                                group2.check(R.id.radioButton65);
                                typenumber=8;
                                typename="游戏";
                            }
                                break;
                        }
                    }
                });
                save1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        type.setText(typename);
                    }
                });
            }
        });
        title.setText("小秘密");
        seed.setText("播种");
        seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(shifou==1)
                {
                      saveBitmapToSharedPreferences(bitmap);
                }
                else {
                    RequestParams params1 = new RequestParams(TreeActivity.this);
                    params1.addFormDataPart("userid",useid);
                    params1.addFormDataPart("topic",titlename.getText()+"");
                    params1.addFormDataPart("content",write.getText()+"");
                    params1.addFormDataPart("type",typenumber+"");
                    params1.addFormDataPart("lon",lon);
                    params1.addFormDataPart("lat",lat);
                    params1.addFormDataPart("skinid","1");
                    params1.addFormDataPart("images","{}");
                    HttpRequest.post(Constant.getpostInvi, params1, new JsonHttpRequestCallback() {
                        @Override
                        protected void onSuccess(Headers headers, JSONObject jsonObject) {
                            super.onSuccess(headers, jsonObject);
                            Log.e("TTTTT",jsonObject.toString());
                            BaseBean inviBean = JSONObject.parseObject(jsonObject.toString(), BaseBean.class);
                            if(inviBean.isStatus())
                            {
                                Intent intent = new Intent();
                                intent.putExtra("ok", "ok");
                                setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                                finish();
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


            }
        });
        phototext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.add_01);
//                saveBitmapToSharedPreferences(bitmap);
//                bitmap=stringtoBitmap(imageString);
//                photo.setImageBitmap(bitmap);
                Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                getAlbum.setType(IMAGE_TYPE);
                startActivityForResult(getAlbum, IMAGE_CODE);
            }
        });
    }
    //重写onActivityResult以获得你需要的信息

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (resultCode != RESULT_OK) {        //此处的 RESULT_OK 是系统自定义得一个常量

//            Log.e(TAG,"ActivityResult resultCode error");

            return;

        }

        Bitmap bm = null;

        //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口

        ContentResolver resolver = getContentResolver();

        //此处的用于判断接收的Activity是不是你想要的那个

        if (requestCode == IMAGE_CODE) {

            try {

                Uri originalUri = data.getData();        //获得图片的uri

                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);        //显得到bitmap图片
                photo.setVisibility(View.VISIBLE);
//
                photo.setImageBitmap(bm);


                bitmap=bm;
                shifou=1;
//                saveBitmapToSharedPreferences(bm);
//                bm=stringtoBitmap(imageString);
//                这里开始的第二部分，获取图片的路径：

                String[] proj = {MediaStore.Images.Media.DATA};

                //好像是android多媒体数据库的封装接口，具体的看Android文档

                Cursor cursor = managedQuery(originalUri, proj, null, null, null);

                //按我个人理解 这个是获得用户选择的图片的索引值

                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                //将光标移至开头 ，这个很重要，不小心很容易引起越界

                cursor.moveToFirst();

                //最后根据索引值获取图片路径

                 path = cursor.getString(column_index);
              getDiskBitmap(path);

            }catch (IOException e) {

//                Log.e(TAG,e.toString());

            }

        }

    }
    private Bitmap getDiskBitmap(String pathString)
    {
        Bitmap bitmap = null;
        try
        {
            File file = new File(pathString);
            if(file.exists())
            {
                bitmap = BitmapFactory.decodeFile(pathString);
                photo.setImageBitmap(bitmap);

            }
        } catch (Exception e)
        {
            // TODO: handle exception
        }


        return bitmap;
    }
//    URL url = new URL(path);
    public static Bitmap getBitmap(String path) throws IOException{

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        Log.e("ADFASDF",conn.getResponseCode()+"");
//        if(conn.getResponseCode() == 200){
//            InputStream inputStream = conn.getInputStream();
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            return bitmap;
//        }
        return null;
    }
    private void saveBitmapToSharedPreferences(Bitmap bitmap) {
        // Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
         imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
//        photozu[0]=imageString;
        Log.e("64字符串",imageString);
        RequestParams params = new RequestParams(TreeActivity.this);
        params.addFormDataPart("userid",useid);
        params.addFormDataPart("topic",titlename.getText()+"");
        params.addFormDataPart("content",write.getText()+"");
        params.addFormDataPart("type",typenumber+"");
        params.addFormDataPart("lon",lon);
        params.addFormDataPart("lat",lat);
        params.addFormDataPart("skinid","1");
        params.addFormDataPart("images","{}");
        HttpRequest.post(Constant.getpostInvi, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("TTTTT",jsonObject.toString());
                BaseBean inviBean = JSONObject.parseObject(jsonObject.toString(), BaseBean.class);
                if(inviBean.isStatus())
                {
                    Intent intent = new Intent();
                    intent.putExtra("ok", "ok");
                    setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                    finish();
                }else {

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
//        //第三步:将String保持至SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("testSP", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("image", imageString);
//        editor.commit();

//        //上传头像
//        setImgByStr(imageString,"");
    }
    public Bitmap stringtoBitmap(String string){
        //将字符串转换成Bitmap类型
        Bitmap bitmap=null;
        try {
            byte[]bitmapArray;
            bitmapArray=Base64.decode(string, Base64.DEFAULT);
            bitmap=BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }
}
