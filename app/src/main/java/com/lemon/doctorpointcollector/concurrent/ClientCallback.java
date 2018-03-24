package com.lemon.doctorpointcollector.concurrent;


import com.lemon.doctorpointcollector.utility.util.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by lemon on 3/10/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public interface ClientCallback {
    int PREPARED=400;

    void onCallback(Object obj, int code);
    void onCallback(List<Map<String, Object>> maps, int code);
    void onPrepareCallback(List<Item> items);
}
