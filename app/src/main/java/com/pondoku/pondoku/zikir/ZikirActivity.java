package com.pondoku.pondoku.zikir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.pondoku.pondoku.HomeYeahh;
import com.pondoku.pondoku.R;
import com.pondoku.pondoku.qibla.QiblaActivity;

public class ZikirActivity extends AppCompatActivity {

    TextView dzikir;
    int zikir = 0;
    Vibrator vibe;
    MediaPlayer mediaPlayer;
    boolean playSound = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikir);
        dzikir = (TextView) findViewById(R.id.dzikir);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void subhanallah(View view) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tasbih);
        if (playSound==true) {
            mediaPlayer.start();
        }

        zikir = zikir + 1;
        tampil(zikir);

    }

    public void alhamdulillah(View view) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tahmid);
        if (playSound==true) {
            mediaPlayer.start();
        }
        zikir = zikir + 1;
        tampil(zikir);

    }

    public void lailahaillallah(View view) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tahlil);
        if (playSound==true) {
            mediaPlayer.start();
        }
        zikir = zikir + 1;
        tampil(zikir);

    }

    public void allahuakbar(View view) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.takbir);
        if (playSound==true) {
            mediaPlayer.start();
        }
        zikir = zikir + 1;
        tampil(zikir);

    }

    public void tampil(int z) {
        dzikir.setText("" + z);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(ZikirActivity.this, HomeYeahh.class));

    }

    public void btnPlaySound(View v) {
//        startActivity(new Intent(HomeYeahh.this, ZikirActivity.class));
//        finish();
        if (playSound == true) {
            playSound = false;
        }
        else {
            playSound = true;
        }
    }


}