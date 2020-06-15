// Creates the package for the app
package com.example.admitme;

// Imports the methods needed in the java class
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

// Creates a class that is linked to the xml file
public class LoginFrag extends Fragment implements Update {

    //Creates variables
    private static final String TAG = "TAG";
    private Button homePageBTN;
    private TextInputLayout email, password;
    private String emailStr, passwordStr, userID;
    private ProgressBar progressBar;
    private TextView forgetPassword;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
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
        // Initializes the Firebase variables
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        // Checks if there is a user signed in and if there is it signs the user out
        if (firebaseAuth.getCurrentUser() != null) {
            firebaseAuth.signOut();
        }

        // Sets an onClickListener on the button which takes the user to the home page
        homePageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calls the method to set up the strings
                setupStr();
                // Sets the progress bar's visibility to visible
                progressBar.setVisibility(View.VISIBLE);

                // Checks if validate email and validate password are true. Only then it will run the if, otherwise it will run the else.
                if (validatePassword() | validateEmail()) {
                    // Sets the progress bar's visibility to gone
                    progressBar.setVisibility(View.GONE);
                } else {
                    // Calls the method to login
                    login();
                    // Sets the progress bar's visibility to gone
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        // Sets an onClickListener on the forget password text
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calls the method to reset the user's password
                resetPassword(v);
            }
        });

        return v;
    }

    public void resetPassword(View v) {

        final EditText resetPasswordEmail = new EditText(v.getContext());
        resetPasswordEmail.setEms(17);
        final AlertDialog.Builder passwordResetDialogue = new AlertDialog.Builder(v.getContext());
        passwordResetDialogue.setTitle("Reset Password?");
        passwordResetDialogue.setMessage("Enter Your Email To Reset Your Password.");
        passwordResetDialogue.setView(resetPasswordEmail);

        passwordResetDialogue.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final String mail = resetPasswordEmail.getText().toString();

                if (mail.isEmpty()) {
                    Toast.makeText(LoginFrag.this.getContext(), "Please enter an email.", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.fetchSignInMethodsForEmail(mail).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                            boolean check = !task.getResult().getSignInMethods().isEmpty();
                            if (!check) {
                                Toast.makeText(LoginFrag.this.getContext(), "This email does not have an account associated with it.", Toast.LENGTH_SHORT).show();
                                resetPasswordEmail.setText("");
                            } else {
                                firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(LoginFrag.this.getContext(), "A password reset link has been sent to your email", Toast.LENGTH_SHORT).show();
                                        counter++;
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginFrag.this.getContext(), "Error!\nLink Hasn't Been Sent To Your Email" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                }


            }
        });

        passwordResetDialogue.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        passwordResetDialogue.create().show();

    }

    @Override
    public void updatePassword(String password) {

        userID = firebaseAuth.getCurrentUser().getUid();

        DocumentReference pswdRef = firebaseFirestore.collection("ACCOUNTS").document(userID);

        pswdRef.update("password", password);

    }

    private void login() {
        firebaseAuth.signInWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                firebaseUser = firebaseAuth.getCurrentUser();
                if (task.isSuccessful()) {
                    Toast.makeText(LoginFrag.this.getContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                    if (counter > 0) {
                        updatePassword(passwordStr);
                    }
                    homePage();

                } else {
                    Toast.makeText(LoginFrag.this.getContext(), "Incorrect Email or Password. " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        password.getEditText().setText("");
        email.getEditText().setText("");
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
    }

    public void homePage() {
        Intent gotoHomePage = new Intent(LoginFrag.this.getContext(), StartupActivity.class);
        startActivity(gotoHomePage);
    }
}
