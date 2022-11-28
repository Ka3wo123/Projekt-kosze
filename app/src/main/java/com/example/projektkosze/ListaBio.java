package com.example.projektkosze;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;

import android.view.View;

import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ListaBio extends AppCompatActivity implements View.OnClickListener {

    private AlertDialog.Builder alertBuilder;
    private AlertDialog dialog;


    private Bin[] myBin;

    // private TextView binInfoArray[][];
    final private int dataCountInRow = 7;
    private TextView binInfoArray[];
    private RequestQueue mQueue;

    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bio);

        mQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        binInfoArray = new TextView[] {
                    findViewById(R.id.bioTitle1),
                    findViewById(R.id.bin1D1),
                    findViewById(R.id.bin1D2),
                    findViewById(R.id.bin1D3),
                    findViewById(R.id.bin1D4),
                    findViewById(R.id.bin1D5),
                    findViewById(R.id.bin1D6)
        };

        // Pobieranie danych z parent Activity
        int sizeArray = intent.getIntExtra("ARRAYLEN", 0);


        // binInfoArray = new TextView[sizeArray][dataCountInRow];

/*        for (int i = 0; i < sizeArray; i++){
            for (int j = 0; j < dataCountInRow; j++){
                binInfoArray[i][j] = findViewById(R.id.t)
            }
        }*/

        myBin = new Bin[sizeArray];

        for (int i = 0; i < sizeArray; i++) {
            myBin[i] = intent.getParcelableExtra("BINLAT" + i);
        }
/*
        alertBuilder = new AlertDialog.Builder(this);*/

/*        for (int i = 0; i < binInfoArray.length; i++) {
            binInfoArray[i].setText(String.valueOf(myBin[i].binName));
            binInfoArray[i].setOnClickListener(this);
        }*/

        // ONE BIN ONYL RIGHT NOW

        // Set title
        binInfoArray[0].setText(String.valueOf(myBin[0].binName));

        TextView textView[] = new TextView[]{findViewById(R.id.bioTitle2), findViewById(R.id.bioTitle3),
        findViewById(R.id.bioTitle4), findViewById(R.id.bioTitle5), findViewById(R.id.bioTitle6)};

        for (int i = 0; i < textView.length; i++){
            textView[i].setText(String.valueOf(myBin[i+1].binName));
        }


        JsonObjectRequest requestWheater = new JsonObjectRequest(Request.Method.GET, "https://api.thingspeak.com/channels/1951230/feeds/last.json", null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.v("VALLRQGOOD", "onResponse");
                    try {
                        binInfoArray[1].setText("T: " + response.getString("field1"));

                        int tmp = (int)Double.parseDouble(response.getString("field2"));
                        tmp /= 100;

                        binInfoArray[2].setText("HPa: " + String.valueOf(tmp));
                        binInfoArray[3].setText("H.: " + response.getString("field3") + "%");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("VALLRQ","ERROR VALLEY " + error.getMessage());
                }
            });
        mQueue.add(requestWheater);
}

    @Override
    public void onClick(View view) {



        /*View popupView = getLayoutInflater().inflate(R.layout.popup, null);
        alertBuilder.setView(popupView);
        dialog = alertBuilder.create();
        dialog.show();
        Button viewById = (Button) popupView.findViewById(R.id.OKbutton);
        viewById.setOnClickListener(a -> {
            dialog.dismiss();

        });*/

    }

}



