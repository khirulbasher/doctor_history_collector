package com.lemon.doctorpointcollector.fragments.setup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.lemon.androidlibs.database.realm.RealmDatabase;

/**
 * Created by lemon on 4/6/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class DoctorSetup extends Fragment {
    private RealmDatabase realmDatabase;
    private EditText doctor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void clearScr(@Nullable String msg) {
        if(msg!=null)
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        doctor.setText("");
    }

    @Override
    public void onDestroyView() {
        if(realmDatabase!=null)
            realmDatabase.close();
        super.onDestroyView();
    }
}
