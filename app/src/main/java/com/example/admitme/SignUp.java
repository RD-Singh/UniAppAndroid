package com.example.admitme;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
//import com.mongodb.client.model.Projections;
//import com.mongodb.stitch.android.core.Stitch;
//import com.mongodb.stitch.android.core.StitchAppClient;
//import com.mongodb.stitch.android.core.auth.StitchUser;
//import com.mongodb.stitch.android.core.auth.providers.userpassword.UserPasswordAuthProviderClient;
//import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
//import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
//import com.mongodb.stitch.core.auth.StitchCredential;
//import com.mongodb.stitch.core.auth.providers.userpassword.UserPasswordCredential;
//import com.mongodb.stitch.core.services.mongodb.remote.RemoteInsertOneResult;
//import com.mongodb.stitch.core.services.mongodb.remote.sync.DefaultSyncConflictResolvers;
//import com.mongodb.stitch.core.services.mongodb.remote.sync.internal.SyncConfiguration;

import org.bson.Document;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.result.InsertOneResult;

public class SignUp extends Fragment {

    private TextInputLayout fullname, username, email, password, confirmPassword;
    private String fullnameStr, usernameStr, emailStr, passwordStr, confirmPasswordStr;
    private ProgressBar progressBar;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" + "(?=.*[0-9])" + "(?=.*[a-zA-Z])" + "(?=.*[@#$%^&+=*])" + "(?=\\S+$)" + ".{4,}" + "$");

    private Button gotoLogin;
    private TextView loginHere;

    private MongoClient client;
    LoginFrag loginFrag = new LoginFrag();

    private MongoCollection<Document> mongoCollection;


    private final static int MIN_LENGTH = 4;
    private final static int MAX_LENGTH = 16;

    private static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private boolean sizeOfText(String text) {

        return text.length() < MIN_LENGTH || text.length() > MAX_LENGTH;
    }

    private boolean emptyCheck(String text) {
        return text.isEmpty();
    }

    private void isEmpty(TextInputLayout textInputLayout) {
        textInputLayout.setError("Please make sure that the field is not empty.");
    }

    private void noError(TextInputLayout textInputLayout) {
        textInputLayout.setError("");
    }

    private boolean validateFullname() {
        if (emptyCheck(fullnameStr)) {
            isEmpty(fullname);
            return true;
        } else if (sizeOfText(fullnameStr)) {
            fullname.setError("Please make sure your name is between 4 and 16 characters long.");
            return true;
        } else {
            noError(fullname);
            return false;
        }
    }

    private boolean validateUsername() {
        if (emptyCheck(usernameStr)) {
            isEmpty(username);
            return true;
        } else {
            noError(username);
            return false;
        }
    }

    private boolean validateEmail() {
        if (emptyCheck(emailStr)) {
            isEmpty(email);
            return true;
        } else if (!isValidEmail(emailStr)) {
            email.setError("Please make sure to use a valid email.");
            return true;
        } else {
            noError(email);
            return false;
        }
    }

    private boolean validatePassword() {
        if (emptyCheck(passwordStr)) {
            isEmpty(password);
            return true;
        } else if (passwordCheck(passwordStr)) {
            password.setError("Your Password is too weak. Please include at least one special character and one number.");
            return true;
        } else if (passwordStr.length() < 6) {
            password.setError(("Your password is too weak. Please make sure it is longer than 6 characters."));
            return true;
        } else {
            noError(password);
            return false;
        }

    }

    private boolean validateConfirmPassword() {
        if (emptyCheck(confirmPasswordStr)) {
            isEmpty(confirmPassword);
            return true;
        } else if (!confirmPasswordStr.equals(passwordStr)) {
            confirmPassword.setError("You're passwords do not match.");
            return true;
        } else {
            noError(confirmPassword);
            return false;
        }
    }

    private boolean passwordCheck(String password) {
        return !PASSWORD_PATTERN.matcher(password).matches();
    }

    private void gotoLogin() {
        SignUp signUp = new SignUp();
        LoginFrag loginFrag = new LoginFrag();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction().remove(signUp);
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment_container, loginFrag);
        fragmentTransaction.commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);

        setupUI(v);


        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupStr();

                if (validateFullname() | validateUsername() | validateEmail() | validatePassword() | validateConfirmPassword()) {

                } else {
                    addAccount();
                }
            }
        });

        loginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLogin();
            }
        });

        return v;
    }

    private void addAccount() {

        progressBar.setVisibility(View.VISIBLE);

        LoginFrag.app.getEmailPasswordAuth().registerUserAsync(emailStr, passwordStr, new App.Callback<Void>() {
            @Override
            public void onResult(App.Result<Void> result) {
                if(result.isSuccess()){

                    Credentials credentials = Credentials.emailPassword(emailStr, passwordStr);

                    LoginFrag.app.loginAsync(credentials, new App.Callback<User>() {
                        @Override
                        public void onResult(App.Result<User> result) {
                            if(result.isSuccess()){

                                client = LoginFrag.app.currentUser().getMongoClient("mongodb-atlas");
                                mongoCollection = client.getDatabase("AdmitU").getCollection("Accounts");

                                Document temp = new Document("user_id", LoginFrag.app.currentUser().getId());
                                temp.append("email", emailStr);
                                temp.append("password", passwordStr);
                                temp.append("full_name", fullnameStr);
                                temp.append("first_time_login", 0);

                                mongoCollection.insertOne(temp).addOnSuccessListener(new OnSuccessListener<InsertOneResult>() {
                                    @Override
                                    public void onSuccess(InsertOneResult insertOneResult) {
                                        System.out.println("Success");
                                        gotoLogin();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        System.out.println("failed");
                                    }
                                });
                            } else{
                                Toast.makeText(SignUp.this.getContext(), "Sign Up failed " + result.getError().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                System.out.println(result.getError().getErrorMessage());
                            }
                        }
                    });

                    LoginFrag.app.currentUser().logOutAsync(new App.Callback<User>() {
                        @Override
                        public void onResult(App.Result<User> result) {

                        }
                    });
                }
            }
        });

    }

    private void setupStr() {
        fullnameStr = fullname.getEditText().getText().toString();
        emailStr = email.getEditText().getText().toString().trim();
        passwordStr = password.getEditText().getText().toString().trim();
        confirmPasswordStr = confirmPassword.getEditText().getText().toString().trim();
        usernameStr = username.getEditText().getText().toString();
    }

    private void setupUI(View v) {
        fullname = v.findViewById(R.id.full_name);
        username = v.findViewById(R.id.username);
        email = v.findViewById(R.id.email);
        password = v.findViewById(R.id.password);
        confirmPassword = v.findViewById(R.id.confirm_password);
        gotoLogin = v.findViewById(R.id.sign_up);
        progressBar = v.findViewById(R.id.signUpProgressBar);
        loginHere = v.findViewById(R.id.login_here);
    }
}
