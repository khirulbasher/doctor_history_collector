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

import com.lemon.androidlibs.database.realm.RealmDatabase;
import com.lemon.androidlibs.utility.fragment.SimpleDialog;
import com.lemon.doctorpointcollector.R;
import com.lemon.doctorpointcollector.entity.MedicalCollege;
import com.lemon.doctorpointcollector.entity.enumeration.MedicalCollegeType;
import com.lemon.doctorpointcollector.fragments.callback.SetupCallback;
import com.lemon.doctorpointcollector.utility.Utility;

import java.util.ArrayList;

/**
 * Created by lemon on 4/6/2018.
 */

@SuppressWarnings({"unused", "DefaultFileTemplate", "FieldCanBeLocal"})
public class MedicalCollegeSetup extends Fragment {
    private RealmDatabase realmDatabase;
    private EditText mc;
    private Spinner district;
    private Spinner division;
    private View view;
    private Spinner collegeType;
    private EditText website;
    private MedicalCollege medicalCollege;
    private SetupCallback setupCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.mc_setup_fragment,container,false);
        realmDatabase=new RealmDatabase();
        mc=view.findViewById(R.id.mc_name);
        website=view.findViewById(R.id.mc_website);
        division=view.findViewById(R.id.division_spinner);
        district=Utility.initSpinner(division,getActivity(),view);
        collegeType=Utility.initTypeSpinner(view,getActivity(), MedicalCollegeType.values(),"College Type");
        Utility.initSaveButton(view, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(!mc.getText().toString().isEmpty()) {
                        medicalCollege.setName(mc.getText().toString());
                        medicalCollege.setMedicalCollegeType(collegeType.getSelectedItem().toString());
                        medicalCollege.setWebsite(website.getText().toString());
                        medicalCollege.setDistrict(district.getSelectedItem().toString());
                        medicalCollege.setDivision(division.getSelectedItem().toString());
                        if(medicalCollege.getId()==null)
                            save(medicalCollege);
                        else update(medicalCollege);

                    }
                    else clearScr(null);
                } catch (Exception e) {
                    e.printStackTrace();
                    new SimpleDialog().setMessage(e.getMessage()).show(getActivity().getSupportFragmentManager(),"");
                }
            }
        });
        if(medicalCollege==null || medicalCollege.getId()==null)
            medicalCollege=new MedicalCollege();
        else {
            mc.setText(medicalCollege.getName());
            collegeType.setSelection(MedicalCollegeType.valueOf(medicalCollege.getMedicalCollegeType()).ordinal());
            website.setText(medicalCollege.getWebsite());
            division.setSelection(new ArrayList<>(Utility.getContinent().keySet()).indexOf(medicalCollege.getDivision()));
            district.setSelection(new ArrayList<>(Utility.getContinent().get(medicalCollege.getDivision())).indexOf(medicalCollege.getDistrict()));
        }
        return view;
    }

    /*Update only values*/
    private void update(final MedicalCollege medicalCollege) {
        realmDatabase.update(medicalCollege.getId(),medicalCollege,MedicalCollege.class);
        clearScr("Update Successfully Completed");
    }

    /*For Save and set primary key*/
    private void save(MedicalCollege medicalCollege) {
        medicalCollege.setId(System.currentTimeMillis());
        realmDatabase.persist(medicalCollege);
        clearScr("Save Successfully Completed");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setupCallback= (SetupCallback) context;
        medicalCollege= (MedicalCollege) setupCallback.getRenderingObject();
        setupCallback.makeRenderingObjectNull();
    }

    private void clearScr(@Nullable String msg) {
        if(msg==null)
            Toast.makeText(getActivity(), "Not a Valid Medical College...", Toast.LENGTH_SHORT).show();

        else {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            mc.setText("");
            website.setText("");
        }
    }

    @Override
    public void onDestroyView() {
        if(realmDatabase!=null)
            realmDatabase.close();
        super.onDestroyView();
    }
}
