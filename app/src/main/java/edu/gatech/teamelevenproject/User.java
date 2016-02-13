package edu.gatech.teamelevenproject;

import android.widget.Toast;


public class User {
    String name;
    String password;
    static String email = "";
    static String fullname = "";

    public User(String n, String p) {
        name = n;
        password = p;
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }
}
