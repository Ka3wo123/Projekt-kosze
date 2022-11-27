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

    private ListView listView;
    private AlertDialog.Builder alertBuilder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_bio);

        listView = findViewById(R.id.listViewOfBins);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            alertBuilder = new AlertDialog.Builder(ListaBio.this);
            final View popup = getLayoutInflater().inflate(R.layout.popup, null);
            alertBuilder.setView(popup);
            dialog = alertBuilder.create();
            dialog.show();
        }


        );


    }


}