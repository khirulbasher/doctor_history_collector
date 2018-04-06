package com.lemon.androidlibs.database.realm;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
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

    public <E> List<E> findAllList(Class<E> entityClass) {
        return new ArrayList<>(this.realm.where((Class)entityClass).findAll());
    }

    public Object findOne(Long primaryKay,Class clazz) {
        return this.realm.where(clazz).equalTo("id",primaryKay).findFirst();
    }

    public boolean delete(Class clazz,Long primaryKey) {
        try {
            RealmResults<RealmObject> realmObjects=realm.where(clazz).equalTo("id",primaryKey).findAll();
            realm.beginTransaction();
            realmObjects.deleteAllFromRealm();
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
