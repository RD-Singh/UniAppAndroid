// Creates the package for the app
package com.example.admitme;

// Imports the methods need in the java class
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

    // Creates variables
    private Button signOut;

    // Contains all of the java code that will run
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflates the layout
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Calls the method to set up the user interface views
        setupUI(v);

        // Creates an onClickListener on the sign out button
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creates an intent that sets the users current page to the main fragment
                Intent goBackToMainFrag = new Intent(HomeFrag.this.getContext(), MainPage.class);
                startActivity(goBackToMainFrag);
                // Signs out the user
                FirebaseAuth.getInstance().signOut();
            }
        });

        // Returns the layout inflater
        return v;
    }

    // A method that sets up all of the user interface views
    public void setupUI(View v){

        // Initializes the variables
        signOut = v.findViewById(R.id.signOut);

    }

}