package com.example.admitme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class Artistic extends Fragment {

    private CheckBox question1, question2, question3, question4, question5, question6, question7;
    private Button next, previous;
    public static int artisticCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_artistic, container, false);
        setupUI(v);


        question1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    artisticCount++;
                } else{
                    if(artisticCount > 0)
                        artisticCount--;
                }
            }
        });

        question2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    artisticCount++;
                } else{
                    if(artisticCount > 0)
                        artisticCount--;
                }
            }
        });

        question3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    artisticCount++;
                } else{
                    if(artisticCount > 0)
                        artisticCount--;
                }
            }
        });

        question4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    artisticCount++;
                } else{
                    if(artisticCount > 0)
                        artisticCount--;
                }
            }
        });

        question5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    artisticCount++;
                } else{
                    if(artisticCount > 0)
                        artisticCount--;
                }
            }
        });

        question6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    artisticCount++;
                } else{
                    if(artisticCount > 0)
                        artisticCount--;
                }
            }
        });

        question7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    artisticCount++;
                } else{
                    if(artisticCount > 0)
                        artisticCount--;
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question1.setSaveEnabled(true);
                question2.setSaveEnabled(true);
                question3.setSaveEnabled(true);
                question4.setSaveEnabled(true);
                question5.setSaveEnabled(true);
                question6.setSaveEnabled(true);
                question7.setSaveEnabled(true);
                System.out.println(artisticCount);
                goToSocial();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question1.setSaveEnabled(true);
                question2.setSaveEnabled(true);
                question3.setSaveEnabled(true);
                question4.setSaveEnabled(true);
                question5.setSaveEnabled(true);
                question6.setSaveEnabled(true);
                question7.setSaveEnabled(true);
                goToArtistic();
            }
        });

        return v;
    }

    private void setupUI(View v){
        question1 = v.findViewById(R.id.question1);
        question2 = v.findViewById(R.id.question2);
        question3 = v.findViewById(R.id.question3);
        question4 = v.findViewById(R.id.question4);
        question5 = v.findViewById(R.id.question5);
        question6 = v.findViewById(R.id.question6);
        question7 = v.findViewById(R.id.question7);
        next = v.findViewById(R.id.nextS);
        previous = v.findViewById(R.id.previousI);
    }

    private void goToSocial(){
        Social social = new Social();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.funnel_container, social);
        fragmentTransaction.commit();
    }

    private void goToArtistic(){
        Artistic artistic = new Artistic();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.funnel_container, artistic);
        fragmentTransaction.commit();
    }
}