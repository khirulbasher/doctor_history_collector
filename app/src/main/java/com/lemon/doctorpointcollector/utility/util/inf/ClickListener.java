package com.lemon.doctorpointcollector.utility.util.inf;

import android.view.View;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings("DefaultFileTemplate")
public interface ClickListener {

    void onClick(View view,int position);

    void onLongClick(View view,int position);

}
