package com.example.projektkosze;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Bin[] binArray = {
            new Bin(50.023753659606214, 20.98423180440173, "Hackathon"),
            new Bin(50.007863015218284, 20.97983693647739, "MPGiK"),
            new Bin(49.99864825315144, 20.995590540257002, "Zamenhofa 41"),
            new Bin(49.99720316765519, 20.996658313375047, "Podzamcze 17"),
            new Bin(49.997547400196744, 20.9983932558752, "Podzamcze 29"),
            new Bin(49.99750064716965, 20.998949885944505, "Al. Tarnowskich 32"),
    };

    // Zakladamy wiecej niz 3 kosze zawsze
    // Wzor na ilosc polaczen w grafie binArray.length + (binArray.length * (binArray.length-3)) / 2
    private String[] arrayOfDistances = new String[binArray.length * binArray.length];

    // For URL request
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mQueue = Volley.newRequestQueue(this);

        // Initializing array to "0"
        for (int i = 0; i < binArray.length; i++) {
            for (int j = 0; j < binArray.length; j++) {
                arrayOfDistances[j + binArray.length * i] = "0";
            }
        }

        // Create URL and
        for (int i = 0; i < binArray.length; i++) {
            for (int j = 0; j < binArray.length; j++) {

                String url = getRequestUrl(binArray[i].getBinCoord(), binArray[j].getBinCoord());
                jsonParse(url, i, j);
            }
        }

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Reading");
        progress.setMessage("Getting bin data...");
        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);

    }

    public void goToListaBio(View view) {
        Intent intent = new Intent(this, ListaBio.class);

        intent.putExtra("MATRIXDISTANCE", arrayOfDistances);
        intent.putExtra("ARRAYLEN", binArray.length);

        // Putting array as single elements

        for (int i = 0; i < binArray.length; i++) {
            intent.putExtra("BINLAT" + i, binArray[i]);

        }
        startActivity(intent);
    }

    public void goToListaProgress(View view) {
        Intent intent = new Intent(this, ListaProgress.class);

        intent.putExtra("MATRIXDISTANCE", arrayOfDistances);
        intent.putExtra("ARRAYLEN", binArray.length);

        // Putting array as single elements
        for (int i = 0; i < binArray.length; i++) {
            intent.putExtra("BINLAT" + i, binArray[i]);
        }
        startActivity(intent);
    }

    public void goToCallendar(View view) {
        Intent intent = new Intent(this, Schedule.class);

        intent.putExtra("MATRIXDISTANCE", arrayOfDistances);
        intent.putExtra("ARRAYLEN", binArray.length);

        // Putting array as single elements
        for (int i = 0; i < binArray.length; i++) {
            intent.putExtra("BINLAT" + i, binArray[i]);
        }
        startActivity(intent);
    }

    public void goToMapActivity(View view) {
        Intent intent = new Intent(this, MapActivity.class);

        intent.putExtra("MATRIXDISTANCE", arrayOfDistances);
        intent.putExtra("ARRAYLEN", binArray.length);

        // Putting array as single elements
        for (int i = 0; i < binArray.length; i++) {
            intent.putExtra("BINLAT" + i, binArray[i]);
        }


        startActivity(intent);
    }


    private void jsonParse(String url, final int i, final int j) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray routes = response.getJSONArray("routes");
                JSONObject object = routes.getJSONObject(0);
                JSONArray legs = object.getJSONArray("legs");
                JSONObject legsObject = legs.getJSONObject(0);

                JSONObject distance = legsObject.getJSONObject("distance");
                String distanceString = distance.getString("value");

                arrayOfDistances[j + binArray.length * i] = distanceString;


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, Throwable::printStackTrace);
        mQueue.add(request);

    }

    private String getRequestUrl(LatLng origin, LatLng destination) {
        String strOrigin = "origin=" + origin.latitude + "," + origin.longitude;
        String strDestination = "destination=" + destination.latitude + "," + destination.longitude;
        String mode = "mode=driving";
        String params = strOrigin + "&" + strDestination + "&" + mode;
        String output = "json";
        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + params + "&key=AIzaSyBLLb7YhZ4vUvBwmz5Azg7CEVRxAVWdUN4";
    }

}

