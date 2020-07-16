// Creates the package for the app
package com.example.admitme;

// Imports the methods needed in the java class
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import org.bson.Document;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;

public class LoginFrag extends Fragment {

    //Creates variables
    private Button homePageBTN;
    private TextInputLayout email, password;
    private String emailStr, passwordStr;
    private ProgressBar progressBar;
    private static final String APP_ID = "admitu-dtcgi";
    public static App app = new App(new AppConfiguration.Builder(APP_ID).build());
    private TextView forgetPassword, signUpHere;
    private MongoClient client;
    private MongoCollection<Document> accountsColl;
    public Accounts account = new Accounts();

    private Credentials credentials;

    private boolean emptyCheck(String text) {
        if (text.isEmpty()) {
            return true;
        }

        return false;
    }

    private void isEmpty(TextInputLayout textInputLayout) {
        textInputLayout.setError("Please make sure that the field is not empty.");
    }

    private boolean validateEmail() {
        if (emptyCheck(emailStr)) {
            isEmpty(email);
            return true;
        } else {
            return false;
        }
    }

    private boolean validatePassword() {
        if (emptyCheck(passwordStr)) {
            isEmpty(password);
            return true;
        } else {
            return false;
        }
    }


    // Contains all of the java code that will run
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflates the layout
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        setupUI(v);

        homePageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupStr();
                if (validatePassword() | validateEmail()) {
                } else {
                    login();
                }
            }
        });

        signUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signUpHere();

            }
        });

        return v;
    }

    private void login() {
        progressBar.setVisibility(View.VISIBLE);
        credentials = Credentials.emailPassword(emailStr, passwordStr);
        app.loginAsync(credentials, new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {
                if(result.isSuccess()){
                    Toast.makeText(LoginFrag.this.getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    client = app.currentUser().getMongoClient("mongodb-atlas");
                    accountsColl = client.getDatabase("AdmitU").getCollection("Accounts");

                    Task<Document> itemsTask = accountsColl.findOne(new Document("email", emailStr).append("password", passwordStr));

                    itemsTask.addOnCompleteListener(new OnCompleteListener<Document>() {
                        @Override
                        public void onComplete(@NonNull Task<Document> task) {
                            if(task.isSuccessful()){
                                Document doc = task.getResult();

                                //account.set_id(doc.getString("user_id"));
                                account.setEmail(doc.getString("email"));
                                account.setFullName(doc.getString("full_name"));
                                account.setPassword(doc.getString("password"));
                                account.setFirstTimeLogin(doc.getInteger("first_time_login"));

                                if(doc.getInteger("first_time_login") == 0){
                                    goToMatchingCriteria();
                                } else{
                                    goToHomePage();
                                }
                            }
                        }
                    });

                } else{
                    Toast.makeText(LoginFrag.this.getContext(), "Login Failed " + result.getError().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goToHomePage(){
        Intent gotoHomePage = new Intent(LoginFrag.this.getContext(), StartupActivity.class);
        startActivity(gotoHomePage);
        getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    public void goToMatchingCriteria(){
        Intent goToMC = new Intent(LoginFrag.this.getContext(), MatchingCriteria.class);
        startActivity(goToMC);
        getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    public void signUpHere(){

        SignUp signUp = new SignUp();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.fragment_container, signUp);
        fragmentTransaction.commit();

    }

    private void setupStr() {
        emailStr = email.getEditText().getText().toString().trim();
        passwordStr = password.getEditText().getText().toString().trim();
    }

    private void setupUI(View v) {
        homePageBTN = v.findViewById(R.id.login);
        email = v.findViewById(R.id.email);
        password = v.findViewById(R.id.password);
        progressBar = v.findViewById(R.id.progressBar);
        forgetPassword = v.findViewById(R.id.forgetPassword);
        signUpHere = v.findViewById(R.id.sign_up_here);
    }

}
