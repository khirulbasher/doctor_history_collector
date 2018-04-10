package com.lemon.doctorpointcollector.entity;

import com.lemon.androidlibs.utility.item.ItemDescription;
import com.lemon.androidlibs.utility.item.ItemTitle;
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
    @ItemTitle
    private String name;
    @ItemDescription
    private Designation designation;
    private RealmList<Diseases> diseases;
    private String district;
    private String division;
    private Double latitude;
    private Double longitude;
    private RealmList<Contact> contacts;
    private RealmList<Clinic> clinics;
    private RealmList<Hospital> hospitals;
    private String employeeType;
    private Boolean persisted;
    private RealmList<MedicalCollege> medicalColleges;

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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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

    public Boolean isPersisted() {
        return persisted;
    }

    public void setPersisted(Boolean persisted) {
        this.persisted = persisted;
    }

    public RealmList<MedicalCollege> getMedicalColleges() {
        return medicalColleges;
    }

    public void setMedicalColleges(RealmList<MedicalCollege> medicalColleges) {
        this.medicalColleges = medicalColleges;
    }
}
