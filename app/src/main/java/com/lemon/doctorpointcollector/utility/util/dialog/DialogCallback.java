package com.lemon.doctorpointcollector.utility.util.dialog;

import com.lemon.doctorpointcollector.utility.util.Callback;

/**
 * Created by lemon on 3/16/2018.
 */

@SuppressWarnings("DefaultFileTemplate")
public interface DialogCallback extends Callback<Boolean> {
    @Override
    void onCallback(Boolean accept);
}
