package net.tszextention.dbafinal.sqlinterface;

/**
 * Created by Administrator on 15-12-16.
 *
 * SharedPreferences操作
 */
public interface ShareDataPersistantInterface {
    /**
     * 将对象保存到SharedPreferences
     * @return
     */
    public abstract boolean saveToShare();
    /**
     * 将对象保存到SharedPreferences
     * @param key
     */
    public abstract boolean saveToShare(String key);

    /**
     * 从SharedPreferences获取信息
     */
    public abstract boolean getFromShare();

    /**
     * 从SharedPreferences获取信息
     * @param key
     */
    public abstract boolean getFromShare(String key);

    /**
     * 从SharedPreferences删除
     * @return
     */
    public abstract boolean deleteFramShare();

    /**
     * 从SharedPreferences删除
     * @param key
     * @return
     */
    public abstract boolean deleteFramShare(String key);
}

