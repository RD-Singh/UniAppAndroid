package com.example.admitme;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsFrag extends Fragment {

    // Creates variables
    private Button signOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        setupUI(v);

        // Creates an onClickListener on the sign out button
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creates an intent that sets the users current page to the main fragment
                Intent goBackToMainFrag = new Intent(SettingsFrag.this.getContext(), MainPage.class);
                startActivity(goBackToMainFrag);
                // Signs out the user
                FirebaseAuth.getInstance().signOut();
            }
        });

        return v;
    }

    public void setupUI(View v){

        // Initializes the variables
        signOut = v.findViewById(R.id.signOut);

    }

}
