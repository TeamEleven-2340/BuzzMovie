package edu.gatech.teamelevenproject;


/**
 * Class that defines the contents of a user.
 */

public class User {
    String name;
    String password;
    String email;
    String fullname;
    boolean banStatus;
    boolean lockStatus;
    String major;
    String interest;
    boolean adminStatus;

    /**
     * Creates a user with a name and password.
     * @param n the user's username
     * @param p the user's password
     */
    public User(String n, String p) {
        name = n;
        password = p;
        banStatus = false;
        lockStatus = false;
        email = "";
        fullname = "";
        major = "";
        interest = "";
        adminStatus = false;
    }
    public User() {

    }

    /**
     * Returns if User is an Admin
     * @return Admin Status
     */
    public boolean isAdminStatus(){
        return adminStatus;
    }

    /**
     * Sets ban status of user
     * @param status Status to be set
     */
    public void setBanStatus(boolean status) {
        this.banStatus = status;
    }

    /**
     * Returns ban status of user
     * @return Ban status of user
     */
    public boolean getBanStatus() {
        return this.banStatus;
    }

    /**
     * Sets lock status of user
     * @param status Status to be set
     */
    public void setLockStatus(boolean status) {
        this.lockStatus = status;
    }

    /**
     * Returns lock status of user
     * @return Lock status of user
     */
    public boolean getLockStatus() {
        return this.lockStatus;
    }

    /**
     * Checks the password of a user.
     * @param pass the inputted password
     * @return whether the inputted password equals the user's password
     */
    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    /**
     * Returns string format of banned + locked status
     * @return return string form of banned status
     */
    public String toStringBannedStatus() {
        return "User: " + name + " Ban Status: " + Boolean.toString(banStatus) +
                " Lock Status: " + Boolean.toString(lockStatus);
    }
}
