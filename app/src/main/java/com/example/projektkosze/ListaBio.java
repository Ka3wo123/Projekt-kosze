package com.example.projektkosze;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import android.view.View;

import android.widget.TextView;

public class ListaBio extends AppCompatActivity {

    private AlertDialog.Builder alertBuilder;
    private AlertDialog dialog;

    private Bin[] myBin;

    TextView binInf1, binInf2, binInf3, binInf4, binInf5, binInf6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bio);

        Intent intent = getIntent();

        binInf1 = findViewById(R.id.binInfo1);
        binInf2 = findViewById(R.id.binInfo2);
        binInf3 = findViewById(R.id.binInfo3);
        binInf4 = findViewById(R.id.binInfo4);
        binInf5 = findViewById(R.id.binInfo5);
        binInf6 = findViewById(R.id.binInfo6);


        // Pobieranie danych z parent Activity
        int sizeArray = intent.getIntExtra("ARRAYLEN", 0);

        myBin = new Bin[sizeArray];

        for (int i = 0; i < sizeArray; i++){
            myBin[i] = intent.getParcelableExtra("BINLAT"+i);
        }

        alertBuilder = new AlertDialog.Builder(this);

        binInf1.setText(String.valueOf(myBin[0].binName));
        binInf1.setOnClickListener(v -> {
            View popupView = getLayoutInflater().inflate(R.layout.popup, null);
            alertBuilder.setView(popupView);
            dialog = alertBuilder.create();
            dialog.show();
            Button viewById = (Button) popupView.findViewById(R.id.OKbutton);
            viewById.setOnClickListener(a -> {
                dialog.dismiss();
            });
        });

        binInf2.setText(String.valueOf(myBin[1].binName));
        binInf2.setOnClickListener(v -> {
            View popupView = getLayoutInflater().inflate(R.layout.popup, null);
            alertBuilder.setView(popupView);
            dialog = alertBuilder.create();
            dialog.show();
            Button viewById = (Button) popupView.findViewById(R.id.OKbutton);
            viewById.setOnClickListener(a -> {
                dialog.dismiss();
            });
        });

        binInf3.setText(String.valueOf(myBin[2].binName));
        binInf3.setOnClickListener(v -> {
            View popupView = getLayoutInflater().inflate(R.layout.popup, null);
            alertBuilder.setView(popupView);
            dialog = alertBuilder.create();
            dialog.show();
            Button viewById = (Button) popupView.findViewById(R.id.OKbutton);
            viewById.setOnClickListener(a -> {
                dialog.dismiss();
            });
        });

        binInf4.setText(String.valueOf(myBin[3].binName));
        binInf4.setOnClickListener(v -> {
            View popupView = getLayoutInflater().inflate(R.layout.popup, null);
            alertBuilder.setView(popupView);
            dialog = alertBuilder.create();
            dialog.show();
            Button viewById = (Button) popupView.findViewById(R.id.OKbutton);
            viewById.setOnClickListener(a -> {
                dialog.dismiss();
            });
        });

        binInf5.setText(String.valueOf(myBin[4].binName));
        binInf5.setOnClickListener(v -> {
            View popupView = getLayoutInflater().inflate(R.layout.popup, null);
            alertBuilder.setView(popupView);
            dialog = alertBuilder.create();
            dialog.show();
            Button viewById = (Button) popupView.findViewById(R.id.OKbutton);
            viewById.setOnClickListener(a -> {
                dialog.dismiss();
            });
        });

        binInf6.setText(String.valueOf(myBin[5].binName));
        binInf6.setOnClickListener(v -> {
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


