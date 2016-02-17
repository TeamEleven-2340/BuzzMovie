package edu.gatech.teamelevenproject;

import android.widget.Toast;

/**
 * Class that defines the contents of a user.
 */

public class User {
    String name;
    String password;
    static String email = "";
    static String fullname = "";
    static boolean banStatus = false;
    static String major = "";
    static String interest = "";

    /**
     * Creates a user with a name and password.
     * @param n the user's username
     * @param p the user's password
     */
    public User(String n, String p) {
        name = n;
        password = p;
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }
}
