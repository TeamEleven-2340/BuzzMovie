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
    boolean banStatus;
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
        banStatus = false;
    }
    public User() {

    }

    public boolean isAdminStatus(){
        return false;   }

    public void setBanStatus(boolean status) {
        this.banStatus = status;
    }

    public boolean getBanStatus() {
        return this.banStatus;
    }

    /**
     * Checks the password of a user.
     * @param pass the inputted password
     * @return whether the inputted password equals the user's password
     */
    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    public String toStringBannedStatus() {
        return name + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + Boolean.toString(banStatus);
    }
}
