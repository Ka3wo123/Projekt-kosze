package com.example.projektkosze;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import android.widget.ListView;

import com.android.volley.RequestQueue;

import com.android.volley.toolbox.JsonObjectRequest;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListaBio extends AppCompatActivity {


    LatLng bin1 = new LatLng(50.013147305246505, 20.993092480594726);
    LatLng bin2 = new LatLng(50.02936749539654, 21.00135672555931);
    LatLng bin3 = new LatLng(50.02635484551118, 21.01277878159267);

    private List<LatLng> listOfBins;
    private List<String> listOfDistances;
    
    private AlertDialog.Builder alertBuilder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bio);

        Button b = findViewById(R.id.button10);

        b.setOnClickListener(v -> {
            alertBuilder = new AlertDialog.Builder(this);
            final View popupView = getLayoutInflater().inflate(R.layout.popup, null);

            alertBuilder.setView(popupView);
            dialog = alertBuilder.create();
            dialog.show();
            Button viewById = (Button )popupView.findViewById(R.id.OKbutton);
            viewById.setOnClickListener(a -> {
                dialog.dismiss();
            });


        });



    }


}