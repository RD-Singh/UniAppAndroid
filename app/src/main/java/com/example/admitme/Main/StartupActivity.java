package com.example.admitme.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.admitme.Funnel.FunnelFrag;
import com.example.admitme.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.app_container, new HomeFrag()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.home_page:
                    selectedFragment = new HomeFrag();
                    break;
                case R.id.matching_criteria:
                    selectedFragment = new FunnelFrag();
                    break;
                case R.id.settings:
                    selectedFragment = new SettingsFrag();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.app_container, selectedFragment).commit();

            return true;
        }

    };
}
