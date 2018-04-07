package com.lemon.androidlibs.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lemon on 4/7/2018.
 */

@SuppressWarnings({"unused", "DefaultFileTemplate", "FieldCanBeLocal", "WeakerAccess"})
public class ThreadPool extends Thread implements OnTaskCompleteListener {
    private ThreadPoolExecutor threadPoolExecutor;
    private final int CORE=Runtime.getRuntime().availableProcessors();
    private final long keepAlive;
    private final TimeUnit timeUnit=TimeUnit.MILLISECONDS;
    private BlockingQueue<CustomRunnable> runnables;
    private int totalJob =0;
    private final Object lock;

    public ThreadPool(Object lock) {
        this(1,lock);
    }

    public ThreadPool(long keepAlive, Object lock) {
        this.keepAlive = keepAlive;
        this.lock = lock;
        runnables=new LinkedBlockingDeque<>();
    }

    public void addRunnable(CustomRunnable runnable) {
        runnable.setListener(this);
        runnables.add(runnable);
    }

    @Override
    public void run() {
        threadPoolExecutor=new ThreadPoolExecutor(CORE,CORE,keepAlive,timeUnit,new LinkedBlockingDeque<Runnable>());
        totalJob =runnables.size();
        for(Runnable runnable:runnables)
            threadPoolExecutor.execute(runnable);
        threadPoolExecutor.shutdown();
    }

    @Override
    public void complete(Long id) {
        synchronized (lock) {
            totalJob--;
            if(totalJob<=0)
                lock.notifyAll();
            interrupt();
        }
    }
}
