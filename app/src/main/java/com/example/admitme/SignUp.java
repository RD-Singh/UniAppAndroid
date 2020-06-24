package com.example.admitme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUp extends Fragment {

    //public static final String TAG = "TAG";
    private TextInputLayout fullname, username, email, password, confirmPassword;
    private String fullnameStr, usernameStr, emailStr, passwordStr, confirmPasswordStr;
    private ProgressBar progressBar;
    private static String URL_SIGNUP = "https://phrenological-deale.000webhostapp.com/signup.php";

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

        return v;
    }

    private void addAccount() {

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SIGNUP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            try{
                Log.e("anyText",response);
                JSONObject jsonObject = new JSONObject(response);
                boolean success = jsonObject.getBoolean("success");

                if(success){
                    Toast.makeText(SignUp.this.getContext(), "Sign Up successful", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    gotoLogin();
                }
            }
            catch(Exception e){
                e.printStackTrace();
                Toast.makeText(SignUp.this.getContext(), "Error! " + e.toString(), Toast.LENGTH_SHORT).show();
                System.out.println(e.toString());
                progressBar.setVisibility(View.GONE);
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUp.this.getContext(), "Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> accounts = new HashMap<>();
                accounts.put("Fullname", fullnameStr);
                accounts.put("Email", emailStr);
                accounts.put("Username", usernameStr);
                accounts.put("Password", passwordStr);

                return accounts;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);

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
