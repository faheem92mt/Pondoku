package com.pondoku.pondoku.solat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pondoku.pondoku.HomeYeahh;
import com.pondoku.pondoku.MainActivity;
import com.pondoku.pondoku.R;
import com.pondoku.pondoku.databinding.ActivitySolatBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class SolatActivity extends AppCompatActivity {

    ActivitySolatBinding binding;
    private SolatAdapter solatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySolatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        solatAdapter = new SolatAdapter(this);
        binding.solatRecycler.setAdapter(solatAdapter);
        binding.solatRecycler.setLayoutManager(new LinearLayoutManager(this));

        loadData();
        scrollToPosition();

    }

    private void loadData() {
        long milis = Calendar.getInstance().getTimeInMillis();
        //change
        String url = "https://www.e-solat.gov.my/index.php?r=esolatApi/TakwimSolat&period=month&zone=SGR01";

//        Toast.makeText(MainActivity.this, "Hmmmm" , Toast.LENGTH_SHORT).show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
//                Toast.makeText(MainActivity.this, "Hmmmm" , Toast.LENGTH_SHORT).show();
                try {

//                    Toast.makeText(MainActivity.this, "Hmmmm" , Toast.LENGTH_SHORT).show();

                    solatAdapter.clear();
                    String status = response.getString("status");
                    if (status.equals("OK!")) {


                        JSONArray jsonArray = response.getJSONArray("prayerTime");

                        for (int i=0; i<jsonArray.length(); i++) {
                            JSONObject prayerTime = jsonArray.getJSONObject(i);
//                           // //JSONObject dateObject = dataArray.getJSONObject(i).getJSONObject("date");

                            String imsak = prayerTime.getString("imsak");
                            String subuh = prayerTime.getString("fajr");
                            String syuruk =  prayerTime.getString("syuruk");

                            String zuhur = prayerTime.getString("dhuhr");
                            String asar = prayerTime.getString("asr");
                            String maghrib = prayerTime.getString("maghrib");
                            String isyak = prayerTime.getString("isha");
//
                            String date = prayerTime.getString("date");

                            SolatModel solatModel = new SolatModel(imsak,subuh,syuruk,zuhur,asar,maghrib,isyak,date);

                            solatAdapter.add(solatModel);


                        }

                    }

                } catch (JSONException e) {
                    Toast.makeText(SolatActivity.this, "Front End Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SolatActivity.this, "General Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);


    }

    public static String convertDate(long dataInMilliseconds, String dateFormat) {
        String myDateString = DateFormat.format(dateFormat, dataInMilliseconds).toString();
        return myDateString;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(SolatActivity.this, HomeYeahh.class));

    }

    public void scrollToPosition() {
//        int position = namazAdapter.getItemPosition("24-Jan-2023");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(day+2);
        Log.d("Hello", String.valueOf(day));
        binding.solatRecycler.scrollToPosition(day-1);
    }

}