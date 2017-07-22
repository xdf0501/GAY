package net.tszextention.httpafinal;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 15-12-25.
 */
public class Msg {
    private String code;
    private String msg;
    private Object obj;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public <T> T getObj(Class<T> cls){
        T data=null;
        try {
            //需要转换得到对象类型
            if (cls!=String.class) {
                //其他对象类型，无法判断是否是json格式，直接装换
                if (obj != null) {
                    if (obj instanceof String) {
                        if (obj.getClass()==cls){
                            //obj 与 T的类型一致
                            data=(T)obj;
                        }else {
                            data = JSON.parseObject(String.valueOf(obj), cls);
                        }
                    }else{
                        //obj 非String类型时
                        if(obj.getClass()==cls){
                            //obj 与 T的类型一致
                            data=(T)obj;
                        }else{
                            //不一致时
                        }
                    }
                }
            }else{
                //String类型
                if (obj != null) {
                    if (obj instanceof String){
                        data = (T) obj.toString();
                    }else{
                        try {
                            //转换为json String
                            data = (T) JSON.toJSONString(obj);
                        }catch (Exception e){
                            e.printStackTrace();
                            //转换失败后
                            data = (T) obj.toString();
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    public <T> List<T> getObjArray( Class<T> cls){
        List<T> data=null;
        try {
            //缺陷:无法判断是否是符合json格式，假定obj是json字符串,直接装换
            if (obj != null) {
                if (obj instanceof JSONArray||obj instanceof String) {
                    data = JSON.parseArray(String.valueOf(obj), cls);
                }else if (obj instanceof List){
                    List list=(List)obj;
                    if (list!=null){
                        if (list.size()>0&&list.get(0).getClass() == cls) {
                            data = (List<T>) obj;
                        }else{
                            //Method method = list.getClass().getMethod("get", null);
                            Method method = list.getClass().getMethod("get", new  Class[0]);
                            Class returnTypeClass = method.getReturnType();
                            if (returnTypeClass==cls){
                                data = (List<T>) obj;
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
