package com.pondoku.pondoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pondoku.pondoku.login.LoginActivity;
import com.pondoku.pondoku.qibla.QiblaActivity;
import com.pondoku.pondoku.solat.SolatActivity;

public class HomeYeahh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_yeahh);
    }


    // to log in
    public void btnOneClick(View v) {
        startActivity(new Intent(HomeYeahh.this, QiblaActivity.class));
        finish();
    }

    public void btnTwoClick(View v) {
        startActivity(new Intent(HomeYeahh.this, SolatActivity.class));
        finish();
    }

    public void btnThreeClick(View v) {
        startActivity(new Intent(HomeYeahh.this, MainActivity.class));
        finish();
    }

}