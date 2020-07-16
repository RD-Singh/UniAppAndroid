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
    public static int q1, q2, q3, q4, q5, q6, q7;
    private Button next, previous;
    public static int artisticCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_artistic, container, false);
        setupUI(v);

        checkToggle(q1, question1);
        checkToggle(q2, question2);
        checkToggle(q3, question3);
        checkToggle(q4, question4);
        checkToggle(q5, question5);
        checkToggle(q6, question6);
        checkToggle(q7, question7);

        question1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    artisticCount++;
                    q1 = 1;
                } else{
                    q1 = 0;
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
                    q2 = 1;
                } else{
                    q2 = 0;
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
                    q3 = 1;
                } else{
                    q3 = 0;
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
                    q4 = 1;
                } else{
                    q4 = 0;
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
                    q5 = 1;
                } else{
                    q5 = 0;
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
                    q6 = 1;
                } else{
                    q6 = 0;
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
                    q7 = 1;
                } else{
                    q7 = 0;
                    if(artisticCount > 0)
                        artisticCount--;
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Artistic: " +artisticCount);
                goToSocial();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInvestigative();
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

    private void checkToggle(int q, CheckBox checkBox){
        if(q == 1) {
            checkBox.toggle();
        }
    }

    private void goToInvestigative(){
        Investigative investigative = new Investigative();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.funnel_container, investigative);
        fragmentTransaction.commit();
    }

    private void goToSocial(){
        Social social = new Social();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.funnel_container, social);
        fragmentTransaction.commit();
    }
}