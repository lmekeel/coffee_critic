package com.example.coffeecritic;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();



        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
       ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#B19C8A"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);


           BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if(navHostFragment != null){
            NavController navController = navHostFragment.getNavController();

            AppBarConfiguration appBarConfig = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_favs).build();

            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
            NavigationUI.setupWithNavController(navView, navController);
        }





    }

}