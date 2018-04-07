package com.lemon.doctorpointcollector.entity;

import com.lemon.androidlibs.utility.item.ItemDescription;
import com.lemon.androidlibs.utility.item.ItemTitle;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class Contact extends RealmObject {
    @PrimaryKey
    private Long id;
    @ItemTitle
    private String phone;
    @ItemDescription
    private String mail;
    private String facebookId;
    private Boolean persisted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public Boolean isPersisted() {
        return persisted;
    }

    public void setPersisted(Boolean persisted) {
        this.persisted = persisted;
    }
}
