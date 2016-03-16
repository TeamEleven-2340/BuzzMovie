package edu.gatech.teamelevenproject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * UserManager handles the database of the registered users.
 */

public class UserManager implements AuthenticationFacade, UserManagementFacade {
    private static Map<String, User> users = new HashMap<>();
    private static User currentUsername;

    /**
     * Setter method for the current username of the user.
     * @param user the current user
     */
    public void setCurrentUsername(User user) {
        currentUsername = user;
    }

    /**
     * Getter method for the current username of the user.
     * @return currentUsername the current username
     */
    public User getCurrentUsername (){
        return currentUsername;
    }

    /**
     * Getter method for a list of Users
     * @return currentUsername the current username
     */
    public ArrayList<User> getUserList (){
        return new ArrayList<User>(users.values());
    }

    /**
     * Finds a user in the database by its username
     * @param id the username
     * @return whether the specified id is in the list of registered users
     */
    public User findUserById(String id) {
        return users.get(id);
    }

    /**
     * Adds a user to the database with the specified username and password.
     * @param name the username inputted by the user
     * @param pass the password inputted by the user
     */
    public void addUser(String name, String pass) {
        User user = new User(name, pass);
        users.put(name, user);
    }

    /**
     * Adds a user to the database with the specified username and password.
     * @param name the username inputted by the user
     * @param pass the password inputted by the user
     */
    public void addAdmin(String name, String pass) {
        Admin user = new Admin(name, pass);
        users.put(name, user);
    }

    /**
     * Handles a request for a user to login. If either the username is invalid or the password
     * is not inputted, the request doesn't go through.
     * @param name the username inputted by the user
     * @param pass the password inputted by the user
     * @return whether the user is registered AND the password is valid
     */
    public boolean handleLoginRequest(String name, String pass) {
        User u = findUserById(name);
        return u != null && u.checkPassword(pass);
    }

    /**
     * Handles a request for a user to register. If the username isn't already used, the user can
     * register with that username. Otherwise, the register activity cannot be completed..
     * @param name the username inputted by the user
     * @param pass the password inputted by the user
     * @return whether the user is registered
     */
    public boolean handleRegisterRequest(String name, String pass) {
        User u = findUserById(name);
        return u == null;
    }
    /**
     * Sets the banned status of a given user.
     *
     * @param status ban stasus to be set
     */
    public void setBannedStatus (Boolean status) {
        currentUsername.setBanStatus(status);
}
    /**
     * Gets the banned stasus of a current user.
     *
     */
    public boolean getBannedStatus() {
        return currentUsername.getBanStatus();
    }

    /**
     * Sets the lock status of a given user.
     *
     * @param status lock stasus to be set
     */
    public void setLockStatus (Boolean status) {
        currentUsername.setLockStatus(status);
    }
    /**
     * Gets the lock stasus of a current user.
     *
     */
    public boolean getLockStatus() {
        return currentUsername.getLockStatus();
    }

    /**
     * Creates the UserManager object.
     */
    public UserManager() {

    }
}
