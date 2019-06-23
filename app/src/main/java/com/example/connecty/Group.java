package com.example.connecty;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Group implements Serializable {

    String ID;
    String Language;
    String Description;
    float x;
    float y;
    String TelephoneNumber;
    int Level;
    int ImageID;
    String Username;
    String Pass;

    public Group() {

    }

    public Group(String ID, String language, String description, float presentPosx, float getPresentPosy, String telephoneNumber, int level, int imageID, String username, String pass) {
        this.ID = ID;
        Language = language;
        Description = description;
        this.x = presentPosx;
        this.y = getPresentPosy;
        TelephoneNumber = telephoneNumber;
        Level = level;
        ImageID = imageID;
        Username = username;
        Pass = pass;
    }






}
