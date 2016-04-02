package edu.gatech.teamelevenproject;

import java.util.ArrayList;

/**
 * UserManagementFacade is an interface for UserManager.
 */
public interface UserManagementFacade {

    /**
     * Adds a user to the userlist.
     *
     * @param name the user's username
     * @param pass the user's password
     */
    void addUser(String name, String pass);

    /**
     * Adds a admin to the userlist.
     *
     * @param name the user's username
     * @param pass the user's password
     */
    void addAdmin(String name, String pass);

    /**
     * Get the admin status of the user
     * @return admin status of the user
     */
    boolean isAdminStatus();

    /**
     * Update the database
     */
    void updateDatabase();

    /**
     * Handles a request for a user to register with a given password.
     *
     * @param name the user's username
     * @param pass the user's password
     * @return whether the request is a valid request
     */
    boolean handleRegisterRequest(String name, String pass);

    /**
     * Finds a user with a given ID.
     *
     * @param id the inputted ID
     * @return the User with the same ID
     */
    User findUserById(String id);

    /**
     * Sets the username of a given user.
     *
     * @param user the inputted user
     */
    void setCurrentUsername(User user);

    /**
     * Returns the username of a given user.
     *
     * @return user the current user
     */
    User getCurrentUsername();

    /**
     * Getter method for a list of Users
     * @return currentUsername the current username
     */
    ArrayList<User> getUserList();

    /**
     * Sets the banned stasus of a given user.
     *
     * @param status banned stasus
     */
    void setBannedStatus(Boolean status);

    /**
     * Gets the banned status of a given user.
     * @return banned status of the user
     */
    boolean getBannedStatus();

    /**
     * Sets the lock stasus of a given user
     * @param status lock stasus
     */
    void setLockStatus(Boolean status);

    /**
     * Gets the lock status of a given user.
     * @return lock status of the user
     */
    boolean getLockStatus();


}