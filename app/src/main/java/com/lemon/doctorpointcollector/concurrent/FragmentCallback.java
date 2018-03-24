package com.lemon.doctorpointcollector.concurrent;


import com.lemon.doctorpointcollector.utility.util.Item;

import java.util.List;

/**
 * Created by lemon on 3/10/2018.
 */

@SuppressWarnings("ALL")
public interface FragmentCallback {
    List<Item> getItems();
    String title();
}
