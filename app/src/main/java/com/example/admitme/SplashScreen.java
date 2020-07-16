package com.example.admitme;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen easySplashScreen = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainPage.class)
                .withSplashTimeOut(2500)
                .withBackgroundColor(Color.parseColor("#1DBCE5"))
                .withLogo(R.drawable.admitu_logo_placeholder);

        easySplashScreen.getLogo().setMaxWidth(600);
        easySplashScreen.getLogo().setMaxHeight(600);

        View easySplash = easySplashScreen.create();
        setContentView(easySplash);


    }
}