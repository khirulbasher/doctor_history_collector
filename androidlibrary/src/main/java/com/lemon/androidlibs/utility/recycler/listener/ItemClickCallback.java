package com.lemon.androidlibs.utility.recycler.listener;

import com.lemon.androidlibs.utility.enumeration.Why;
import com.lemon.androidlibs.utility.Item;

/**
 * Created by lemon on 4/6/2018.
 */

public interface ItemClickCallback {
    void onCallback(Item item, Why why);
    Class getRenderingClass();
}
