package com.lemon.androidlibs.utility.inf;

/**
 * Created by lemon on 3/16/2018.
 */

public interface Callback<P> {
    void onCallback(P send,boolean accept);
}
