package com.example.admitme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class UniversityInfoFrag extends Fragment {

    private ImageView mCloseFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_university_info, container, false);

        setUpUI(v);

        mCloseFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SearchFrag searchFrag = new SearchFrag();
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.app_container, searchFrag);
                fragmentTransaction.commit();

            }
        });

        return v;
    }

    public void setUpUI(View v){

        mCloseFrag = v.findViewById(R.id.exit_university_info_image);

    }

}