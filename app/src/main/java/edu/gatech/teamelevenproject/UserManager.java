package edu.gatech.teamelevenproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private DatabaseWrapper dbHelper;
    private SQLiteDatabase database;
    String[] columns = {
            DatabaseWrapper.USERNAME,
            DatabaseWrapper.PASSWORD,
            DatabaseWrapper.EMAIL,
            DatabaseWrapper.FULLNAME,
            DatabaseWrapper.MAJOR,
            DatabaseWrapper.INTEREST,
            DatabaseWrapper.LOCKSTATUS,
            DatabaseWrapper.BANSTATUS,
            DatabaseWrapper.ADMINSTATUS
    };


    /**
     * Creates the UserManager object.
     */
    public UserManager(Context context) throws SQLiteException {
        dbHelper = new DatabaseWrapper(context, DatabaseWrapper.DATABASE_NAME);
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Setter method for the current username of the user.
     * @param user the current user
     */
    public void setCurrentUsername(User user) {
        ArrayList<User> userList = getUserList();
        for (int i = 0; i < userList.size(); i++) {
            User foundUser = userList.get(i);
            if (foundUser.name.equals(user.name)) {
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
    public ArrayList<User> getUserList (){
        List usernameList = new ArrayList();
        ArrayList userList = new ArrayList();
        SQLiteDatabase rdb = dbHelper.getReadableDatabase();
        Cursor cursor = rdb.query(DatabaseWrapper.USER, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String username = cursor.getString(0);
            String password = cursor.getString(1);
            String email = cursor.getString(2);
            String fullname = cursor.getString(3);
            String major = cursor.getString(4);
            String interest = cursor.getString(5);
            String lockStatus = cursor.getString(6);
            String banStatus = cursor.getString(7);
            String adminStatus = cursor.getString(8);
            User user = new User(username, password);
            user.interest = interest;
            user.fullname = fullname;
            user.lockStatus = Boolean.parseBoolean(lockStatus);
            user.banStatus = Boolean.parseBoolean(banStatus);
            user.major = major;
            user.email = email;
            user.adminStatus = Boolean.parseBoolean(adminStatus);
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
        ArrayList<User> userList = getUserList();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.name.equals(id)) {
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
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        User user = new User(name, pass);
        users.put(name, user);
        String query = "INSERT INTO Users (Username,Password,Email,Fullname,Major,Interest,"
                        + "Lockstatus,Banstatus,Adminstatus) VALUES('"+name+"', '"+pass+"','"+user.email+"','"+user.fullname+"',"
                        + "'"+user.major+"','"+user.interest+"','"+Boolean.toString(user.lockStatus)+"'"
                        + ",'"+Boolean.toString(user.banStatus)+"','"+"false"+"');";
        database.execSQL(query);
        setCurrentUsername(user);
    }

    /**
     * Adds a user to the database with the specified username and password.
     * @param name the username inputted by the user
     * @param pass the password inputted by the user
     */
    public void addAdmin(String name, String pass) {
        User user = new User(name, pass);
        users.put(name, user);
        users.get(name).adminStatus = true;
        String query = "INSERT INTO Users (Username,Password,Email,Fullname,Major,Interest,"
                + "Lockstatus,Banstatus,Adminstatus) VALUES('"+name+"', '"+pass+"','"+user.email+"','"+user.fullname+"',"
                + "'"+user.major+"','"+user.interest+"','"+"false"+"'"
                + ",'"+"false"+"','"+Boolean.toString(user.adminStatus)+"');";
        database.execSQL(query);
        setCurrentUsername(user);
    }

    /**
     * Returns if User is an Admin
     * @return Admin Status
     */
    public boolean isAdminStatus(){
        User user = findUserById(currentUsername.name);
        return user.adminStatus;
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
     * Update the user database
     */
    public void updateDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseWrapper.USERNAME, currentUsername.name);
        values.put(DatabaseWrapper.PASSWORD, currentUsername.password);
        values.put(DatabaseWrapper.BANSTATUS, Boolean.toString(currentUsername.banStatus));
        values.put(DatabaseWrapper.LOCKSTATUS, Boolean.toString(currentUsername.lockStatus));
        values.put(DatabaseWrapper.MAJOR, currentUsername.major);
        values.put(DatabaseWrapper.EMAIL, currentUsername.email);
        values.put(DatabaseWrapper.FULLNAME, currentUsername.fullname);
        values.put(DatabaseWrapper.INTEREST, currentUsername.interest);
        values.put(DatabaseWrapper.ADMINSTATUS, Boolean.toString(currentUsername.adminStatus));
        String [] whereArgs = {currentUsername.name};
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
     *
     */
    public boolean getBannedStatus() {
        User user = findUserById(currentUsername.name);
        return user.banStatus;
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
     * Gets the lock stasus of a current user.
     *
     */
    public boolean getLockStatus() {
        User user = findUserById(currentUsername.name);
        return user.lockStatus;
    }
}
