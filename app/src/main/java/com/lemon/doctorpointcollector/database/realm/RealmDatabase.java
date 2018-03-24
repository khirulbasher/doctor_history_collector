package com.lemon.doctorpointcollector.database.realm;

import java.lang.reflect.Method;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by lemon on 3/24/2018.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal", "unchecked", "UnusedReturnValue"})
public class RealmDatabase {
    private final Realm realm;

    public RealmDatabase() {
        realm=Realm.getDefaultInstance();
    }

    public RealmObject persist(final RealmObject realmObject) {
        try {
            Method method=realmObject.getClass().getMethod("setId",Long.class);
            method.invoke(realmObject,System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.realm.beginTransaction();
        final RealmObject object = realm.copyToRealm(realmObject);
        this.realm.commitTransaction();
        return object;
    }

    public void close() {
        if(this.realm!=null)
            this.realm.close();
    }

    public RealmResults<Object> findAll(Class entityClass) {
        return this.realm.where(entityClass).findAll();
    }

    public List findAllList(Class entityClass) {
        return this.realm.where(entityClass).findAll();
    }

    public Object findOne(Long primaryKay,Class aClass) {
        return this.realm.where(aClass).equalTo("id",primaryKay).findFirst();
    }
}
