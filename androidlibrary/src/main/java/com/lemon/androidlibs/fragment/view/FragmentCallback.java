package com.lemon.androidlibs.fragment.view;


import com.lemon.androidlibs.utility.recycler.Item;
import com.lemon.androidlibs.utility.recycler.listener.ItemClickListener;

import java.util.List;

/**
 * Created by lemon on 3/10/2018.
 */

@SuppressWarnings("ALL")
public interface FragmentCallback {
    List<Item> getItems();
    ItemClickListener getListener();
}
