package com.example.myappga;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Home1 home1 = new Home1();
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.container, Home1.class,null).commit();
        supportFinishAfterTransition();
    }
}