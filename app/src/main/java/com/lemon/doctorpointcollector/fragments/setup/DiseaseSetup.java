package com.lemon.doctorpointcollector.fragments.setup;

import android.content.Context;
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
import com.lemon.doctorpointcollector.fragments.callback.SetupCallback;
import com.lemon.doctorpointcollector.utility.Utility;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal", "SameParameterValue"})
public class DiseaseSetup extends Fragment {
    private View view;
    private EditText disease;
    private RealmDatabase realmDatabase;
    private Spinner spinner;
    private Diseases diseasesSetup;
    private SetupCallback setupCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.disease_setup_frag,container,false);
        disease =view.findViewById(R.id.disease_field);
        view.findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!disease.getText().toString().isEmpty()) {
                    diseasesSetup.setDisease(disease.getText().toString());
                    diseasesSetup.setDiseaseType(spinner.getSelectedItem().toString());
                    if(diseasesSetup.getId()==null)
                        save(diseasesSetup);
                    else update(diseasesSetup);
                    diseasesSetup=new Diseases();
                }
            }
        });
        realmDatabase=new RealmDatabase();
        spinner= Utility.initTypeSpinner(view,getActivity(), DiseaseType.values(),"Disease Type");
        if(diseasesSetup!=null) {
            disease.setText(diseasesSetup.getDisease());
            spinner.setSelection(DiseaseType.valueOf(diseasesSetup.getDiseaseType()).ordinal());
        }
        else diseasesSetup=new Diseases();
        return view;
    }

    private void update(Diseases diseasesSetup) {
        realmDatabase.update(diseasesSetup.getId(),diseasesSetup,diseasesSetup.getClass());
        clearScr("Disease has been Updated Successfully");
    }

    private void save(Diseases diseasesSetup) {
        diseasesSetup.setId(System.currentTimeMillis());
        realmDatabase.persist(diseasesSetup);
        clearScr("Disease has been saved Successfully");
    }

    @Override
    public void onAttach(Context context) {
        setupCallback=(SetupCallback)context;
        diseasesSetup= (Diseases) setupCallback.getRenderingObject();
        super.onAttach(context);
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
