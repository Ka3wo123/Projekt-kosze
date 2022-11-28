package com.example.projektkosze;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class Bin implements Parcelable {
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

    private Bin(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        binLevel = in.readInt();
        binName = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeInt(binLevel);
        parcel.writeString(binName);
    }

    public static final Parcelable.Creator<Bin> CREATOR = new Parcelable.Creator<Bin>() {
        public Bin createFromParcel(Parcel in) {
            return new Bin(in);
        }

        public Bin[] newArray(int size) {
            return new Bin[size];
        }
    };
}
