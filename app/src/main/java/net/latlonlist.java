package net;


import com.Bean.modelBean;
import com.Constant;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface latlonlist {
    @POST(Constant.login)                             //直接constant.后面自己需要的接口
    @FormUrlEncoded
    Call<modelBean> getString(@Field("esjson") String esjson);
    //主要看接口文档里面是什么（？后面开始看），有两种，一直sdjson，还有一种另外的
    //这里的返回值类型需要Bean文件最外面那个大的Bean文件

}
