package com.example.admitme.Main;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.admitme.LoginRegister.LoginFrag;
import com.example.admitme.LoginRegister.MainPage;
import com.example.admitme.R;

import io.realm.mongodb.App;
import io.realm.mongodb.User;

public class SettingsFrag extends Fragment {

    private Button logout;
    private LoginFrag loginFrag = new LoginFrag();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        logout = v.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        return v;
    }

    private void logout(){
        loginFrag.app.currentUser().logOutAsync(new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {
                if(result.isSuccess()){
                    Toast.makeText(SettingsFrag.this.getContext(), "Logging Out", Toast.LENGTH_SHORT).show();
                    goToLogout();
                } else{
                    Toast.makeText(SettingsFrag.this.getContext(), "Could not logout", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToLogout(){
        Intent intent = new Intent(SettingsFrag.this.getContext(), MainPage.class);
        startActivity(intent);
    }
}