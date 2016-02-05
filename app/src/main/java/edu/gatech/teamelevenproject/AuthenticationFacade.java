package edu.gatech.teamelevenproject;

/**
 * Created by Admin on 2/2/2016.
 */
public interface AuthenticationFacade {
    boolean handleLoginRequest(String name, String password);
}
