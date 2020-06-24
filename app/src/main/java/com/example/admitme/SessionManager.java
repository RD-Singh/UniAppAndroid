package com.example.admitme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    final int PRIVATE_MODE = 0;

    private static final String PREFERENCE_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    final String USER_NAME = "USERNAME";
    final String EMAIL = "EMAIL";


    public SessionManager (Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String email, String username){
        editor.putBoolean(LOGIN, true);
        editor.putString(USER_NAME, username);
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void loginStatus(){
        if(!this.isLoggedIn()){
            Intent intent = new Intent(context, MainPage.class);
            context.startActivity(intent);
            ((StartupActivity) context).finish();
        }
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, MainPage.class);
        context.startActivity(intent);
        ((StartupActivity) context).finish();
    }

    public HashMap<String, String> userDetails(){
        HashMap<String, String> user = new HashMap<>();
        user.put(USER_NAME, sharedPreferences.getString(USER_NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        return user;
    }
}