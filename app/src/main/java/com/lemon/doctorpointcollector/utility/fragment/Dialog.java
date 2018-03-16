package com.lemon.doctorpointcollector.utility.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.lemon.doctorpointcollector.utility.util.Callback;


/**
 * Created by lemon on 3/16/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class Dialog extends DialogFragment {
    private Callback<Boolean> callback;
    private String title="User Confirmation";
    private String message ="Are You Sure?";
    private String reject="Reject";
    private String accept="Accept";
    private boolean autoCancel=false;

    public Dialog setCallback(Callback<Boolean> callback) {
        this.callback=callback;
        return this;
    }

    public Dialog setAccept(String accept) {
        this.accept=accept;
        return this;
    }

    public Dialog setReject(String reject) {
        this.reject=reject;
        return this;
    }

    public Dialog setTitle(String title) {
        this.title=title;
        return this;
    }

    public Dialog setMessage(String message) {
        this.message=message;
        return this;
    }

    private Dialog setAutoCancel(boolean autoCancel) {
        this.autoCancel=autoCancel;
        return this;
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()).setTitle(title).setMessage(message).setNegativeButton(reject, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(callback!=null)
                    callback.onCallback(false);
                dismiss();
            }
        }).setPositiveButton(accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(callback!=null)
                    callback.onCallback(true);
                dismiss();
            }
        }).setCancelable(autoCancel).create();
    }
}
