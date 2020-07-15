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

public class Conventional extends Fragment {

    private CheckBox question1, question2, question3, question4, question5, question6, question7;
    private Button previous;
    public static int conventionalCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_conventional, container, false);
        setupUI(v);


        question1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    conventionalCount++;
                } else{
                    if(conventionalCount > 0)
                        conventionalCount--;
                }
            }
        });

        question2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    conventionalCount++;
                } else{
                    if(conventionalCount > 0)
                        conventionalCount--;
                }
            }
        });

        question3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    conventionalCount++;
                } else{
                    if(conventionalCount > 0)
                        conventionalCount--;
                }
            }
        });

        question4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    conventionalCount++;
                } else{
                    if(conventionalCount > 0)
                        conventionalCount--;
                }
            }
        });

        question5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    conventionalCount++;
                } else{
                    if(conventionalCount > 0)
                        conventionalCount--;
                }
            }
        });

        question6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    conventionalCount++;
                } else{
                    if(conventionalCount > 0)
                        conventionalCount--;
                }
            }
        });

        question7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    conventionalCount++;
                } else{
                    if(conventionalCount > 0)
                        conventionalCount--;
                }
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
                goToEnterprising();
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
        previous = v.findViewById(R.id.previousE);
    }

    private void goToEnterprising(){
        Enterprising enterprising = new Enterprising();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.funnel_container, enterprising);
        fragmentTransaction.commit();
    }
}