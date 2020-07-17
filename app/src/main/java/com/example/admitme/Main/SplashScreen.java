package com.example.admitme.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.admitme.LoginRegister.MainPage;
import com.example.admitme.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen easySplashScreen = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainPage.class)
                .withSplashTimeOut(2000)
                .withBackgroundColor(Color.parseColor("#1DBCE5"))
                .withLogo(R.drawable.admitu_logo_placeholder);

        easySplashScreen.getLogo().setMaxWidth(600);
        easySplashScreen.getLogo().setMaxHeight(600);

        View easySplash = easySplashScreen.create();
        overridePendingTransition(0, R.anim.fade_out);
        setContentView(easySplash);


    }
}