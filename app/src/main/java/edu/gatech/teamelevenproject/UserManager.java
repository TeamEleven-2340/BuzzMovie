package edu.gatech.teamelevenproject;

import android.content.ContentValues;
import android.content.Context;
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
    private static Map<String, User> users = new HashMap<>();
    private static User currentUsername;
    private DatabaseWrapper dbHelper;
    private SQLiteDatabase database;
    private String[] columns = {
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
    public UserManager(Context context) {
        dbHelper = new DatabaseWrapper(context, DatabaseWrapper.DATABASE_NAME);
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Setter method for the current username of the user.
     * @param user the current user
     */
    public void setCurrentUsername(User user) {
        final ArrayList<User> userList = getUserList();
        for (int i = 0; i < userList.size(); i++) {
            final User foundUser = userList.get(i);
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
        final List usernameList = new ArrayList();
        final ArrayList userList = new ArrayList();
        final SQLiteDatabase rdb = dbHelper.getReadableDatabase();
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
        final ArrayList<User> userList = getUserList();
        for (int i = 0; i < userList.size(); i++) {
            final User user = userList.get(i);
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
        final User user = new User(name, pass);
        users.put(name, user);
        final String comma = "','";
        final String query = "INSERT INTO Users (Username,Password,Email,Fullname,Major,Interest,"
                        + "Lockstatus,Banstatus,Adminstatus) VALUES('"+name+"', '"+pass+comma+user.email+comma+user.fullname+"',"
                        + "'"+user.major+comma+user.interest+comma+Boolean.toString(user.lockStatus)+"'"
                        + ",'"+Boolean.toString(user.banStatus)+comma+"false"+"');";
        database.execSQL(query);
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
        users.get(name).adminStatus = true;
        final String comma = "','";
        final String query = "INSERT INTO Users (Username,Password,Email,Fullname,Major,Interest,"
                + "Lockstatus,Banstatus,Adminstatus) VALUES('"+name+"', '"+pass+comma+user.email+comma+user.fullname+"',"
                + "'"+user.major+comma+user.interest+comma+"false"+"'"
                + ",'"+"false"+comma+Boolean.toString(user.adminStatus)+"');";
        database.execSQL(query);
        setCurrentUsername(user);
    }

    /**
     * Returns if User is an Admin
     * @return Admin Status
     */
    public boolean isAdminStatus(){
        return findUserById(currentUsername.name).adminStatus;
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
        values.put(DatabaseWrapper.USERNAME, currentUsername.name);
        values.put(DatabaseWrapper.PASSWORD, currentUsername.password);
        values.put(DatabaseWrapper.BANSTATUS, Boolean.toString(currentUsername.banStatus));
        values.put(DatabaseWrapper.LOCKSTATUS, Boolean.toString(currentUsername.lockStatus));
        values.put(DatabaseWrapper.MAJOR, currentUsername.major);
        values.put(DatabaseWrapper.EMAIL, currentUsername.email);
        values.put(DatabaseWrapper.FULLNAME, currentUsername.fullname);
        values.put(DatabaseWrapper.INTEREST, currentUsername.interest);
        values.put(DatabaseWrapper.ADMINSTATUS, Boolean.toString(currentUsername.adminStatus));
        final String [] whereArgs = {currentUsername.name};
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
        return findUserById(currentUsername.name).banStatus;
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
        return findUserById(currentUsername.name).lockStatus;
    }
}
