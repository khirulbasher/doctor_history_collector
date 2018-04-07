package com.lemon.androidlibs.concurrent;

@SuppressWarnings("unused")
public class CustomRunnable implements Runnable {
    private OnTaskCompleteListener listener;
    private Task task;

    public CustomRunnable(Task task) {
        this.task = task;
    }

    public CustomRunnable(OnTaskCompleteListener listener) {
        this.listener = listener;
    }

    public void setListener(OnTaskCompleteListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        task.doTask(null);
        listener.complete(0L);
    }
}
