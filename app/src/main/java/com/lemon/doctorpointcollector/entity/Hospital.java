package com.lemon.doctorpointcollector.entity;

import com.lemon.androidlibs.utility.item.ItemDescription;
import com.lemon.androidlibs.utility.item.ItemTitle;
import com.lemon.doctorpointcollector.entity.enumeration.HospitalType;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class Hospital extends RealmObject {
    @PrimaryKey
    private Long id;
    @ItemTitle
    private String name;
    private String district;
    private String division;
    private Double latitude;
    private Double longitude;
    private RealmList<Diseases> diseases;
    private RealmList<Long> doctors;
    private RealmList<Contact> contacts;
    @ItemDescription
    private String hospitalType;
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

    public RealmList<Diseases> getDiseases() {
        return diseases;
    }

    public void setDiseases(RealmList<Diseases> diseases) {
        this.diseases = diseases;
    }

    public RealmList<Long> getDoctors() {
        return doctors;
    }

    public void setDoctors(RealmList<Long> doctors) {
        this.doctors = doctors;
    }

    public RealmList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(RealmList<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(HospitalType hospitalType) {
        this.hospitalType = hospitalType.name();
    }

    public String getWebsite() {
        return website;
    }

    public void setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
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
