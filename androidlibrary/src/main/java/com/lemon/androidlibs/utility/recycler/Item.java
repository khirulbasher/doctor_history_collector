package com.lemon.androidlibs.utility.recycler;

/**
 * Created by lemon on 2/17/2018.
 */

public class Item {
    public final Long primaryKey;
    public final String title;
    public final String description;

    public Item(Long primaryKey, String title, String description) {
        this.primaryKey = primaryKey;
        this.title = title;
        this.description = description;
    }
}
