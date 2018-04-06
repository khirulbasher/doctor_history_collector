package com.lemon.doctorpointcollector.fragments.setup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lemon.doctorpointcollector.R;
import com.lemon.androidlibs.database.realm.RealmDatabase;
import com.lemon.doctorpointcollector.entity.Diseases;
import com.lemon.doctorpointcollector.entity.enumeration.DiseaseType;
import com.lemon.doctorpointcollector.utility.Utility;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public class DiseaseSetup extends Fragment {
    private View view;
    private EditText disease;
    private RealmDatabase realmDatabase;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.disease_setup_frag,container,false);
        disease =view.findViewById(R.id.disease_field);
        view.findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realmDatabase.persist(new Diseases(disease.getText().toString(),spinner.getSelectedItem().toString()));
                clearScr("Disease Successfully Saved...");
            }
        });
        realmDatabase=new RealmDatabase();
        spinner= Utility.initTypeSpinner(view,getActivity(), DiseaseType.values(),"Disease Type");
        return view;
    }

    private void clearScr(@Nullable String msg) {
        if(msg!=null)
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        disease.setText("");
    }

    @Override
    public void onDestroyView() {
        if(realmDatabase!=null)
            realmDatabase.close();
        super.onDestroyView();
    }
}
