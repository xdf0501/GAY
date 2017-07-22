package com.gay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by 狄飞 on 2017/4/28.
 */

public class MapActivity extends Activity {
    private MapView mapView;
    private BaiduMap baiduMap;
    private Double lon;
    private Double lat;
    private int id;
    private int hot;
    private Boolean isschool=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        lon= intent.getDoubleExtra("lon",0.0);
        lat= intent.getDoubleExtra("lat",0.0);
        id= intent.getIntExtra("id",0);
        hot=intent.getIntExtra("hot",0);
        init();
        run();
    }
    private void init()
    {
        mapView=(MapView)findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        // 开启定位图层
//        baiduMap.setMyLocationEnabled(true);
        mapView.showZoomControls(false);
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // 设置为一般地图
        baiduMap.setTrafficEnabled(false); // 开启交通图
    }
    private void run()
    {
        BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.mipmap.smalltree);
            LatLng markerll = new LatLng(lat, lon);
            if(hot<25)
            {
                bd = BitmapDescriptorFactory.fromResource(R.mipmap.smalltree);
            }
            else if(hot<50&&25<=hot)
            {
                bd = BitmapDescriptorFactory.fromResource(R.mipmap.smalltree2);
            }
            else if(hot<75&&50<=hot)
            {
                bd = BitmapDescriptorFactory.fromResource(R.mipmap.bigtree2);
            }
            else if(75<=hot)
            {
                bd = BitmapDescriptorFactory.fromResource(R.mipmap.bigtree1);
            }
            OverlayOptions oo = new MarkerOptions().icon(bd).position(markerll);// 图标
            Marker marker = (Marker) (baiduMap.addOverlay(oo));
            LatLng ll = new LatLng(lat,lon);
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(20.0f);
            baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
           baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            public boolean onMarkerClick(final Marker marker) {
                if(isschool)
                {
                    Intent intent = new Intent(MapActivity.this, Tree3Activity.class);
                    intent.putExtra("id",id+"");
                    intent.putExtra("school","某学校");
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MapActivity.this, Tree2Activity.class);
                    intent.putExtra("id",id+"");
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
}
