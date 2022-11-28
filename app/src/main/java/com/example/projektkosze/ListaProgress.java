package com.example.projektkosze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class ListaProgress extends AppCompatActivity {
    TextView binProgress1, binProgress2, binProgress3, binProgress4, binProgress5, binProgress6;
    TextView percent1, percent2, percent3, percent4, percent5, percent6;
    private Bin[] myBin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_progress);

        Intent intent = getIntent();


        binProgress1 = findViewById(R.id.binProgress1);
        binProgress2 = findViewById(R.id.binProgress2);
        binProgress3 = findViewById(R.id.binProgress3);
        binProgress4 = findViewById(R.id.binProgress4);
        binProgress5 = findViewById(R.id.binProgress5);
        binProgress6 = findViewById(R.id.binProgress6);

        percent1 = findViewById(R.id.percent1);
        percent2 = findViewById(R.id.percent2);
        percent3 = findViewById(R.id.percent3);
        percent4 = findViewById(R.id.percent4);
        percent5 = findViewById(R.id.percent5);
        percent6 = findViewById(R.id.percent6);

        // Pobieranie danych z parent Activity
        int sizeArray = intent.getIntExtra("ARRAYLEN", 0);

        myBin = new Bin[sizeArray];

        for (int i = 0; i < sizeArray; i++){
            myBin[i] = intent.getParcelableExtra("BINLAT"+i);
        }

        binProgress1.setText(myBin[0].binName);
        if(myBin[0].binLevel >= 1 && myBin[0].binLevel < 25) {
            percent1.setTextColor(Color.GREEN);
        } else if(myBin[0].binLevel >= 25 && myBin[0].binLevel < 75) {
            percent1.setTextColor(Color.YELLOW);
        }  else if(myBin[0].binLevel >= 75) {
            percent1.setTextColor(Color.RED);
        }
        percent1.setText(myBin[0].binLevel + "%");

        binProgress2.setText(myBin[1].binName);
        if(myBin[1].binLevel >= 1 && myBin[1].binLevel < 25) {
            percent2.setTextColor(Color.GREEN);
        } else if(myBin[1].binLevel >= 25 && myBin[1].binLevel < 75) {
            percent2.setTextColor(Color.YELLOW);
        }  else if(myBin[1].binLevel >= 75) {
            percent2.setTextColor(Color.RED);
        }
        percent2.setText(myBin[1].binLevel + "%");

        binProgress3.setText(myBin[2].binName);
        if(myBin[2].binLevel >= 1 && myBin[2].binLevel < 25) {
            percent3.setTextColor(Color.GREEN);
        } else if(myBin[2].binLevel >= 25 && myBin[2].binLevel < 75) {
            percent3.setTextColor(Color.YELLOW);
        }  else if(myBin[2].binLevel >= 75) {
            percent3.setTextColor(Color.RED);
        }
        percent3.setText(myBin[2].binLevel + "%");

        binProgress4.setText(myBin[3].binName);
        if(myBin[3].binLevel >= 1 && myBin[3].binLevel < 25) {
            percent4.setTextColor(Color.GREEN);
        } else if(myBin[3].binLevel >= 25 && myBin[3].binLevel < 75) {
            percent4.setTextColor(Color.YELLOW);
        }  else if(myBin[3].binLevel >= 75) {
            percent4.setTextColor(Color.RED);
        }
        percent4.setText(myBin[3].binLevel + "%");

        binProgress5.setText(myBin[4].binName);
        if(myBin[4].binLevel >= 1 && myBin[4].binLevel < 25) {
            percent5.setTextColor(Color.GREEN);
        } else if(myBin[4].binLevel >= 25 && myBin[4].binLevel < 75) {
            percent5.setTextColor(Color.YELLOW);
        }  else if(myBin[4].binLevel >= 75) {
            percent5.setTextColor(Color.RED);
        }
        percent5.setText(myBin[4].binLevel + "%");

        binProgress6.setText(myBin[5].binName);
        if(myBin[5].binLevel >= 1 && myBin[5].binLevel < 25) {
            percent6.setTextColor(Color.GREEN);
        } else if(myBin[5].binLevel >= 25 && myBin[5].binLevel < 75) {
            percent6.setTextColor(Color.YELLOW);
        }  else if(myBin[5].binLevel >= 75) {
            percent6.setTextColor(Color.RED);
        }
        percent6.setText(myBin[5].binLevel + "%");



    }
}