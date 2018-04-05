package com.lemon.androidlibs.utility.recycler.listener;

import android.view.View;

/**
 * Created by lemon on 2/17/2018.
 */

@SuppressWarnings("unused")
public interface ItemClickListener {
    void onClickListener(View view, int position);
    void onLongClickListener(View view, int position);
}
