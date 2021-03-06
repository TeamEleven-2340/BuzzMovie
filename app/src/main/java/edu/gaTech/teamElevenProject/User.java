package edu.gaTech.teamElevenProject;


/**
 * Class that defines the contents of a user.
 */

public class User {

    /**
     * Name of the user
     */
    private String name;

    /**
     * password of the user
     */
    private String password;

    /**
     * Email of the user
     */
    private String email;

    /**
     * full name of the user
     */
    private String fullName;

    /**
     * Ban status of the user
     */
    private boolean banStatus;

    /**
     * Lock status of the user
     */
    private boolean lockStatus;

    /**
     * Major of the user
     */
    private String major;

    /**
     * Interest of the user
     */
    private String interest;

    /**
     * Admin status of the user
     */
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
        fullName = "";
        major = "";
        interest = "";
        adminStatus = false;
    }

    /**
     * unused constructor
     */
    public User() {

    }

    /**
     * Returns if User is an Admin
     * @return Admin Status
     */
    public boolean isAdminStatus() {
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
        return "User: " + name
                + " Ban Status: " + Boolean.toString(banStatus)
                + " Lock Status: " + Boolean.toString(lockStatus);
    }

    /**
     * get the name
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the user
     * @param setName name of the user
     */
    public void setName(String setName) {
        this.name = setName;
    }

    /**
     * get the password
     * @return password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * set the password of the user
     * @param setPassword password of the user
     */
    public void setPassword(String setPassword) {
        this.password = setPassword;
    }

    /**
     * Get the email address of the user
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address of the user
     * @param setEmail email of the user
     */
    public void setEmail(String setEmail) {
        this.email = setEmail;
    }

    /**
     * Get the full name of the user
     * @return full name of the user
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Set the full name of the user
     * @param setFullName full name of the user
     */
    public void setFullName(String setFullName) {
        this.fullName = setFullName;
    }

    /**
     * get the ban status of the user
     * @return the ban status of the user
     */
    public boolean isBanStatus() {
        return banStatus;
    }

    /**
     * get the lock status of the user
     * @return the lock status of the user
     */
    public boolean isLockStatus() {
        return lockStatus;
    }

    /**
     * Get the major of the user
     * @return major of the user
     */
    public String getMajor() {
        return major;
    }

    /**
     * Set the major of the user
     * @param setMajor major of the user
     */
    public void setMajor(String setMajor) {
        this.major = setMajor;
    }

    /**
     * Get the interest of the user
     * @return interest of the user
     */
    public String getInterest() {
        return interest;
    }

    /**
     * Set the interest of the user
     * @param setInterest interest of the user
     */
    public void setInterest(String setInterest) {
        this.interest = setInterest;
    }

    /**
     * Set the admin status of the user
     * @param setAdminStatus admin status of the user
     */
    public void setAdminStatus(boolean setAdminStatus) {
        this.adminStatus = setAdminStatus;
    }
}
