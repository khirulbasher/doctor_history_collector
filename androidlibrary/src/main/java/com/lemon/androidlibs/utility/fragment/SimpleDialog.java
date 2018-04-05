package com.lemon.androidlibs.utility.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by lemon on 3/16/2018.
 */

public class SimpleDialog extends DialogFragment {

    private String title="User Confirmation";
    private String message ="Are You Sure?";

    public SimpleDialog setTitle(String title) {
        this.title=title;
        return this;
    }

    public SimpleDialog setMessage(String message) {
        this.message=message;
        return this;
    }
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()).setTitle(title).setMessage(message).setCancelable(true).setNegativeButton("Cancel",null).create();
    }
}
