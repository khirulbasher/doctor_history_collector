package com.lemon.androidlibs.concurrent;

import android.os.Handler;


import com.lemon.androidlibs.utility.recycler.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by lemon on 3/24/2018.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public class TaskManager<S,R> extends Thread implements ClientCallback{
    private Handler handler;
    private Task task;

    public TaskManager(Handler handler, Task task) {
        this.handler = handler;
        this.task = task;
        setPriority(MAX_PRIORITY);
    }

    @Override
    public void run() {
        task.doTask(this);
    }

    @Override
    public void onCallback(Object obj, int code) {
        handler.obtainMessage(ClientCallback.PREPARED,obj).sendToTarget();
    }

    @Override
    public void onCallback(List<Map<String, Object>> maps, int code) {
        handler.obtainMessage(ClientCallback.PREPARED,maps).sendToTarget();
    }

    @Override
    public void onPrepareCallback(List<Item> items) {
        handler.obtainMessage(ClientCallback.PREPARED,items).sendToTarget();
    }
}
