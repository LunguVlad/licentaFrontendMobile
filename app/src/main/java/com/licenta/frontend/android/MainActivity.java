package com.licenta.frontend.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = this.getIntent().getParcelableExtra("user");
        System.out.println("USER MAIN!!::  " + user);
    }
}