package com.example.admitme.Funnel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.admitme.R;

public class MatchingCriteria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_criteria);

        WelcomeFrag welcomeFrag = new WelcomeFrag();
        //FunnelPreferencesFrag funnelPreferencesFrag = new FunnelPreferencesFrag();
        //UnisFrag unisFrag = new UnisFrag();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.funnel_container, welcomeFrag)
                .commit();
    }
}