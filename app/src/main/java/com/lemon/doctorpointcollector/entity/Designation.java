package com.lemon.doctorpointcollector.entity;

import com.lemon.doctorpointcollector.entity.enumeration.EmployeeType;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class Designation extends RealmObject {
    @PrimaryKey
    private Long id;
    private String designation;
    private String employeeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType.name();
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
}
