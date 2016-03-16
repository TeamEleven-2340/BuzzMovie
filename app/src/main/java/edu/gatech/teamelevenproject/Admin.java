package edu.gatech.teamelevenproject;

/**
 * Created by Jinaru on 3/7/2016.
 */
public class Admin extends User{
    String name;
    String password;
    Boolean isAdmin;
    public Admin (String n, String p) {
        this.name = n;
        this.password = p;
        isAdmin = true;
    }
    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    public boolean isAdminStatus() {
        return true;
    }
    public String toStringBannedStatus() {
        return name + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "Admin";
    }

}
