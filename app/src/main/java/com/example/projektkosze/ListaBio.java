package com.example.projektkosze;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.view.View;

import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;


public class ListaBio extends AppCompatActivity {


    LatLng bin1 = new LatLng(50.013147305246505, 20.993092480594726);
    LatLng bin2 = new LatLng(50.02936749539654, 21.00135672555931);
    LatLng bin3 = new LatLng(50.02635484551118, 21.01277878159267);

    private AlertDialog.Builder alertBuilder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bio);

        final List<LatLng> listOfBins = new ArrayList<>();
        listOfBins.add(bin1);
        listOfBins.add(bin2);
        listOfBins.add(bin3);

        ListView listView = (ListView) findViewById(R.id.listViewBins);

        alertBuilder = new AlertDialog.Builder(this);

        ArrayAdapter<LatLng> adapter = new ArrayAdapter<>(this, R.layout.list_bins, listOfBins);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            View popupView = getLayoutInflater().inflate(R.layout.popup, null);
            alertBuilder.setView(popupView);
            dialog = alertBuilder.create();
            dialog.show();
            Button viewById = (Button) popupView.findViewById(R.id.OKbutton);
            viewById.setOnClickListener(a -> {
                dialog.dismiss();
            });
        });

    }
}


