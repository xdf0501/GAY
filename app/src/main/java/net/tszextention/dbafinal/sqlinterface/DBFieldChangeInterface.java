package net.tszextention.dbafinal.sqlinterface;

import java.util.Map;

/**
 * Created by Administrator on 15-12-16.
 *
 * 用来修改已保存到sqlite中表的记录某些字段
 */
public interface DBFieldChangeInterface {
    ////字段变更
	/**
	 * 需要重命名的旧字段名字，及新字段名Map
	 * key：旧字段名；value：新字段名。
    */
    public abstract Map<String, String> toRenameColumns();
}
