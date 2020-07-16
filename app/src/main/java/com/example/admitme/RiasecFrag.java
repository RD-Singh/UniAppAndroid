package com.example.admitme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RiasecFrag extends Fragment {

    public static ArrayList<Integer> riasec = new ArrayList<>();
    public static int first, second, third;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_riasec, container, false);

        setupUI(v);

        riasec.add(0, Realistic.realisticCount);
        riasec.add(1, Investigative.investigateCount);
        riasec.add(2, Artistic.artisticCount);
        riasec.add(3, Social.socialCount);
        riasec.add(4, Enterprising.enterprisingCount);
        riasec.add(5, Conventional.conventionalCount);

        Comparator c = Collections.reverseOrder();
        Collections.sort(riasec, c);

        first = riasec.get(0);
        second = riasec.get(1);
        third = riasec.get(2);

        return v;
    }

    private void setupUI(View v){

    }
}