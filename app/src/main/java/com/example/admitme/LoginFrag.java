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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// Creates a class that is linked to the xml file
public class LoginFrag extends Fragment {

    //Creates variables
    private static final String TAG = "TAG";
    private Button homePageBTN;
    private TextInputLayout email, password;
    private String emailStr, passwordStr, userID;
    private ProgressBar progressBar;
    private TextView forgetPassword, signUpHere;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private static String URL_LOGIN = "https://phrenological-deale.000webhostapp.com/login.php";
    SessionManager sessionManager;
    private FirebaseUser firebaseUser;
    private int counter = 0;

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

        // Calls the method to set up the user interface views
        setupUI(v);
        sessionManager = new SessionManager(this.getContext());

        // Sets an onClickListener on the button which takes the user to the home page
        homePageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calls the method to set up the strings
                setupStr();

                // Checks if validate email and validate password are true. Only then it will run the if, otherwise it will run the else.
                if (validatePassword() | validateEmail()) {
                    // Sets the progress bar's visibility to gone
                } else {
                    // Calls the method to login
                    login();
                    // Sets the progress bar's visibility to gone
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if(success){
                        Toast.makeText(LoginFrag.this.getContext(), "Login Success!", Toast.LENGTH_SHORT).show();

                        for(int i = 0; i < jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);

                            String email = object.getString("Email").trim();
                            String username = object.getString("Username").trim();

                            sessionManager.createSession(email, username);

                            Intent gotoHomePage = new Intent(LoginFrag.this.getContext(), StartupActivity.class);
                            gotoHomePage.putExtra("email", email);
                            gotoHomePage.putExtra("username", username);
                            startActivity(gotoHomePage);
                        }

                    }else{
                        Toast.makeText(LoginFrag.this.getContext(), "Incorrect Username or Password.", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);

                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println(e.toString());
                    Toast.makeText(LoginFrag.this.getContext(), "Login error! " + e.toString(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginFrag.this.getContext(), "Login error! " + error.toString(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> account = new HashMap<>();

                account.put("Email", emailStr);
                account.put("Password", passwordStr);

                return account;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);

        password.getEditText().setText("");
        email.getEditText().setText("");
    }

    public void signUpHere(){

        SignUp signUp = new SignUp();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
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
