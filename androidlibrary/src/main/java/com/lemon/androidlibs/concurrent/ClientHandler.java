package com.lemon.androidlibs.concurrent;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import com.lemon.androidlibs.utility.Item;

import java.util.List;

/**
 * Created by lemon on 3/10/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unchecked", "unused"})
public class ClientHandler extends Handler {
    private ClientCallback callback;

    public ClientHandler(ClientCallback callback) {
        this.callback = callback;
    }

    public ClientHandler(Looper looper, ClientCallback callback) {
        super(looper);
        this.callback = callback;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case ClientCallback.PREPARED:
                callback.onPrepareCallback((List<Item>) msg.obj);
        }
    }
}
