package com.example.projektkosze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ListaProgress extends AppCompatActivity implements View.OnClickListener {
    private TextView binProgress1, binProgress2, binProgress3, binProgress4, binProgress5, binProgress6;
    private TextView percent1, percent2, percent3, percent4, percent5, percent6;
    private Bin[] myBin;

    private RequestQueue mQueue;

    final private int MAXBINLEVEL = 700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_progress);

        Intent intent = getIntent();

        mQueue = Volley.newRequestQueue(this);

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

        // Read JSom from URL
        getBinLevelAPI();

        binProgress1.setText(myBin[0].binName);
        if(myBin[0].binLevel >= 1 && myBin[0].binLevel < 25) {
            percent1.setTextColor(Color.GREEN);
        } else if(myBin[0].binLevel >= 25 && myBin[0].binLevel < 75) {
            percent1.setTextColor(Color.YELLOW);
        }  else if(myBin[0].binLevel >= 75) {
            percent1.setTextColor(Color.RED);
        }

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

        Button button = findViewById(R.id.refreshButton);
        button.setOnClickListener(this);

    }

    void getBinLevelAPI(){
        JsonObjectRequest requestDistance = new JsonObjectRequest(Request.Method.GET, "https://api.thingspeak.com/channels/1951230/feeds/last.json", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("VALLRQGOOD", "onResponse");
                        try {
                            int distBin = response.getInt("field4");

                            if (distBin >= MAXBINLEVEL){
                                percent1.setText("0%");
                                myBin[0].binLevel = 0;
                            }
                            else{
                                int percent = (int)((1 - distBin / (double)MAXBINLEVEL) * 100);
                                myBin[0].binLevel = percent;
                                percent1.setText((String.valueOf(percent) + "%"));
                            }

                            if(myBin[0].binLevel >= 1 && myBin[0].binLevel < 25) {
                                percent1.setTextColor(Color.GREEN);
                            } else if(myBin[0].binLevel >= 25 && myBin[0].binLevel < 75) {
                                percent1.setTextColor(Color.YELLOW);
                            }  else if(myBin[0].binLevel >= 75) {
                                percent1.setTextColor(Color.RED);
                            }

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
        mQueue.add(requestDistance);
    }

    @Override
    public void onClick(View view) {
        // Update data from ThingSpeak
        getBinLevelAPI();
    }
}