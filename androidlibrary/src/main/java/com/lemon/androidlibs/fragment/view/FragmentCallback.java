package com.lemon.androidlibs.fragment.view;


import android.support.v4.app.Fragment;

import com.lemon.androidlibs.utility.item.Item;
import com.lemon.androidlibs.utility.enumeration.Why;
import com.lemon.androidlibs.utility.recycler.listener.ItemClickCallback;

import java.util.List;

/**
 * Created by lemon on 3/10/2018.
 */

@SuppressWarnings("ALL")
public interface FragmentCallback {
    List<Item> getItems();
    ItemClickCallback getListener();
    FragmentConversation getFragmentConversation();

    void showFragment(Fragment fragment, Object one, Why why);
}
