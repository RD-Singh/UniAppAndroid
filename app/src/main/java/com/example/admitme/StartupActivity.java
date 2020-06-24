package com.example.admitme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class StartupActivity extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        sessionManager = new SessionManager(this);
        sessionManager.loginStatus();

        HashMap<String, String> user = sessionManager.userDetails();

        System.out.println(user.get(sessionManager.USER_NAME));
        System.out.println(user.get(sessionManager.EMAIL));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.app_container, new HomeFrag()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.home:
                    selectedFragment = new HomeFrag();
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
