package com.lemon.doctorpointcollector.entity;

import com.lemon.androidlibs.utility.item.ItemDescription;
import com.lemon.androidlibs.utility.item.ItemTitle;
import com.lemon.doctorpointcollector.entity.enumeration.ClinicType;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"unused", "DefaultFileTemplate"})
public class Clinic extends RealmObject {
    @PrimaryKey
    private Long id;
    @ItemTitle
    private String name;
    private String district;
    private String division;
    private Double latitude;
    private Double longitude;
    private RealmList<Doctor> doctors;
    private RealmList<Contact> contacts;
    @ItemDescription
    private String clinicType;
    private String website;
    private Boolean persisted;

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

    public RealmList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(RealmList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public RealmList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(RealmList<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getClinicType() {
        return clinicType;
    }

    public void setClinicType(ClinicType clinicType) {
        this.clinicType = clinicType.name();
    }

    public void setClinicType(String clinicType) {
        this.clinicType = clinicType;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Boolean isPersisted() {
        return persisted;
    }

    public void setPersisted(Boolean persisted) {
        this.persisted = persisted;
    }
}
