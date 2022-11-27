package com.example.projektkosze;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {


    LatLng bin1 = new LatLng(50.013147305246505, 20.993092480594726);
    LatLng bin2 = new LatLng(50.02936749539654, 21.00135672555931);
    LatLng bin3 = new LatLng(50.02635484551118, 21.01277878159267);

    private RequestQueue mQueue;
    private List<LatLng> listOfBins;
    private List<String> listOfDistances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

       /* listOfBins = new ArrayList<>();
        listOfDistances = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);


        listOfBins.add(bin1);
        listOfBins.add(bin2);
        listOfBins.add(bin3);

        //String url = getRequestUrl(listOfBins.get(0), listOfBins.get(1));
//        TaskRequestDirection taskRequestDirection = new TaskRequestDirection();
//        taskRequestDirection.execute(url);

        for(int i = 0; i < listOfBins.size(); i++) {
            for(int j = i+1; j < listOfBins.size(); j++) {
                String url = getRequestUrl(listOfBins.get(i), listOfBins.get(j));
//                TaskRequestDirection taskRequestDirection = new TaskRequestDirection();
//                taskRequestDirection.execute(url);
                jsonParse(url);
                //listOfDistances.add(tv.getText().toString());
            }
        }




        if (!listOfDistances.isEmpty()) {
            Log.v("Lista", listOfDistances.get(0));
        } else {
            Log.v("Lista2", "nie ma");
        }
*/
    }

    private void jsonParse(String url) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray routes = response.getJSONArray("routes");
                JSONObject object = routes.getJSONObject(0);
                JSONArray legs = object.getJSONArray("legs");
                JSONObject legsObject = legs.getJSONObject(0);

                JSONObject distance = legsObject.getJSONObject("distance");
                String distanceString = distance.getString("value");
                listOfDistances.add(distanceString);


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

    private String requestDirection(String requestURL) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpCon = null;
        try {
            URL url = new URL(requestURL);
            httpCon = (HttpURLConnection) url.openConnection();
            httpCon.connect();

            inputStream = httpCon.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }

            responseString = buffer.toString();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (httpCon != null) {
                httpCon.disconnect();
            }
        }

        return responseString;

    }


    public class TaskRequestDirection extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String response = "";
            try {
                response = requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    public class TaskParser extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
            } catch (JSONException jsone) {
                jsone.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            ArrayList<LatLng> points;

            PolylineOptions options = null;

            for (List<HashMap<String, String>> path : lists) {
                points = new ArrayList<>();
                // options = new PolylineOptions();

                for (HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(Objects.requireNonNull(point.get("lat")));
                    double lon = Double.parseDouble(Objects.requireNonNull(point.get("lng")));

                    points.add(new LatLng(lat, lon));
                }
//                    options.addAll(points);
//                    options.width(10);
//                    options.color(Color.BLUE);
//                    options.geodesic(true);
            }

//                if (options != null) {
//                    mMap.addPolyline(options);
//                } else {
//                    Toast.makeText(getApplicationContext(), "Direction not found", Toast.LENGTH_SHORT).show();
//                }
        }
    }

}