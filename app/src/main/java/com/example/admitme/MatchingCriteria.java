package com.example.admitme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;


import java.util.HashMap;

public class MatchingCriteria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_criteria);

        WelcomeFrag welcomeFrag = new WelcomeFrag();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.funnel_container, welcomeFrag)
                .commit();

    }
}