package com.example.projektkosze;

import com.google.android.gms.maps.model.LatLng;

public class Bin {
    Double latitude;
    Double longitude;
    String binName;
    int binLevel;
    LatLng binCoord;
    AirPolution airPolution = new AirPolution();
    WeatherClass weatherClass = new WeatherClass();


    public Bin(Double latitude, Double longtitude, String name) {
        this.latitude = latitude;
        this.longitude = longtitude;
        this.binName = name;

        binCoord = new LatLng(latitude, longitude);
    }
    public String getWeatherUrl(){
        return "http://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=a7411493638501a02081b68b7092f328";
    }
    public String getAirPolutionUrl(){
        return "http://api.openweathermap.org/data/2.5/air_pollution?lat=" + latitude + "&lon=" + longitude + "&appid=a7411493638501a02081b68b7092f328";
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
