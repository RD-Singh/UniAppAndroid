// Creates the package for the app
package com.example.admitme.Main;

// Imports the methods needed in the java class
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.admitme.Funnel.UniversityItem;
import com.example.admitme.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// Creates a class that is linked to the xml file
public class HomeFrag extends Fragment {

    private Dialog mHomeDialog;
    private LinearLayout mUniversityOne, mUniversityTwo, mUniversityThree;
    private TextView mUniversityOneName, mUniversityTwoName, mUniversityThreeName;
    private TextView mUniversityOneDescription, mUniversityTwoDescription, mUniversityThreeDescription;

    public static ArrayList<UniversityItem> uniList = new ArrayList<>();

    // Contains all of the java code that will run
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflates the layout
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Calls the method to set up the user interface views
        setupUI(v);
        Set<UniversityItem> clearDupes = new HashSet<>(uniList);
        uniList.clear();
        uniList.addAll(clearDupes);

        System.out.println(uniList);

        mHomeDialog = new Dialog(HomeFrag.this.getContext());

        mUniversityOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mHomeDialog.setContentView(R.layout.home_pop_up);
                ImageView closeUniversityDialog;
                TextView universityName;
                TextView universityDescription;
                closeUniversityDialog = mHomeDialog.findViewById(R.id.close_pop_up_home);
                universityName = mHomeDialog.findViewById(R.id.university_name_txt_home);
                universityDescription = mHomeDialog.findViewById(R.id.university_description_txt_home);
                universityName.setText(mUniversityOneName.getText().toString());
                universityDescription.setText(mUniversityOneDescription.getText().toString());
                closeUniversityDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mHomeDialog.dismiss();

                    }
                });

                mHomeDialog.show();

            }
        });

        mUniversityTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mHomeDialog.setContentView(R.layout.home_pop_up);
                ImageView closeUniversityDialog;
                TextView universityName;
                TextView universityDescription;
                closeUniversityDialog = mHomeDialog.findViewById(R.id.close_pop_up_home);
                universityName = mHomeDialog.findViewById(R.id.university_name_txt_home);
                universityDescription = mHomeDialog.findViewById(R.id.university_description_txt_home);
                universityName.setText(mUniversityTwoName.getText().toString());
                universityDescription.setText(mUniversityTwoDescription.getText().toString());
                closeUniversityDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mHomeDialog.dismiss();

                    }
                });

                mHomeDialog.show();

            }
        });

        mUniversityThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mHomeDialog.setContentView(R.layout.home_pop_up);
                ImageView closeUniversityDialog;
                TextView universityName;
                TextView universityDescription;
                closeUniversityDialog = mHomeDialog.findViewById(R.id.close_pop_up_home);
                universityName = mHomeDialog.findViewById(R.id.university_name_txt_home);
                universityDescription = mHomeDialog.findViewById(R.id.university_description_txt_home);
                universityName.setText(mUniversityThreeName.getText().toString());
                universityDescription.setText(mUniversityThreeDescription.getText().toString());
                closeUniversityDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mHomeDialog.dismiss();

                    }
                });

                mHomeDialog.show();

            }
        });

        // Returns the layout inflater
        return v;
    }

    // A method that sets up all of the user interface views
    public void setupUI(View v){

        mUniversityOne = v.findViewById(R.id.university_one);
        mUniversityOneName = v.findViewById(R.id.university_one_name);
        mUniversityOneDescription = v.findViewById(R.id.university_one_description);

        mUniversityTwo = v.findViewById(R.id.university_two);
        mUniversityTwoName = v.findViewById(R.id.university_two_name);
        mUniversityTwoDescription = v.findViewById(R.id.university_two_description);

        mUniversityThree = v.findViewById(R.id.university_three);
        mUniversityThreeName = v.findViewById(R.id.university_three_name);
        mUniversityThreeDescription = v.findViewById(R.id.university_three_description);

    }

}