package com.example.projektkosze;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

import com.google.android.gms.maps.model.LatLng;

public class Bin implements Parcelable {

    double latitude;
    double longitude;
    String binName;
    int binLevel;
    LatLng binCoord;
    //    AirPolution airPolution = new AirPolution();
//    WeatherClass weatherClass = new WeatherClass();
    double no2, pm10, o3;
    Random number = new Random();

    public Bin(double latitude, double longtitude, String name) {
        this.latitude = latitude;
        this.longitude = longtitude;
        this.binName = name;
        binLevel = number.nextInt(100);

        binCoord = new LatLng(latitude, longtitude);
    }

    public String getWeatherUrl() {
        return "https://api.openweathermap.org/data/2.5/weather?lat=" + Double.toString(latitude) + "&lon=" + Double.toString(longitude) + "&appid=b08fbe438e5185b224865e9f8dd84222";
    }

    public String getAirPolutionUrl() {
        return "https://api.openweathermap.org/data/2.5/air_pollution?lat=" + Double.toString(latitude) + "&lon=" + Double.toString(longitude) + "&appid=b08fbe438e5185b224865e9f8dd84222";
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
