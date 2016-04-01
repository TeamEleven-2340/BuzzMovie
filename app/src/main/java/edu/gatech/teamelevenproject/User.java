package edu.gatech.teamelevenproject;


/**
 * Class that defines the contents of a user.
 */

public class User {


    private String name;
    private String password;
    private String email;
    private String fullname;
    private boolean banStatus;
    private boolean lockStatus;
    private String major;
    private String interest;
    private boolean adminStatus;

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

    public String getName() {
        return name;
    }

    public void setName(String setname) {
        this.name = setname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String setpassword) {
        this.password = setpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String setemail) {
        this.email = setemail;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String setfullname) {
        this.fullname = setfullname;
    }

    public boolean isBanStatus() {
        return banStatus;
    }

    public boolean isLockStatus() {
        return lockStatus;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String setmajor) {
        this.major = setmajor;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String setinterest) {
        this.interest = setinterest;
    }

    public void setAdminStatus(boolean setadminStatus) {
        this.adminStatus = setadminStatus;
    }
}
