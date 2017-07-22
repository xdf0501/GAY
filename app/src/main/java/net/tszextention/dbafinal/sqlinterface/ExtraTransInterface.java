package net.tszextention.dbafinal.sqlinterface;

import java.util.Map;

/**
 * Created by Administrator on 15-12-16.
 *
 * 数据转换，及其他附加操作
 */
public interface ExtraTransInterface {
    public abstract boolean setValue(String name, Object value);
//    {
//        return DBIndenty.setValue(this, name, value);
//    }
    public abstract Object getValue(String name, Object value);
//    {
//        return DBIndenty.getValue(this,name);
//    }
    public abstract Map<String, Object> toMap();
//    {
//        return DBIndenty.toMap(this);
//    }
    public abstract Map<String, String> toStringMap();
//    {
//        return DBIndenty.toStringMap(this);
//    }
    public abstract <T> void setValuesFromMap(Map<String, ?> map);
//    {
//        DBIndenty.setValuesFromMap(this,map);
//    }
}
