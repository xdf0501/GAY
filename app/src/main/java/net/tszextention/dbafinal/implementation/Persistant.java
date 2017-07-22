package net.tszextention.dbafinal.implementation;

import android.text.TextUtils;

import net.tsz.afinal.db.table.Id;
import net.tsz.afinal.db.table.TableInfo;
import net.tszextention.dbafinal.helper.DBDataHelper;
import net.tszextention.dbafinal.helper.DBIndenty;
import net.tszextention.dbafinal.helper.ShareDataHelper;
import net.tszextention.dbafinal.sqlinterface.DBFieldChangeInterface;
import net.tszextention.dbafinal.sqlinterface.DBPersistantInterface;
import net.tszextention.dbafinal.sqlinterface.ExtraTransInterface;
import net.tszextention.dbafinal.sqlinterface.ShareDataPersistantInterface;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 待测试
 * 尝试使用interface去给一个类添加额外数据保存的功能；避免改变某些类的基类 如javabean
 * 使用方式：
 * 1、表的类从基类到子类都是自己定义，直接将此类作为基类使用
 * 2、如果是其他有自己独有的基类的（如javabean），则自定义一个继承与javabean的类，并按需要实现以下某些interface
 */
public class Persistant implements Serializable, DBPersistantInterface, ShareDataPersistantInterface, DBFieldChangeInterface, ExtraTransInterface {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    ////sqlite/////////////////////////////
    /**
     * 保存信息到sqlite
     */
    public boolean save() {
        boolean flag = false;
        try {
            //只在第一次做检查（每次ApplicationContext重新创建时），检查的寄过缓存在latestMap
            //检查表字段是否有更改
            DBDataHelper.checkTable(this.getClass());
            //执行保存
            TableInfo info = TableInfo.get(this.getClass());
            Object idvalue = info.getId().getValue(this);
            if (null == idvalue) {
                DBDataHelper.finalDb().save(this);
            } else {
                try {
                    Object record = DBDataHelper.finalDb().findById(idvalue, this.getClass());
                    if (record != null) {
                        DBDataHelper.finalDb().updateBindId(this);
                        //DBDataHelper.finalDb().update(this);
                    } else {
                        Id iid = info.getId();
                        if (iid.getDataType() == int.class || iid.getDataType() == Integer.class
                                || iid.getDataType() == long.class || iid.getDataType() == Long.class) {
                            long id = DBDataHelper.finalDb().saveBackID(this);
                            if (id > 0) {
                                info.getId().setValue(this, id);
                            }
                        } else {
                            DBDataHelper.finalDb().save(this);
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return flag;
    }

    public boolean delete() {
        boolean flag = false;
        try {
            DBDataHelper.finalDb().delete(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean syncFromDb() {
        boolean flag = false;
        try {
            TableInfo info = TableInfo.get(this.getClass());
            Persistant data = DBDataHelper.finalDb().findById(info.getId(), this.getClass());
            if (data != null) {
                DBIndenty.copy(this, data);
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    ////字段变更/////////////////////////////////////

    /**
     * 需要需要修改的字段名及对应名字
     *
     * @return
     */
    public Map<String, String> toRenameColumns() {
//		//old name is key
//		//new name is value
        Map<String, String> colsMap = new HashMap<String, String>();
//		//eg:
//		//colsMap.put("name", "newName");
//		//colsMap.put("email", "newEmail");
        return colsMap;
    }
    ////SharedPreferences/////////////////////////////

    /**
     * 将对象保存到SharedPreferences（记录是唯一的）
     *
     * @return
     */
    public boolean saveToShare() {
        boolean flag = false;
        try {
            saveToShare(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 将对象保存到SharedPreferences
     *
     * @param key
     */
    public boolean saveToShare(String key) {
        boolean flag = false;
        try {
            if (TextUtils.isEmpty(key)) {
                key = getClass().getName().replace('.', '_');
            }
            ShareDataHelper.setObject(null, key, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 从SharedPreferences获取信息
     */
    public boolean getFromShare() {
        boolean flag = false;
        try {
            getFromShare(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 从SharedPreferences获取信息
     *
     * @param key
     */
    public boolean getFromShare(String key) {
        boolean flag = false;
        try {
            if (TextUtils.isEmpty(key)) {
                key = getClass().getName().replace('.', '_');
            }
            Object object = ShareDataHelper.getObject(null, key);
            if (object != null&&object instanceof Persistant) {
                Persistant fromData = (Persistant) object;
                DBIndenty.copy(this, fromData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 从SharedPreferences删除
     *
     * @return
     */
    public boolean deleteFramShare() {
        boolean flag = false;
        try {
            flag = deleteFramShare(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 从SharedPreferences删除
     *
     * @param key
     * @return
     */
    public boolean deleteFramShare(String key) {
        boolean flag = false;
        try {
            if (TextUtils.isEmpty(key)) {
                key = getClass().getName().replace('.', '_');
            }
            flag = ShareDataHelper.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //////////////////////////
    public boolean setValue(String name, Object value) {
        return DBIndenty.setValue(this, name, value);
    }

    public Object getValue(String name, Object value) {
        return DBIndenty.getValue(this, name);
    }

    public Map<String, Object> toMap() {
        return DBIndenty.toMap(this);
    }

    public Map<String, String> toStringMap() {
        return DBIndenty.toStringMap(this);
    }

    public <T> void setValuesFromMap(Map<String, ?> map) {
        DBIndenty.setValuesFromMap(this, map);
    }
}
