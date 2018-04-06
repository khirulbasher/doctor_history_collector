package com.lemon.doctorpointcollector.entity;

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
    private String name;
    private Territory territory;
    private RealmList<Diseases> diseases;
    private RealmList<Doctor> doctors;
    private RealmList<Contact> contacts;
    private String hospitalType;
    private String website;
    private boolean persisted;

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

    public RealmList<Diseases> getDiseases() {
        return diseases;
    }

    public void setDiseases(RealmList<Diseases> diseases) {
        this.diseases = diseases;
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

    public boolean isPersisted() {
        return persisted;
    }

    public void setPersisted(boolean persisted) {
        this.persisted = persisted;
    }
}
