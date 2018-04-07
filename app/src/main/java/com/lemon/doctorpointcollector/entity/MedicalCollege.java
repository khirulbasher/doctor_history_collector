package com.lemon.doctorpointcollector.entity;


import com.lemon.doctorpointcollector.entity.enumeration.MedicalCollegeType;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused", "WeakerAccess"})
public class MedicalCollege extends RealmObject {
    @PrimaryKey
    private Long id;
    private String name;
    private String district;
    private String division;
    private Double latitude;
    private Double longitude;
    private String website;
    private String medicalCollegeType;
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



    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMedicalCollegeType() {
        return medicalCollegeType;
    }

    public void setMedicalCollegeType(String medicalCollegeType) {
        this.medicalCollegeType = medicalCollegeType;
    }
    public void setMedicalCollegeType(MedicalCollegeType medicalCollegeType) {
        this.medicalCollegeType = medicalCollegeType.name();
    }

    public Boolean isPersisted() {
        return persisted;
    }

    public void setPersisted(Boolean persisted) {
        this.persisted = persisted;
    }
}
