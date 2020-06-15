// Creates the package for the app
package com.example.admitme;

// Imports the methods need in the java class
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Creates a class that is linked to the xml file
public class BookmarksFrag extends Fragment {

    // Contains all of the java code that will run
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflates the layout
        View v = inflater.inflate(R.layout.fragment_bookmarks, container, false);

        // Returns the layout inflater
        return v;
    }
}