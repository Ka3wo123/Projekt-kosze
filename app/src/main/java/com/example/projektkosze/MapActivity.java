package com.example.projektkosze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    // "https://www.google.com/maps/dir/?api=1&origin=&destination=&travelmode=driving&waypoints=%7C"

    private String URL;
    LinkedList<Integer> visited;

    private Bin[] myBin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();

        // Pobieranie danych z parent Activity
        String[] arrayDistance = intent.getStringArrayExtra("MATRIXDISTANCE");
        int sizeArray = intent.getIntExtra("ARRAYLEN", 0);

        myBin = new Bin[sizeArray];

        for (int i = 0; i < sizeArray; i++){
            myBin[i] = intent.getParcelableExtra("BINLAT"+i);
        }


        // Musimy tutaj dodac waypoints, src, dest z komiwojażera.
        TSP tsp = new TSP(sizeArray, arrayDistance);
        int ans = Integer.MAX_VALUE;
        tsp.solveTSP();

        visited = tsp.getNodesIdx(); // NODES

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
                LinkedList<String> waypoints = new LinkedList<>();

                for (Integer idx : visited){
                    String tmp = myBin[idx].getLatitude() + "," + myBin[idx].getLongitude();
                    waypoints.add(tmp);
                }

                String src = waypoints.getFirst().toString();
                waypoints.removeFirst();

                String dst = waypoints.getLast().toString();
                waypoints.removeLast();

                URL = createRouteURL(src, dst, waypoints);

                intentMap.setData(Uri.parse(URL));
                startActivity(intentMap);
                break;
            }
        }
    }
}