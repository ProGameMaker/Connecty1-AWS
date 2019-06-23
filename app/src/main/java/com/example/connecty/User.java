package com.example.connecty;

import com.google.android.gms.maps.model.LatLng;

public class User {

    String ID;
    LatLng Pos;
    long Account;

    public User(String id, long account) {
        ID = id; Account = account;
    }

}
