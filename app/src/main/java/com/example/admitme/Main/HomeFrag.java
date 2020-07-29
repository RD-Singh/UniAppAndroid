// Creates the package for the app
package com.example.admitme.Main;

// Imports the methods needed in the java class
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.admitme.Funnel.UniversityItem;
import com.example.admitme.LoginRegister.LoginFrag;
import com.example.admitme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.bson.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;

//TODO change the university item and recyclerview so that they don't include program as displayed information,
// instead make it so that it stores all programs that they meet requirements for in an array and then have it display that array in the dialog box in the homefrag
//TODO make all the universities associated with the account display on home frag properly
//TODO change the hom_pop_up.xml file to actually support the multiple programs alongside their respective durations,
//  scrolling down the list and displaying university information
//TODO make the settings page so that it actually updates the account info in the database
//TODO make email confirmation and password reset
//TODO go through the whole funnel, UI and general experience with Dad and get feedback on what needs to change in order to decide what can be changed for the alpha

// Creates a class that is linked to the xml file
public class HomeFrag extends Fragment {

    private Dialog mHomeDialog;
    private LinearLayout mUniversityOne, mUniversityTwo, mUniversityThree;
    private TextView mUniversityOneName, mUniversityTwoName, mUniversityThreeName;
    private TextView mUniversityOneDescription, mUniversityTwoDescription, mUniversityThreeDescription;

    private MongoClient client;
    private MongoCollection<Document> acctCollection;

    public static ArrayList<UniversityItem> uniList = new ArrayList<>();

    // Contains all of the java code that will run
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflates the layout
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        mHomeDialog = new Dialog(HomeFrag.this.getContext());
        acctCollection = client.getDatabase("AdmitU").getCollection("Accounts");

        Task<Document> task = acctCollection.findOne(new Document("user_id", LoginFrag.app.currentUser().getId()));

        task.addOnCompleteListener(new OnCompleteListener<Document>() {
            @Override
            public void onComplete(@NonNull Task<Document> task) {
                if(task.isSuccessful()){
                    Document uni1 = (Document) task.getResult().get("University 1");

                    mUniversityOneName.setText(uni1.keySet().toString().replace("[", "").replace("]", ""));
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

                }
            }
        });

        // Calls the method to set up the user interface views
        setupUI(v);
        Set<UniversityItem> clearDupes = new HashSet<>(uniList);
        uniList.clear();
        uniList.addAll(clearDupes);

        System.out.println(uniList);

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