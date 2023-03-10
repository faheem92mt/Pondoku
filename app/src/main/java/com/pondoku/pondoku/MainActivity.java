package com.pondoku.pondoku;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.pondoku.pondoku.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pondoku.pondoku.databinding.ActivityMainBinding;

/**
 * MainActivity of the program that binds to the navigation view.
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // binds all of the fragments in the navigation bar and all the other fragments
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_settings, R.id.navigation_following, R.id.navigation_add_habit, R.id.navigation_sharing)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//        startActivity(new Intent(MainActivity.this, HomeYeahh.class));
//
//    }

//    public void onBackPressed() {
//        //do nothing
//    }

}