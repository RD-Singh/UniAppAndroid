package com.example.admitme.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

import com.example.admitme.R;

import io.realm.Realm;


public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        overridePendingTransition(R.anim.fade_in,0);

        Realm.init(this);

        MainFrag mainFrag = new MainFrag();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, mainFrag)
                .commit();
    }


}
