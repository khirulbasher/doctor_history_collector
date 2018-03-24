package com.lemon.doctorpointcollector.entity;

import com.lemon.doctorpointcollector.entity.enumeration.EmployeeType;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class Doctor extends RealmObject {
    @PrimaryKey
    private Long id;
    private String name;
    private Designation designation;
    private RealmList<Diseases> diseases;
    private Territory address;
    private RealmList<Contact> contacts;
    private RealmList<Clinic> clinics;
    private RealmList<Hospital> hospitals;
    private String employeeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public RealmList<Diseases> getDiseases() {
        return diseases;
    }

    public void setDiseases(RealmList<Diseases> diseases) {
        this.diseases = diseases;
    }

    public Territory getAddress() {
        return address;
    }

    public void setAddress(Territory address) {
        this.address = address;
    }

    public RealmList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(RealmList<Contact> contacts) {
        this.contacts = contacts;
    }

    public RealmList<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(RealmList<Clinic> clinics) {
        this.clinics = clinics;
    }

    public RealmList<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(RealmList<Hospital> hospitals) {
        this.hospitals = hospitals;
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
