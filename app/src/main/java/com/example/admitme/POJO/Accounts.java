package com.example.admitme.POJO;

import org.bson.types.ObjectId;

public class Accounts {

    public ObjectId _id;
    public String email;
    public String password;
    public String fullName;
    public int firstTimeLogin;

    public Accounts(){

    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getFirstTimeLogin() {
        return firstTimeLogin;
    }

    public void setFirstTimeLogin(int firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }
}
