package com.example.admitme.Funnel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.admitme.R;
import com.example.admitme.RIASEC.RiasecFrag;

public class FunnelPreferencesFrag extends Fragment {

    private RadioButton lessOne, oneToTwo, moreTwo;
    public static boolean isLessOne, isOneToTwo, isMoreTwo;
    private Button nextPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_funnel_preferences, container, false);

        setupUI(v);

        lessOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isLessOne = true;
                    isOneToTwo = false;
                    isMoreTwo = false;
                    oneToTwo.setChecked(isOneToTwo);
                    moreTwo.setChecked(isMoreTwo);
                }
            }
        });

        oneToTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isLessOne = false;
                    isOneToTwo = true;
                    isMoreTwo = false;
                    lessOne.setChecked(isLessOne);
                    moreTwo.setChecked(isMoreTwo);
                }
            }
        });

        moreTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isLessOne = false;
                    isOneToTwo = false;
                    isMoreTwo = true;
                    oneToTwo.setChecked(isLessOne);
                    lessOne.setChecked(isOneToTwo);
                }
            }
        });

        nextPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLessOne || isOneToTwo || isMoreTwo){
                    Toast toast = Toast.makeText(FunnelPreferencesFrag.this.getContext(), "Please make sure to select one of the above options", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    goToRIASEC();
                }
            }
        });

        return v;
    }

    private void goToRIASEC(){
        RiasecFrag riasec = new RiasecFrag();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.funnel_container, riasec);
        fragmentTransaction.commit();
    }

    private void setupUI(View v){
        lessOne = v.findViewById(R.id.less_year);
        oneToTwo = v.findViewById(R.id.one_two_year);
        moreTwo = v.findViewById(R.id.more_two_year);
        nextPref = v.findViewById(R.id.nextPref);
    }
}