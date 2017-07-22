package com.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.Bean.PositionBean;
import com.Bean.ThemelistBean;
import com.Constant;
import com.alibaba.fastjson.JSONObject;
import com.appbase.BaseFragment;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.gay.R;
import com.gay.SchoolActivity;
import com.gay.Tree2Activity;
import com.gay.Tree3Activity;

import java.util.List;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;


/**
 * Created by xiaomei on 2016/12/8.
 * 位置首页
 */

public class Main_pageFragment extends BaseFragment implements HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    public static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 1;
    private Context context;//MainActivity的context
    View conuntView;//View
    private MapView mapView;
    private String schoolname;
    private BaiduMap baiduMap;
    private TextView chooseshool;
    private ImageView tree;
    private int start=1;
    private LatLng treelat;
    private LatLng treelat1;
    private Marker TreeMarkerA;
    private Marker TreeMarkerB;
    private double longitude1=1;//人经度
    private double latitude1=1;//人纬度
    boolean isFirstLoc = true; // 是否首次定位
    boolean isFirstruote=true;
    private Boolean nav_map_statu = false;
    private ImageView left;
    private ImageView right;
    private PositionBean positionBean1;
    // 定位相关声明
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
//    public BDLocationListener myListener = null;
    BitmapDescriptor bdA = BitmapDescriptorFactory
            .fromResource(R.mipmap.green);
    BitmapDescriptor bdB = BitmapDescriptorFactory
            .fromResource(R.mipmap.tree3);
    private int number=-1;
    private ThemelistBean themelistBean2;
    private Boolean isschool=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        conuntView = View.inflate(getActivity(), R.layout.pagefragment, null);
        conuntView.setLayoutParams(params);

        mapView = (MapView) conuntView.findViewById(R.id.bmapView);
        left=(ImageView)conuntView.findViewById(R.id.hp_map_left);
        right=(ImageView)conuntView.findViewById(R.id.hp_map_right);
        chooseshool=(TextView)conuntView.findViewById(R.id.textView21);
