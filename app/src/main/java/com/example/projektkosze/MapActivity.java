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
    LinkedList<String> waypoints = new LinkedList<>();
    int sizeArray;
    String [] arrayDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();

        arrayDistance = intent.getStringArrayExtra("MATRIXDISTANCE");
        
        // Musimy tutaj dodac waypoints, src, dest z komiwojażera.

        Button mapButton = (Button) findViewById(R.id.button_launch_maps);
        mapButton.setOnClickListener(this);

    }

    private String createRouteURL(String origin, String dest, LinkedList<String> waypoints){
        URL = "https://www.google.com/maps/dir/?api=1&origin="+origin+"&destination=" +
                dest + "&travelmode=driving&waypoints=";

        StringBuilder buffer = new StringBuilder(32);

        buffer.append(URL);

        for (String point: waypoints){
            buffer.append(point).append("%7C");
        }

        return buffer.toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.button_launch_maps):{
                Intent intentMap = new Intent(Intent.ACTION_VIEW);
                intentMap.setData(Uri.parse(URL));
                startActivity(intentMap);
                break;
            }
        }
    }
}