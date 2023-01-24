package com.pondoku.pondoku.qibla;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.pondoku.pondoku.HomeYeahh;
import com.pondoku.pondoku.R;
import com.pondoku.pondoku.solat.SolatActivity;

public class QiblaActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView ic_compass;
    private static SensorManager sensorManager;
    private static Sensor sensor;
    private float currentDegree;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qibla);

        ic_compass = findViewById(R.id.ic);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        if (sensor!=null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
        else {
            Toast.makeText(getApplicationContext(), "not supported", Toast.LENGTH_SHORT).show();
        }

    }




    @Override
    public void onSensorChanged(SensorEvent event) {

        int degree = Math.round(event.values[0]);

        RotateAnimation animation = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        ic_compass.setAnimation(animation);
        currentDegree = -degree;
    }




    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(QiblaActivity.this, HomeYeahh.class));

    }

}