package net.tsz.afinal.db.sqlite;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 涓澶氬欢杩熷姞杞界被
 * Created by pwy on 13-7-25.
 * @param <O> 瀹夸富瀹炰綋鐨刢lass
 * @param <M> 澶氭斁瀹炰綋class
 */
public class OneToManyLazyLoader<O,M> {
    O ownerEntity;
    Class<O> ownerClazz;
    Class<M> listItemClazz;
    FinalDb db;
    public OneToManyLazyLoader(O ownerEntity,Class<O> ownerClazz,Class<M> listItemclazz,FinalDb db){
        this.ownerEntity = ownerEntity;
        this.ownerClazz = ownerClazz;
        this.listItemClazz = listItemclazz;
        this.db = db;
    }
    List<M> entities;

    /**
     * 濡傛灉鏁版嵁鏈姞杞斤紝鍒欒皟鐢╨oadOneToMany濉厖鏁版嵁
     * @return
     */
    public List<M> getList(){
        if(entities==null){
            this.db.loadOneToMany((O)this.ownerEntity,this.ownerClazz,this.listItemClazz);
        }
        if(entities==null){
            entities =new ArrayList<M>();
        }
        return entities;
    }
    public void setList(List<M> value){
        entities = value;
    }

}
