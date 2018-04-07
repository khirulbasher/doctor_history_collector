package com.lemon.doctorpointcollector.fragments.setup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemon.doctorpointcollector.R;

/**
 * Created by lemon on 4/7/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class DesignationSetup extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.designation_setup_frag,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
