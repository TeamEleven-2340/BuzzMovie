package edu.gaTech.teamElevenProject;

/**
 * Interface that handles login requests.
 */
public interface AuthenticationFacade {

    /**
     * Handle login request
     * @param name name of the user
     * @param password password for the user
     * @return true if password matches user
     */
    boolean handleLoginRequest(String name, String password);
}
