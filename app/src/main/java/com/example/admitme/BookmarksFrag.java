// Creates the package for the app
package com.example.admitme;

// Imports the methods needed in the java class
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

// Creates a class that is linked to the xml file
public class BookmarksFrag extends Fragment {

    // Creates variables
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private UniversityAdapter mAdapter;
    ArrayList<Universities> mBookmarkedUniversities;

    // Contains all of the java code that will run
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflates the layout
        View v = inflater.inflate(R.layout.fragment_bookmarks, container, false);

        createBookmarkedList();
        buildRecyclerView(v);

        // Returns the layout inflater
        return v;
    }

    public void buildRecyclerView(View v){

        mRecyclerView = v.findViewById(R.id.bookmark_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(BookmarksFrag.this.getContext());
        mAdapter = new UniversityAdapter(mBookmarkedUniversities);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void createBookmarkedList(){

        mBookmarkedUniversities = new ArrayList<>();

        mBookmarkedUniversities.add(new Universities("UBC", "Vancouver, B.C.", 46, R.drawable.ic_bookmark_not_filled));

    }


}