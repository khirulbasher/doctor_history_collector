package com.lemon.doctorpointcollector.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lemon on 3/11/2018.
 */

@SuppressWarnings("DefaultFileTemplate")
public class Clinic {
    public String name;
    public Location location;
    public String diseases;
    public List<Doctor> doctors;

    public Clinic() {
        this.doctors=new ArrayList<>();
        location=new Location();
    }
}
