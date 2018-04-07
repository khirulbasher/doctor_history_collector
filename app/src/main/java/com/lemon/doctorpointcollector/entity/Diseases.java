package com.lemon.doctorpointcollector.entity;

import com.lemon.androidlibs.utility.item.ItemDescription;
import com.lemon.androidlibs.utility.item.ItemTitle;
import com.lemon.doctorpointcollector.entity.enumeration.DiseaseType;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class Diseases extends RealmObject {
    @PrimaryKey
    private Long id;
    @ItemTitle
    private String disease;
    @ItemDescription
    private String diseaseType;
    private Boolean persisted;

    public Diseases() {
    }

    public Diseases(String disease,String diseaseType) {
        this.disease = disease;
        this.diseaseType=diseaseType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public void setDiseaseType(DiseaseType diseaseType) {
        this.diseaseType = diseaseType.name();
    }

    public Boolean isPersisted() {
        return persisted;
    }

    public void setPersisted(Boolean persisted) {
        this.persisted = persisted;
    }

}
