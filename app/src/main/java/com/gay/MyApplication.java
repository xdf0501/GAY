package com.gay;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Dispatcher;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        builder.setDispatcher(new Dispatcher(Executors.newFixedThreadPool(1)));
//        5
        OkHttpFinal.getInstance().init(builder.build());
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);

    }

}