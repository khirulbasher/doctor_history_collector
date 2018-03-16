package com.lemon.doctorpointcollector.utility.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lemon.doctorpointcollector.R;
import com.lemon.doctorpointcollector.datastructure.FireList;

import java.util.TreeMap;

/**
 * Created by lemon on 3/15/2018.
 */

public class Utility {
    private static TreeMap<String,FireList> continent;

    public static TreeMap<String, FireList> getContinent() {
        if(continent==null)
            initContinent();
        return continent;
    }

    private static void initContinent() {
        continent=new TreeMap<>();
        continent.put("Barisal",new FireList().add("Barisal","Barguna","Bhola","Jhalokati","Patuakhali","Pirojpur"));
        continent.put("Chittagong",new FireList().add("Brahmanbaria","Comilla","Chandpur","Lakshmipur","Noakhali","Feni","Khagrachhari","Rangamati","Bandarban","Chittagong","Cox's Bazar"));
        continent.put("Dhaka",new FireList().add("Dhaka","Gazipur","Kishoreganj","Manikganj","Munshiganj","Narayanganj","Narsingdi","Tangail","Faridpur","Gopalganj","Madaripur","Rajbari","Shariatpur"));
        continent.put("Khulna", new FireList().add("Bagerhat","Chuadanga","Jessore","Jhenaidah","Khulna","Kushtia","Magura","Meherpur","Narail","Satkhira"));
        continent.put("Mymensingh",new FireList().add("Jamalpur","Mymensingh","Netrokona","Sherpur"));
        continent.put("Rajshahi",new FireList().add("Bogra","Chapainawabganj","Joypurhat","Naogaon","Natore","Pabna","Rajshahi","Sirajganj") );
        continent.put("Rangpur", new FireList().add("Dinajpur","Gaibandha","Kurigram","Lalmonirhat","Nilphamari","Panchagarh","Rangpur","Thakurgaon"));
        continent.put("Sylhet",new FireList().add("Habiganj","Moulvibazar","Sunamganj","Sylhet"));
    }

    public static void d(String message) {
        StackTraceElement traceElement=Thread.currentThread().getStackTrace()[3];
        Log.d("AndroidView","[]"+traceElement.getClassName().substring(traceElement.getClassName().lastIndexOf('.')+1)+":->"+traceElement.getMethodName()+";->"+traceElement.getLineNumber()+":->"+message);
    }

    public static void initCommon(View view, Spinner district, Spinner division) {
        district=view.findViewById(R.id.district_spinner);
        division=view.findViewById(R.id.division_spinner);
    }
}
