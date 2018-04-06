package com.lemon.androidlibs.fragment.view;

import com.lemon.androidlibs.utility.enumeration.Why;

import java.util.Map;

/**
 * Created by lemon on 4/6/2018.
 */

@SuppressWarnings({"unused", "DefaultFileTemplate"})
public interface FragmentConversation {
    void onConversation(Class entityClass, Why why, Map<Why,Object> whyObjectMap);
}
