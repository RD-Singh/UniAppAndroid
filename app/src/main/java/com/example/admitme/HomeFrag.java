// Creates the package for the app
package com.example.admitme;

// Imports the methods needed in the java class
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

// Creates a class that is linked to the xml file
public class HomeFrag extends Fragment {

    // Contains all of the java code that will run
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflates the layout
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Calls the method to set up the user interface views
        setupUI(v);

        // Returns the layout inflater
        return v;
    }

    // A method that sets up all of the user interface views
    public void setupUI(View v){

    }

}