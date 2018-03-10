package com.lemon.doctorpointcollector.fragments;

import android.Manifest;
import android.app.ActionBar;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.lemon.doctorpointcollector.MainActivity;
import com.lemon.doctorpointcollector.R;
import com.lemon.doctorpointcollector.entity.Clinic;

/**
 * Created by lemon on 3/10/2018.
 */

@SuppressWarnings("DefaultFileTemplate")
public class ClinicFragment extends Fragment {
    View view;
    Button button;
    EditText clinicName, disease;
    Clinic clinic;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_clinic, container, false);
        clinic=new Clinic();
        initBasic(view);
        return view;
    }

    private void initBasic(View view) {
        button = view.findViewById(R.id.location);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FusedLocationProviderClient providerClient = LocationServices.getFusedLocationProviderClient(getActivity());
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},0);
                }
                providerClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        clinic.location.latitude=task.getResult().getLatitude()+"";
                        clinic.location.longitude=task.getResult().getLongitude()+"";
                        button.setText("["+clinic.location.latitude+","+clinic.location.longitude+"]");
                    }
                });
            }
        });
        clinicName=view.findViewById(R.id.clinic_name);
        disease=view.findViewById(R.id.disease);
    }
}
