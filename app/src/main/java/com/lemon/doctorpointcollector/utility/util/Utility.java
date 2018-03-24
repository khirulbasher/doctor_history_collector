package com.lemon.doctorpointcollector.utility.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lemon.doctorpointcollector.R;
import com.lemon.doctorpointcollector.datastructure.FireList;
import com.lemon.doctorpointcollector.entity.enumeration.DiseaseType;
import com.lemon.doctorpointcollector.entity.enumeration.EmployeeType;

import java.util.Enumeration;
import java.util.TreeMap;

/**
 * Created by lemon on 3/15/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
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

    public static <E> Spinner initTypeSpinner(View parent , Context context, Object[] objects) {
        String[] params=new String[objects.length];

        for(int i=0;i<objects.length;i++)
            params[i]=objects[i].toString();
        Spinner spinner=parent.findViewById(R.id.global_type);
        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,params);
        spinner.setAdapter(stringArrayAdapter);

        return spinner;
    }
}
