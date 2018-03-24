package com.lemon.doctorpointcollector.concurrent;


import java.util.List;

/**
 * Created by lemon on 3/18/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "WeakerAccess", "unused"})
public class ConverterThread<S,R> extends Thread {
    private ClientCallback clientCallback;
    private Converter<S,R> converter;
    private List<S> sList;

    @SuppressWarnings("unchecked")
    public ConverterThread(ClientCallback clientCallback, Converter converter, List<S> ls) {
        this(clientCallback,converter,ls,false);
    }


    @SuppressWarnings("unchecked")
    public ConverterThread(ClientCallback clientCallback, Converter converter, List<S> ls,boolean autoStart) {
        this.clientCallback = clientCallback;
        this.converter = converter;
        this.sList = ls;
        setPriority(MAX_PRIORITY);
        if(autoStart)
            start();
    }

    @Override
    public void run() {
        clientCallback.onCallback(converter.convert(sList), ClientCallback.PREPARED);
    }
}
