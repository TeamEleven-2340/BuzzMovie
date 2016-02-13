package edu.gatech.teamelevenproject;

public interface UserManagementFacade {
    void addUser(String name, String pass);
    boolean handleRegisterRequest(String name, String pass);
    User findUserById(String id);
    void setCurrentUsername(User user);
    User getCurrentUsername ();
}