//        mapView.showZoomControls(false);
        baiduMap = mapView.getMap();
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        mapView.showZoomControls(false);
        mLocationClient = new LocationClient(getContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        initLocation();
        mLocationClient.start();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // 设置为一般地图
        baiduMap.setTrafficEnabled(false); // 开启交通图
        run3();
        return conuntView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (resultCode)
        {
            case 20:
            {
                Bundle Buddle = data.getExtras();
                isschool=true;
                number=-1;
                Integer schoolid = Buddle.getInt("schoolid");
                String lat=Buddle.getString("lat");
                String lon=Buddle.getString("lon");
                 schoolname=Buddle.getString("schoolname");
                run5(schoolid);
                chooseshool.setText(schoolname);
                LatLng ll = new LatLng(Double.valueOf(lat),Double.valueOf(lon));
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(17.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
                break;

        }
    }
    private void run5(int id)
    {
        baiduMap.clear();
        RequestParams params1 = new RequestParams(Main_pageFragment.this);
        params1.addFormDataPart("schoolid",id);
        HttpRequest.get(Constant.getThemeInvitationList+"?", params1, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("fafsadsd",jsonObject.toString());
                ThemelistBean themelistBean = JSONObject.parseObject(jsonObject.toString(), ThemelistBean.class);
                themelistBean2=themelistBean;
                if(themelistBean.isStatus())
                {
                    BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.mipmap.smalltree);
                    for (int i=0;i<themelistBean.getThemeInvitationList().size();i++)
                    {
                        LatLng markerll = new LatLng(themelistBean.getThemeInvitationList().get(i).getLat(),
                                themelistBean.getThemeInvitationList().get(i).getLon());

                        if(themelistBean.getThemeInvitationList().get(i).
                                getImage().equals("http://182.254.243.200:8080/NightSleep/files/images/skins/theme1.png"))
                        {
                            bd = BitmapDescriptorFactory.fromResource(R.mipmap.theme1);
                        }
                        else if(themelistBean.getThemeInvitationList().get(i).
                                getImage().equals("http://182.254.243.200:8080/NightSleep/files/images/skins/theme2.png"))
                        {
                            bd = BitmapDescriptorFactory.fromResource(R.mipmap.theme2);
                        }
                        else if(themelistBean.getThemeInvitationList().get(i).
                                getImage().equals("http://182.254.243.200:8080/NightSleep/files/images/skins/theme3.png"))
                        {
                            bd = BitmapDescriptorFactory.fromResource(R.mipmap.theme3);
                        }
                        else if(themelistBean.getThemeInvitationList().get(i).
                                getImage().equals("http://182.254.243.200:8080/NightSleep/files/images/skins/theme4.png"))
                        {
                            bd = BitmapDescriptorFactory.fromResource(R.mipmap.theme4);
                        }
                        OverlayOptions oo = new MarkerOptions().icon(bd).position(markerll);// 图标
                        Marker marker = (Marker) (baiduMap.addOverlay(oo));
                        marker.setTitle(i+"");
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

    private void run3()
    {
        chooseshool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SchoolActivity.class);
                Bundle bundle = new Bundle();
                String str1 = "aaaaaa";
                bundle.putString("str1", str1);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);//这里采用startActivityForResu
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(number<=0)
            {
                LatLng ll;
                if(isschool)
                {
                    ll = new LatLng(themelistBean2.getThemeInvitationList().get(0).getLat(),themelistBean2.getThemeInvitationList().get(0).getLon());
                }
                else {
                    ll = new LatLng(positionBean1.getData().get(0).getLat(),positionBean1.getData().get(0).getLon());
                }

                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(20.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                Toast.makeText(getContext(), "这是第一棵树",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                number--;
                LatLng ll;
                if(isschool)
                {
                    ll = new LatLng(themelistBean2.getThemeInvitationList().get(number).getLat(),themelistBean2.getThemeInvitationList().get(number).getLon());
                }
                else {
                    ll = new LatLng(positionBean1.getData().get(number).getLat(),positionBean1.getData().get(number).getLon());
                }

                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(20.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            }

        }
    });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum;
                if(isschool)
                {
                    sum=themelistBean2.getThemeInvitationList().size()-1;
                }
                else {
                    sum=positionBean1.getData().size()-1;
                }
                if(number==sum)
                {
                    Toast.makeText(getContext(), "这是最后一棵树",
                        Toast.LENGTH_SHORT).show();
                }
                else {
                    number++;
                    LatLng ll;
                    if(isschool)
                    {
                        ll = new LatLng(themelistBean2.getThemeInvitationList().get(number).getLat(),themelistBean2.getThemeInvitationList().get(number).getLon());
                    }
                    else {
                        ll = new LatLng(positionBean1.getData().get(number).getLat(),positionBean1.getData().get(number).getLon());
                    }
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(ll).zoom(20.0f);
                    baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                }

            }
        });
    }
    private void run2()
    {
        Log.e("hello",latitude1+"");
        RequestParams params2 = new RequestParams(Main_pageFragment.this);
        params2.addFormDataPart("Lon",longitude1);
        params2.addFormDataPart("Lat",latitude1);
        HttpRequest.post(Constant.UserInvitation, params2, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.e("fasddasfasdf",jsonObject.toString());
                PositionBean positionBean = JSONObject.parseObject(jsonObject.toString(), PositionBean.class);
                positionBean1=positionBean;
                if(positionBean.isStatus())
                {
                    int j=0;
                    BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.mipmap.smalltree);
                    for (int i=0;i<positionBean.getData().size();i++)
                    {
                        LatLng markerll = new LatLng(positionBean.getData().get(i).getLat(), positionBean.getData().get(i).getLon());


                        if(positionBean.getData().get(i).getHot()<25)
                        {
                            bd = BitmapDescriptorFactory.fromResource(R.mipmap.smalltree);
                        }
                        else if(positionBean.getData().get(i).getHot()<50&&25<=positionBean.getData().get(i).getHot())
                        {
                            bd = BitmapDescriptorFactory.fromResource(R.mipmap.smalltree2);
                        }
                        else if(positionBean.getData().get(i).getHot()<75&&50<=positionBean.getData().get(i).getHot())
                        {
                            bd = BitmapDescriptorFactory.fromResource(R.mipmap.bigtree2);
                        }
                        else if(75<=positionBean.getData().get(i).getHot())
                        {
                            bd = BitmapDescriptorFactory.fromResource(R.mipmap.bigtree1);
                        }
                        OverlayOptions oo = new MarkerOptions().icon(bd).position(markerll);// 图标
                        Marker marker = (Marker) (baiduMap.addOverlay(oo));
                        marker.setTitle(i+"");
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
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            public boolean onMarkerClick(final Marker marker) {

                    number=Integer.valueOf(marker.getTitle());
                    marker.getTitle();
                if(isschool)
                {
                    Intent intent = new Intent(getContext(), Tree3Activity.class);
                    intent.putExtra("id",themelistBean2.getThemeInvitationList().get(number).getId()+"");
                    intent.putExtra("school",schoolname);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getContext(), Tree2Activity.class);
                    intent.putExtra("id",positionBean1.getData().get(number).getId()+"");
                    startActivity(intent);
                }



                return true;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.e("DINGWEI",location.getLatitude()+"");
            longitude1=location.getLongitude();
            latitude1=location.getLatitude();
            if(isFirstLoc)
            {
                run2();
                //实例化SharedPreferences对象（第一步）
                SharedPreferences mySharedPreferences= getContext().getSharedPreferences("test",
                        Activity.MODE_PRIVATE);
//实例化SharedPreferences.Editor对象（第二步）
                SharedPreferences.Editor editor = mySharedPreferences.edit();
//用putString的方法保存数据
                editor.putString("lon", longitude1+"");
                editor.putString("lat", latitude1+"");
//提交当前数据
                editor.commit();
                isFirstLoc=false;
            }


//使用toast信息提示框提示成功写入数据
//            Toast.makeText(this, "数据成功写入SharedPreferences！" ,
//                    Toast.LENGTH_LONG).show();
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
            if(start==1){
                LatLng ll = new LatLng(latitude1,longitude1);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                start=0;
            }
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


}
