package com.lemon.doctorpointcollector.entity;


import com.lemon.doctorpointcollector.entity.enumeration.MedicalCollegeType;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class MedicalCollege extends RealmObject {
    @PrimaryKey
    private Long id;
    private String name;
    private Territory territory;
    private String website;
    private String medicalCollegeType;

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

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
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

    public boolean isValidObj() {
        return name!=null&&medicalCollegeType!=null&&!name.isEmpty()&&!medicalCollegeType.isEmpty();
    }

}
