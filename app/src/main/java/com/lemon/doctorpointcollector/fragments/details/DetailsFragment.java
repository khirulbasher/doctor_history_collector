package com.lemon.doctorpointcollector.fragments.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemon.doctorpointcollector.R;
import com.lemon.doctorpointcollector.fragments.callback.SetupCallback;

/**
 * Created by lemon on 4/7/2018.
 */

public class DetailsFragment extends Fragment {
    private Object object;
    private SetupCallback setupCallback;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.details_fragment,container,false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
