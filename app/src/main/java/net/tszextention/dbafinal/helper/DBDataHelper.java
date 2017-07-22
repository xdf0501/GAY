package net.tszextention.dbafinal.helper;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.db.sqlite.DbModel;
import net.tsz.afinal.db.sqlite.SqlBuilder;
import net.tsz.afinal.db.table.TableInfo;
import net.tszextention.dbafinal.sqlinterface.DBFieldChangeInterface;
import net.tszextention.dbafinal.sqlinterface.DBPersistantInterface;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 只支持有限的数据类型;
 * 字段类型应该使用大写开头的;eg:Long/Integar/Double等
 */
public class DBDataHelper {
    //子类中用到
    public static Object obj = new Object();
    public static Map<String, Boolean> latestMap = new HashMap<String, Boolean>();

    //最后一次使用的db名字
    static String dbName;

    private DBDataHelper() {

    }

    private static Context context;//should use applicationContext

    public static void setApplicationContext( Context applicationContext) {
        context = applicationContext;
    }

    private static Context getContext() {
        if (context == null) {
            Log.e(DBDataHelper.class.getName(), "should call setApplicationContext first");
        }
        return context;
    }

    /**
     * 不会重复创建相同名字的数据库db，若name为空,db创建在内存中
     *
     * @param name
     * @return
     */
    public synchronized static FinalDb finalDb(String name) {
        FinalDb db = null;
        try {
            if (getContext() != null) {
                if (TextUtils.isEmpty(name)) {
                    dbName = null;
                    //获取db，内存db文件，不存在则创建
                    db = FinalDb.create(getContext());
                } else {
                    if (name.endsWith(".db")) {
                        dbName = name;
                    } else {
                        dbName = name + ".db";
                    }
                    String nameTemp=dbName;
                    if (dbName.contains("/")){
                        nameTemp=dbName.substring(name.lastIndexOf("/")+1);
                        String directory=dbName.substring(0,name.lastIndexOf("/"));
                        if (!TextUtils.isEmpty(directory)){
                            if (!new File(directory).exists()){
//                                FileManager.makeDirs(directory);
                            }
                            db = FinalDb.create(getContext(),directory, nameTemp);
                        }else{
                            db = FinalDb.create(getContext(), nameTemp);
                        }
                    }else {
                        //获取db，本地文件，不存在则创建
                        db = FinalDb.create(getContext(), nameTemp);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return db;
    }

    public synchronized static FinalDb finalDb() {
        return DBDataHelper.finalDb(dbName);
    }
    ///////////////////////查询

    /**
     * 查询所有记录
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> find( Class<T> clazz) {
        //return DBDataHelper.finalDb().findAll(clazz);
        return findAllByWhere(clazz, "");
    }

    public static <T> List<T> findAllByWhere( Class<T> clazz, String strWhere) {
//		checkTable(clazz);
//		return DBDataHelper.finalDb().findAllByWhere(clazz, strWhere);
        return findAllByWhere(clazz, strWhere, "");
    }

    public static <T> List<T> findAllByWhere( Class<T> clazz, String strWhere,
                                             String orderBy) {
//		checkTable(clazz);
//		if (!TextUtils.isEmpty(orderBy)){
//			return DBDataHelper.finalDb().findAllByWhere(clazz, strWhere,orderBy);
//		}
//		return DBDataHelper.finalDb().findAllByWhere(clazz, strWhere);
        return findAllByWhere(clazz, strWhere, orderBy, "");
    }

    /**
     * 按条件查询记录
     *
     * @param clazz
     * @param strWhere 条件
     * @param orderBy  排序
     * @param limit    分页
     * @param <T>
     * @return
     */
    public static <T> List<T> findAllByWhere( Class<T> clazz, String strWhere,
                                             String orderBy, String limit) {
        checkTable(clazz);
        if (TextUtils.isEmpty(orderBy)) {
            orderBy = null;
        }
        if (TextUtils.isEmpty(limit)) {
            limit = null;
        }
        return DBDataHelper.finalDb().findAllByWhere(clazz, strWhere, orderBy, limit);
    }

    /**
     * 检查表字段，并更新
     *
     * @param clazz
     * @return
     */
    public static boolean checkTable(Class<?> clazz) {
        boolean flag = false;
        if (clazz != null) {
            try {
                Object object = clazz.newInstance();
                //原本定义的表必须继承自DBPersistant，
                //现在尝试任意继承自Object的类，由使用者自己控制，
                if (object instanceof DBPersistantInterface) {
                    TableInfo info = TableInfo.get(clazz);
                    if (info != null) {
                        String tblName = info.getTableName();
                        if (DBDataHelper.latestMap.containsKey(tblName)) {
                            flag = DBDataHelper.latestMap.get(tblName);
                        }
                        if (!flag) {
                            synchronized (DBDataHelper.obj) {
                                if (DBDataHelper.latestMap.containsKey(tblName)) {
                                    flag = DBDataHelper.latestMap.get(tblName);
                                }
                                if (!flag) {
                                    boolean recreate = false;
                                    long num = DBDataHelper.finalDb().count(clazz);
                                    if (num <= 0) {
                                        recreate = true;
                                    }
                                    if (recreate) {
                                        DBDataHelper.finalDb().dropTable(clazz);
                                    } else {
                                        String sql = "select * from " + tblName + "  limit 1 offset 0";
                                        DbModel dbModel = DBDataHelper.finalDb().findDbModelBySQL(sql);
                                        //already exist filed
                                        Map<String, Object> dataMap = dbModel.getDataMap();
                                        if (dataMap != null) {
                                            //need to modified filed-name
                                            //原本定义的表字段更新依据必须改写DBPersistant的toRenameColumns方法，
                                            //现在尝试修改未interface模式
                                            //Map<String, String> nameMap=((DBPersistant) object).toRenameColumns();
                                            Map<String, String> nameMap = null;
                                            if (object instanceof DBFieldChangeInterface) {
                                                nameMap = ((DBFieldChangeInterface) object).toRenameColumns();
                                            }

                                            //delete the filed not exist in table
                                            ArrayList<String> keysTmp = new ArrayList<String>();
                                            if (nameMap != null) {
                                                for (String key : nameMap.keySet()) {
                                                    if (!dataMap.containsKey(key)) {
                                                        keysTmp.add(key);
                                                    }
                                                }
                                            }

                                            if (keysTmp.size() > 0) {
                                                for (String key : keysTmp) {
                                                    nameMap.remove(key);
                                                }
                                                keysTmp.clear();
                                            }
                                            //delete filed not exist in table which will be created
                                            for (String key : nameMap.keySet()) {
                                                String newName = nameMap.get(key);
                                                if (!info.propertyMap.containsKey(newName)) {
                                                    keysTmp.add(key);
                                                }
                                            }
                                            if (keysTmp.size() > 0) {
                                                for (String key : keysTmp) {
                                                    nameMap.remove(key);
                                                }
                                                keysTmp.clear();
                                            }
                                            int extraNum = 0;
                                            if (info.getId() != null) {
                                                if (!info.propertyMap.containsKey(info.getId().getColumn())) {
                                                    extraNum = 1;
                                                }
                                            }
                                            boolean forceUpdate = false;
                                            for (String key : dataMap.keySet()) {
                                                if (!info.propertyMap.containsKey(key) && !nameMap.containsKey(key)) {
                                                    forceUpdate = true;
                                                }
                                            }
                                            //here info.propertyMap may not contain sqlite Id column
                                            if (nameMap.size() > 0 || dataMap.size() != info.propertyMap.size() + extraNum || forceUpdate) {
                                                String toColumn = "";
                                                String fromColumn = "";
                                                String newColumn = "";
                                                String oldColumn = "";
                                                for (String key : info.propertyMap.keySet()) {
                                                    if (nameMap.size() > 0 && nameMap.containsValue(key)) {
                                                        boolean findflag = false;
                                                        for (String keyTmp : nameMap.keySet()) {
                                                            if (key.equals(nameMap.get(keyTmp))) {
                                                                fromColumn += keyTmp + ",";
                                                                findflag = true;
                                                            }
                                                        }
                                                        if (!findflag) {
                                                            fromColumn += key + ",";
                                                        }
                                                        toColumn += key + ",";
                                                    } else {
                                                        newColumn += key + ",";
                                                        if (dataMap.containsKey(key)) {
                                                            oldColumn += key + ",";
                                                        } else {
                                                            oldColumn += "'',";
                                                        }
                                                    }
                                                }
                                                String fromSql = fromColumn + oldColumn;
                                                String toSql = toColumn + newColumn;
                                                if (fromSql.length() > 3 && toSql.length() > 3) {
                                                    fromSql = fromSql.substring(0, fromSql.length() - 1);
                                                    toSql = toSql.substring(0, toSql.length() - 1);
                                                }
                                                String tmpTable = tblName + "_tmp";
                                                if (DBDataHelper.finalDb().tableIsExist(tmpTable)) {
                                                    DBDataHelper.finalDb().dropTable(tmpTable);
                                                }
                                                if (!toSql.equals(fromSql)) {
                                                    String tmpTableSql = "ALTER TABLE " + tblName + " RENAME TO " + tmpTable;
                                                    String newTableSql = SqlBuilder.getCreatTableSQL(clazz);
                                                    String copyTableSql = "INSERT INTO " + tblName + " (" + toSql + ") SELECT " + fromSql + " FROM " + tmpTable;

                                                    DBDataHelper.finalDb().exeSql(tmpTableSql);
                                                    DBDataHelper.finalDb().exeSql(newTableSql);
                                                    DBDataHelper.finalDb().exeSql(copyTableSql);
                                                    DBDataHelper.finalDb().dropTable(tmpTable);
                                                }
                                            }
                                        } else {
                                            DBDataHelper.finalDb().dropTable(clazz);
                                        }
                                    }
                                    DBDataHelper.latestMap.put(tblName, true);
                                    System.out.println("table:" + tblName + " had checked");
                                    flag = true;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 删除表的所有记录，但不删除表
     *
     * @param clazz
     */
    public static void delete(Class<?> clazz) {
        delete(clazz, null);
    }

    /**
     * 删除行记录
     *
     * @param clazz
     * @param strWhere
     */
    public static void delete(Class<?> clazz, String strWhere) {
        try {
            checkTable(clazz);
            DBDataHelper.finalDb().deleteByWhere(clazz, strWhere);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 删除表
     *
     * @param clazz
     */
    public static void drop(Class<?> clazz) {
        try {
            DBDataHelper.finalDb().dropTable(clazz);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    //////时间的转换///////

    /**
     * 获取时间字符串中格式yyyy-MM-dd/yyyy-MM-dd HH:mm/yyyy-MM-dd HH:mm:ss
     *
     * @param date 时间字符串
     * @return
     */
    public static String getDateFormatMsg(String date) {
        if (date != null && !"".equals(date)) {
            date = date.trim();
            String reg1 = "[0-9]{4}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}";
            if (date.matches(reg1)) {
                return "yyyy-MM-dd";
            }
            String reg2 = "[0-9]{4}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}[\\s]+[0-9]{1,2}[:]{1}[0-9]{1,2}";
            if (date.matches(reg2)) {
                return "yyyy-MM-dd HH:mm";
            }
            String reg3 = "[0-9]{4}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}[\\s]+[0-9]{1,2}[:]{1}[0-9]{1,2}[:]{1}[0-9]{1,2}";
            if (date.matches(reg3)) {
                return "yyyy-MM-dd HH:mm:ss";
            }
        }
        return null;
    }

    /**
     * 将时间字符串转换为Long型
     *
     * @param dateTime 时间字符串
     * @param format   时间字符串格式,参考getDateFormatMsg
     * @return
     */
    public static Long toLong(String dateTime, String format) {
        long time = -1;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = null;
            if (dateTime != null) {
                try {
                    date = sdf.parse(dateTime);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            time = date.getTime();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("DateFormatUtil::toLong:" + dateTime
                    + "-format:" + format);
        }
        return time;
    }

    /**
     * 将Long型时间转换为指定格式字符串
     *
     * @param dateTime 时间字符串
     * @param format   时间字符串格式,参考getDateFormatMsg
     * @return
     */
    public static String toString(Long dateTime, String format) {
        String str = "";
        try {
            Date date = new Date();
            if (dateTime != null) {
                date.setTime(dateTime.longValue());
                SimpleDateFormat sdf = null;
                sdf = new SimpleDateFormat(format);
                str = sdf.format(date);
            } else {
                return "";
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Log.i(DBDataHelper.class.getName(), dateTime
                    + "-format:" + format);
        }
        return str;
    }

    /**
     * 将时间字符串转换为Date型
     *
     * @param date   时间字符串
     * @param format 时间字符串格式,参考getDateFormatMsg
     * @return
     */
    public static Date toDate(String date, String format) {
        Date d = null;
        if (date != null && !date.equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                d = sdf.parse(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return d;
    }
}
