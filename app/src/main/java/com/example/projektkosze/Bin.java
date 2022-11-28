package com.example.projektkosze;

import com.google.android.gms.maps.model.LatLng;

public class Bin {
    Double latitude;
    Double longitude;
    int binLevel;
    String binName;

    LatLng binCoord;

    public Bin(Double latitude, Double longtitude, String name) {
        this.latitude = latitude;
        this.longitude = longtitude;
        this.binName = name;

        binCoord = new LatLng(latitude, longitude);
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public LatLng getBinCoord() {
        return binCoord;
    }
}
