package com.lemon.androidlibs.database.realm;

/**
 * Created by lemon on 4/7/2018.
 */

public interface PersistCallback<K> {
    K copyToRealm(K entity);
}
