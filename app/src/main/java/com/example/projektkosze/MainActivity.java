package com.example.projektkosze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void goToListaBio(View view){
        Intent intent = new Intent(this, ListaBio.class);
        startActivity(intent);
    }
    public void goToListaProgress(View view){
        Intent intent = new Intent(this, ListaProgress.class);
        startActivity(intent);
    }
    public void goToMapActivity(View view){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

}