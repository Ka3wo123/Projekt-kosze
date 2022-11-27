package com.example.projektkosze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    // "https://www.google.com/maps/dir/?api=1&origin=&destination=&travelmode=driving&waypoints=%7C"

    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Testing initial points
        LinkedList<String> waypoints = new LinkedList<>();

        String origin = "50.01775241020009, 20.98671753344372";
        String dest = "50.01445338043756, 21.01438738781465";
        waypoints.add("50.01768361637082, 21.00522672993565");
        waypoints.add("50.0143074088555, 21.012408714218996");

        URL = "https://www.google.com/maps/dir/?api=1&origin="+origin+"&destination=" +
                dest + "&travelmode=driving&waypoints=";

        StringBuilder buffer = new StringBuilder(32);

        buffer.append(URL);

        for (String point: waypoints){
            buffer.append(point).append("%7C");
        }

        URL = buffer.toString();

        Button mapButton = (Button) findViewById(R.id.button_launch_maps);
        mapButton.setOnClickListener(this);

    }

    private String createRouteURL(){
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.button_launch_maps):{
                Intent intentMap = new Intent(Intent.ACTION_VIEW);
                intentMap.setData(Uri.parse(URL));
                startActivity(intentMap);

                // 7C do kolejnych waypointow
                // https://www.google.com/maps/dir/?api=1&origin=Jasna+9+Tarnów&destination=Widok+29+Tarnów&travelmode=driving&waypoints=Jasna+71+Tarnów%7CSłoneczna+26+Tarnów"

                break;
            }
        }
    }
}