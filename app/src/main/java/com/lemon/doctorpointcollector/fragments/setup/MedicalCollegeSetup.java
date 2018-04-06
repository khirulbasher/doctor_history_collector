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

import com.lemon.androidlibs.database.realm.RealmDatabase;
import com.lemon.doctorpointcollector.R;
import com.lemon.doctorpointcollector.entity.MedicalCollege;
import com.lemon.doctorpointcollector.entity.Territory;
import com.lemon.doctorpointcollector.entity.enumeration.MedicalCollegeType;
import com.lemon.doctorpointcollector.utility.Utility;

/**
 * Created by lemon on 4/6/2018.
 */

@SuppressWarnings({"unused", "DefaultFileTemplate"})
public class MedicalCollegeSetup extends Fragment {
    private RealmDatabase realmDatabase;
    private EditText mc;
    private Spinner district;
    private Spinner division;
    private View view;
    private Spinner collegeType;
    private EditText website;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.mc_setup_fragment,container,false);
        realmDatabase=new RealmDatabase();
        mc=view.findViewById(R.id.mc_name);
        website=view.findViewById(R.id.mc_website);
        district=Utility.initSpinner(division,getActivity(),view);
        collegeType=Utility.initTypeSpinner(view,getActivity(), MedicalCollegeType.values(),"College Type");
        Utility.initSaveButton(view, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MedicalCollege medicalCollege=new MedicalCollege();
                medicalCollege.setName(mc.getText().toString());
                medicalCollege.setMedicalCollegeType(collegeType.getSelectedItem().toString());
                medicalCollege.setWebsite(website.getText().toString());
                medicalCollege.setTerritory(new Territory(district.getSelectedItem().toString()));
                if(medicalCollege.isValidObj()) {
                    if(medicalCollege.getId()==null)
                        save(medicalCollege);
                    else update(medicalCollege);

                }
                else clearScr(null);
            }
        });
        return view;
    }

    private void update(MedicalCollege medicalCollege) {
        clearScr("Update Successfully Completed");
    }

    private void save(MedicalCollege medicalCollege) {
        medicalCollege.setId(System.currentTimeMillis());
        realmDatabase.persist(medicalCollege);
        clearScr("Save Successfully Completed");
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
