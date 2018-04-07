package com.lemon.doctorpointcollector.utility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.location.LocationServices;
import com.lemon.androidlibs.datastructure.FireList;
import com.lemon.androidlibs.utility.enumeration.Why;
import com.lemon.doctorpointcollector.R;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lemon on 3/15/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused", "ConstantConditions"})
public class Utility {
    private static TreeMap<String, FireList> continent;

    public static TreeMap<String, FireList> getContinent() {
        if (continent == null)
            initContinent();
        return continent;
    }

    private static void initContinent() {
        continent = new TreeMap<>();
        continent.put("Barisal", new FireList().add("Barisal", "Barguna", "Bhola", "Jhalokati", "Patuakhali", "Pirojpur"));
        continent.put("Chittagong", new FireList().add("Brahmanbaria", "Comilla", "Chandpur", "Lakshmipur", "Noakhali", "Feni", "Khagrachhari", "Rangamati", "Bandarban", "Chittagong", "Cox's Bazar"));
        continent.put("Dhaka", new FireList().add("Dhaka", "Gazipur", "Kishoreganj", "Manikganj", "Munshiganj", "Narayanganj", "Narsingdi", "Tangail", "Faridpur", "Gopalganj", "Madaripur", "Rajbari", "Shariatpur"));
        continent.put("Khulna", new FireList().add("Bagerhat", "Chuadanga", "Jessore", "Jhenaidah", "Khulna", "Kushtia", "Magura", "Meherpur", "Narail", "Satkhira"));
        continent.put("Mymensingh", new FireList().add("Jamalpur", "Mymensingh", "Netrokona", "Sherpur"));
        continent.put("Rajshahi", new FireList().add("Bogra", "Chapainawabganj", "Joypurhat", "Naogaon", "Natore", "Pabna", "Rajshahi", "Sirajganj"));
        continent.put("Rangpur", new FireList().add("Dinajpur", "Gaibandha", "Kurigram", "Lalmonirhat", "Nilphamari", "Panchagarh", "Rangpur", "Thakurgaon"));
        continent.put("Sylhet", new FireList().add("Habiganj", "Moulvibazar", "Sunamganj", "Sylhet"));
    }

    public static void d(String message) {
        StackTraceElement traceElement = Thread.currentThread().getStackTrace()[3];
        Log.d("AndroidView", "[]" + traceElement.getClassName().substring(traceElement.getClassName().lastIndexOf('.') + 1) + ":->" + traceElement.getMethodName() + ";->" + traceElement.getLineNumber() + ":->" + message);
    }

    public static <E> Spinner initTypeSpinner(View parent, Context context, Object[] objects, String title) {
        String[] params = new String[objects.length];
        ((TextView) parent.findViewById(R.id.global_spinner_text)).setText(title);
        for (int i = 0; i < objects.length; i++)
            params[i] = objects[i].toString();
        Spinner spinner = parent.findViewById(R.id.global_type);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, params);
        spinner.setAdapter(stringArrayAdapter);

        return spinner;
    }

    public static Spinner initSpinner(Spinner division, final Context context, View parent) {
        final Spinner finalDistrict = parent.findViewById(R.id.district_spinner);
        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, getContinent().keySet().toArray(new String[0]));
        division.setAdapter(divisionAdapter);
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, getContinent().get(division.getSelectedItem().toString()));
        finalDistrict.setAdapter(districtAdapter);

        final Spinner finalDivision = division;
        division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, getContinent().get(finalDivision.getSelectedItem().toString()));
                finalDistrict.setAdapter(districtAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return finalDistrict;
    }

    public static Map<Why, Object> getWhyMap(Why why, Object obj) {
        Map<Why, Object> whyObjectMap = new HashMap<>();
        whyObjectMap.put(why, obj);
        return whyObjectMap;
    }

    public static void initSaveButton(View view, View.OnClickListener onClickListener) {
        view.findViewById(R.id.save_button).setOnClickListener(onClickListener);
    }

    public Location getLocation(Activity context) {
        LocationServices locationServices;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        }
        String locationProvider = locationManager.getBestProvider(new Criteria(), false);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},200 );
        }
        return locationManager.getLastKnownLocation(locationProvider);
    }
}
