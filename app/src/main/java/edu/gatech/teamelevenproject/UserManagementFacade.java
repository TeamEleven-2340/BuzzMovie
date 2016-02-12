package edu.gatech.teamelevenproject;

/**
 * Created by Admin on 2/2/2016.
 */
public interface UserManagementFacade {
    void addUser(String name, String pass);
    boolean handleRegisterRequest(String name, String pass);
    User findUserById(String id);
}

