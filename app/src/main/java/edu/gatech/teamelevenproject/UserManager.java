package edu.gatech.teamelevenproject;

/**
 * Created by Admin on 2/2/2016.
 */

import java.util.HashMap;
import java.util.Map;

public class UserManager implements AuthenticationFacade, UserManagementFacade {
    private static Map<String, User> users = new HashMap<>();


    public User findUserById(String id) {
        return users.get(id);
    }

    public void addUser(String name, String pass) {
        User user = new User(name, pass);
        users.put(name, user);

    }

    public boolean handleLoginRequest(String name, String pass) {
        User u = findUserById(name);
        return u!=null && u.checkPassword(pass);
    }
    public boolean handleRegisterRequest(String name, String pass) {
        User u = findUserById(name);
        if (u == null) {
            return true;
        }
        else {
            return  false;
        }
    }
    public UserManager() {

    }
}
