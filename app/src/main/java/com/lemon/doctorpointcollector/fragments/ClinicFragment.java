package com.lemon.doctorpointcollector.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.lemon.doctorpointcollector.R;
import com.lemon.doctorpointcollector.entity.Clinic;
import com.lemon.doctorpointcollector.entity.Doctor;
import com.lemon.doctorpointcollector.utility.fragment.Dialog;
import com.lemon.doctorpointcollector.utility.util.Utility;
import com.lemon.doctorpointcollector.utility.util.dialog.DialogCallback;

import java.util.List;

/**
 * Created by lemon on 3/10/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "StringBufferReplaceableByString", "FieldCanBeLocal", "unused"})
public class ClinicFragment extends Fragment {
    private View view;
    private Button button;
    private EditText clinicName, disease;
    private Clinic clinic;
    private LinearLayout linearLayout;
    private LayoutInflater layoutInflater;
    private LinearLayout child;
    private Spinner division,district;
    private int count;

    private String[] dis=new String[0];



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.fragment_clinic, container, false);
        this.layoutInflater=inflater;
        clinic=new Clinic();
        initBasic(view);
        return view;
    }

    private void initBasic(View view) {
        button = view.findViewById(R.id.location);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dialog().setCallback(new DialogCallback() {
                    @Override
                    public void onCallback(Boolean accept) {
                        if(accept) {
                            FusedLocationProviderClient providerClient = LocationServices.getFusedLocationProviderClient(getActivity());
                            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},0);
                            }
                            providerClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                                @Override
                                public void onComplete(@NonNull Task<Location> task) {
                                    clinic.location.latitude=task.getResult().getLatitude()+"";
                                    clinic.location.longitude=task.getResult().getLongitude()+"";
                                    button.setText(new StringBuilder().append("[").append(clinic.location.latitude).append(",").append(clinic.location.longitude).append("]").toString());
                                }
                            });
                        }
                    }
                }).show(getFragmentManager(),"Dialog");
            }
        });
        clinicName=view.findViewById(R.id.clinic_name);
        disease=view.findViewById(R.id.disease);
        linearLayout=view.findViewById(R.id.linear_layout);
        Utility.initCommon(view,district,division);
        ArrayAdapter<String> divisionAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,Utility.getContinent().keySet().toArray(new String[0]));
        division.setAdapter(divisionAdapter);
        final ArrayAdapter<String> districtAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,dis);
        district.setAdapter(districtAdapter);
        division.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dis=Utility.getContinent().get(division.getSelectedItem()).toArray(new String[0]);
                districtAdapter.notifyDataSetChanged();
            }
        });
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
        Clinic clinic=new Clinic();
        count=linearLayout.getChildCount();
        clinic.name=clinicName.getText().toString();
        clinic.diseases=disease.getText().toString();
        clinic.location.division=division.getSelectedItem().toString();
        clinic.location.district=district.getSelectedItem().toString();
        if(count>0) {
            for(int i=0;i<count;i++) {
                child= (LinearLayout) linearLayout.getChildAt(i);
                Doctor doctor=new Doctor();
                doctor.name=((EditText)child.findViewById(R.id.doctor_name)).getText().toString();
                doctor.clinic=clinic.name;
                doctor.diseases=((EditText)child.findViewById(R.id.doctor_disease)).getText().toString();
                doctor.contact=((EditText)child.findViewById(R.id.doctor_contact)).getText().toString();
                doctor.doctorId=((EditText)child.findViewById(R.id.doctor_trid)).getText().toString();
                doctor.mail=((EditText)child.findViewById(R.id.doctor_mail)).getText().toString();
                doctor.medicalCollege=((EditText)child.findViewById(R.id.medical_college)).getText().toString();
                clinic.doctors.add(doctor);
            }
        }
    }

    @SuppressLint("InflateParams")
    private void addDoctor() {
        child= (LinearLayout) layoutInflater.inflate(R.layout.clinic,null);
        ((TextView)child.findViewById(R.id.doc_heading)).setText("Doctor:"+linearLayout.getChildCount());
        linearLayout.addView(child);
    }

    private void removeDoctor() {
        count=linearLayout.getChildCount();
        if(count>0)
            linearLayout.removeView(linearLayout.getChildAt(count-1));
    }
}
