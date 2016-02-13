package edu.gatech.teamelevenproject;

import android.widget.Toast;


public class User {
    String name;
    String password;
    String email = "";
    String fullname = "";

    public User(String n, String p) {
        name = n;
        password = p;
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }
}
