package com.lemon.doctorpointcollector.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.lemon.doctorpointcollector.R;

/**
 * Created by lemon on 3/10/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "StringBufferReplaceableByString", "FieldCanBeLocal", "unused"})
public class ClinicFragment extends Fragment {
    private View view;
    private Button button;
    private EditText clinicName, disease;
    private LinearLayout linearLayout;
    private LayoutInflater layoutInflater;
    private LinearLayout child;
    private Spinner division,district;
    private int count;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        this.layoutInflater=inflater;
        initBasic(view);
        return view;
    }

    private void initBasic(View view) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.clinic,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                addDoctor();
                break;
            case R.id.action_remove:
                removeDoctor();
                break;
            case R.id.action_save:
                save();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {

    }

    @SuppressLint("InflateParams")
    private void addDoctor() {
        linearLayout.addView(child);
    }

    private void removeDoctor() {
        count=linearLayout.getChildCount();
        if(count>0)
            linearLayout.removeView(linearLayout.getChildAt(count-1));
    }
}
