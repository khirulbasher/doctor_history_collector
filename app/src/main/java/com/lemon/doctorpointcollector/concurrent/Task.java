package com.lemon.doctorpointcollector.concurrent;

/**
 * Created by lemon on 3/24/2018.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public interface Task {
    void doTask(ClientCallback clientCallback);
}
