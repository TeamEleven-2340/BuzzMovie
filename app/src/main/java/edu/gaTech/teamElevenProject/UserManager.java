package edu.gaTech.teamElevenProject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * UserManager handles the database of the registered users.
 */

public class UserManager implements AuthenticationFacade, UserManagementFacade {
    /**
     * map that contains all the users
     */
    private static Map<String, User> users = new HashMap<>();
    /**
     * currentUsername for the app
     */
    private static User currentUsername;
    /**
     * private DatabaseWrapper for userManager
     */
    private DatabaseWrapper dbHelper;
    /**
     * private databse that controls database in userManager
     */
    private SQLiteDatabase rdb;
    /**
     * Columns of the database
     */
    private String[] columns = {
        DatabaseWrapper.USERNAME,
        DatabaseWrapper.PASSWORD,
        DatabaseWrapper.EMAIL,
        DatabaseWrapper.fullName,
        DatabaseWrapper.MAJOR,
        DatabaseWrapper.INTEREST,
        DatabaseWrapper.lockStatus,
        DatabaseWrapper.banStatus,
        DatabaseWrapper.adminStatus
    };


    /**
     * Creates the UserManager object.
     * @param databaseHelper SQLiteOpenHelper
     * @param database SQLiteDatabase
     */
    public UserManager(DatabaseWrapper databaseHelper, SQLiteDatabase database) {
        this.dbHelper = databaseHelper;
        this.rdb = database;
    }

    /**
     * Setter method for the current username of the user.
     * @param user the current user
     */
    public void setCurrentUsername(User user) {
        final ArrayList<User> userList = (ArrayList<User>)getUserList();
        for (int i = 0; i < userList.size(); i++) {
            final User foundUser = userList.get(i);
            if (foundUser.getName().equals(user.getName())) {
                currentUsername = foundUser;
            }
        }
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
    public List<User> getUserList (){
        final List usernameList = new ArrayList();
        final ArrayList userList = new ArrayList();
        final Cursor cursor = rdb.query(DatabaseWrapper.USER, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            final String username = cursor.getString(0);
            final String password = cursor.getString(1);
            final String email = cursor.getString(2);
            final String fullname = cursor.getString(2+1);
            final String major = cursor.getString(2+2);
            final String interest = cursor.getString(2+2+1);
            final String lockStatus = cursor.getString(2+2+2);
            final String banStatus = cursor.getString(2+2+2+1);
            final String adminStatus = cursor.getString(2+2+2+2);
            final User user = new User(username, password);
            user.setInterest(interest);
            user.setFullName(fullname);
            user.setLockStatus(Boolean.parseBoolean(lockStatus));
            user.setBanStatus(Boolean.parseBoolean(banStatus));
            user.setMajor(major);
            user.setEmail(email);
            user.setAdminStatus(Boolean.parseBoolean(adminStatus));
            usernameList.add(username);
            userList.add(user);
            cursor.moveToNext();
        }
        return userList;
    }

    /**
     * Finds a user in the database by its username
     * @param id the username
     * @return whether the specified id is in the list of registered users
     */
    public User findUserById(String id) {
        final ArrayList<User> userList = (ArrayList<User>) getUserList();
        for (int i = 0; i < userList.size(); i++) {
            final User user = userList.get(i);
            if (user.getName().equals(id)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a user to the database with the specified username and password.
     * @param name the username inputted by the user
     * @param pass the password inputted by the user
     */
    public void addUser(String name, String pass) {
        final User user = new User(name, pass);
        users.put(name, user);
        final String comma = "','";
        final String query = "INSERT INTO Users (Username,Password,Email,Fullname,Major,Interest,"
                        + "Lockstatus,Banstatus,Adminstatus) VALUES('"+name+"', '"+pass+comma+user.getEmail()+comma+user.getFullName()+"',"
                        + "'"+user.getMajor()+comma+user.getInterest()+comma+Boolean.toString(user.getLockStatus())+"'"
                        + ",'"+Boolean.toString(user.getBanStatus())+comma+"false"+"');";
        rdb.execSQL(query);
        setCurrentUsername(user);
    }

    /**
     * Adds a user to the database with the specified username and password.
     * @param name the username inputted by the user
     * @param pass the password inputted by the user
     */
    public void addAdmin(String name, String pass) {
        final User user = new User(name, pass);
        users.put(name, user);
        users.get(name).setAdminStatus(true);
        final String comma = "','";
        final String query = "INSERT INTO Users (Username,Password,Email,Fullname,Major,Interest,"
                + "Lockstatus,Banstatus,Adminstatus) VALUES('"+name+"', '"+pass+comma+user.getEmail()+comma+user.getFullName()+"',"
                + "'"+user.getMajor()+comma+user.getInterest()+comma+"false"+"'"
                + ",'"+"false"+comma+Boolean.toString(user.isAdminStatus())+"');";
        rdb.execSQL(query);
        setCurrentUsername(user);
    }

    /**
     * Returns if User is an Admin
     * @return Admin Status
     */
    public boolean isAdminStatus(){
        return findUserById(currentUsername.getName()).isAdminStatus();
    }


    /**
     * Handles a request for a user to login. If either the username is invalid or the password
     * is not inputted, the request doesn't go through.
     * @param name the username inputted by the user
     * @param pass the password inputted by the user
     * @return whether the user is registered AND the password is valid
     */
    public boolean handleLoginRequest(String name, String pass) {
        final User u = findUserById(name);
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
        final User u = findUserById(name);
        return u == null;
    }

    /**
     * Update the user database
     */
    public void updateDatabase() {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put(DatabaseWrapper.USERNAME, currentUsername.getName());
        values.put(DatabaseWrapper.PASSWORD, currentUsername.getPassword());
        values.put(DatabaseWrapper.banStatus, Boolean.toString(currentUsername.getBanStatus()));
        values.put(DatabaseWrapper.lockStatus, Boolean.toString(currentUsername.getLockStatus()));
        values.put(DatabaseWrapper.MAJOR, currentUsername.getMajor());
        values.put(DatabaseWrapper.EMAIL, currentUsername.getEmail());
        values.put(DatabaseWrapper.fullName, currentUsername.getFullName());
        values.put(DatabaseWrapper.INTEREST, currentUsername.getInterest());
        values.put(DatabaseWrapper.adminStatus, Boolean.toString(currentUsername.isAdminStatus()));
        final String [] whereArgs = {currentUsername.getName()};
        db.update(DatabaseWrapper.USER, values, DatabaseWrapper.USERNAME + "= ?", whereArgs);
    }
    /**
     * Sets the banned status of a given user.
     *
     * @param status ban stasus to be set
     */
    public void setBannedStatus (Boolean status) {
        currentUsername.setBanStatus(status);
        updateDatabase();
    }
    /**
     * Gets the banned stasus of a current user.
     * @return banned status of a current user
     */
    public boolean getBannedStatus() {
        return findUserById(currentUsername.getName()).getBanStatus();
    }

    /**
     * Sets the lock status of a given user.
     *
     * @param status lock status to be set
     */
    public void setLockStatus (Boolean status) {
        currentUsername.setLockStatus(status);
        updateDatabase();
    }
    /**
     * Gets the lock status of a current user.
     * @return lock status of a current user
     */
    public boolean getLockStatus() {
        return findUserById(currentUsername.getName()).getLockStatus();
    }
}
