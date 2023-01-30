package com.pondoku.pondoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.pondoku.pondoku.alarm.AlarmActivity;
import com.pondoku.pondoku.login.LoginActivity;
import com.pondoku.pondoku.profile.ChangePasswordActivity;
import com.pondoku.pondoku.qibla.QiblaActivity;
import com.pondoku.pondoku.solat.SolatActivity;
import com.pondoku.pondoku.zikir.ZikirActivity;

public class HomeYeahh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_yeahh);
    }


    // to log in
    public void btnQiblaClick(View v) {
        startActivity(new Intent(HomeYeahh.this, QiblaActivity.class));
        finish();
    }

    public void btnSolatClick(View v) {
        startActivity(new Intent(HomeYeahh.this, SolatActivity.class));
        finish();
    }

    public void btnPondokuClick(View v) {
        startActivity(new Intent(HomeYeahh.this, MainActivity.class));
        finish();
    }

    public void btnZikirClick(View v) {
        startActivity(new Intent(HomeYeahh.this, ZikirActivity.class));
        finish();
    }

    public void btnAlarmClick(View v) {
        startActivity(new Intent(HomeYeahh.this, AlarmActivity.class));
        finish();
    }

    public void btnProfileClick(View view) {
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth.signOut();
        startActivity(new Intent(HomeYeahh.this, ChangePasswordActivity.class));
        finish();
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finishAndRemoveTask();
////        finishAffinity();
////        finish();
//    }

    public void onBackPressed() {
        //do nothing
    }

}