package edu.gaTech.teamElevenProject;

/**
 * Class that handles administrators.
 */
public class Admin extends User {

    /**
     * name of the admin
     */
    private String name;

    /**
     * password of the admin
     */
    private String password;

    /**
     * constructor
     * @param n name
     * @param p password
     */
    public Admin(String n, String p) {
        this.name = n;
        this.password = p;
    }

    /**
     * Check the password
     * @param pass the inputted password
     * @return true if password is correct
     */
    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    /**
     * Check the name
     * @param na the inputted name
     * @return true if name is correct
     */
    public boolean checkName(String na) {
        return name.equals(na);
    }
}
