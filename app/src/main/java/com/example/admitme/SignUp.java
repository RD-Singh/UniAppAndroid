package com.example.admitme;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUp extends Fragment {

    public static final String TAG = "TAG";
    private TextInputLayout fullname, username, email, password, confirmPassword;
    private String fullnameStr, usernameStr, emailStr, passwordStr, confirmPasswordStr, userID;
    private ProgressBar progressBar;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private FirebaseUser fUser;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" + "(?=.*[0-9])" + "(?=.*[a-zA-Z])" + "(?=.*[@#$%^&+=*])" + "(?=\\S+$)" + ".{4,}" + "$");

    private Button gotoLogin;

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
        if (text.isEmpty()) {
            return true;
        }

        return false;
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
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().remove(signUp);

        fragmentTransaction.replace(R.id.fragment_container, loginFrag);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);

        setupUI(v);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser() != null) {
            gotoLogin();
        }

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupStr();
                progressBar.setVisibility(View.VISIBLE);

                if (validateFullname() | validateUsername() | validateEmail() | validatePassword() | validateConfirmPassword()) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    addAccount();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        return v;
    }

    private void addAccount() {
        fAuth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    fUser = fAuth.getCurrentUser();

                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference dRef = fStore.collection("ACCOUNTS").document(userID);
                    Map<String, Object> accounts = new HashMap<>();
                    accounts.put("Full Name", fullnameStr);
                    accounts.put("Email", emailStr);
                    accounts.put("Username", usernameStr);
                    accounts.put("Password", passwordStr);
                    dRef.set(accounts).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: user Profile created for " + userID);
                            Toast.makeText(SignUp.this.getContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.toString());
                            Toast.makeText(SignUp.this.getContext(), "Sign Up unsuccessful. Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    gotoLogin();

                } else {
                    Toast.makeText(SignUp.this.getContext(), "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
    }
}
