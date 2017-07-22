package net.tszextention.dbafinal.sqlinterface;

/**
 * Created by Administrator on 15-12-16.
 *
 * sqlite数据操作
 */
public interface DBPersistantInterface {
    public abstract boolean save();
    public abstract boolean delete();
    public abstract boolean syncFromDb();
}
