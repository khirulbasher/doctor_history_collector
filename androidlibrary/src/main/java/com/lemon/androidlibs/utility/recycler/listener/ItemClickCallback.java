package com.lemon.androidlibs.utility.recycler.listener;

import android.support.v4.app.Fragment;

import com.lemon.androidlibs.utility.enumeration.Why;
import com.lemon.androidlibs.utility.item.Item;

/**
 * Created by lemon on 4/6/2018.
 */

@SuppressWarnings("DefaultFileTemplate")
public interface ItemClickCallback {
    void onCallback(Item item, Why why);
    Class getRenderingClass();
    Fragment makeFragment(Why why) throws Exception;
}
