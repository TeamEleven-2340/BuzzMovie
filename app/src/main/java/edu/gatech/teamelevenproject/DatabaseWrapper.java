package edu.gatech.teamelevenproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 3/21/2016.
 */
public class DatabaseWrapper extends SQLiteOpenHelper {
    /**
     * User database column name
     */
    public static final String USER = "Users";
    /**
     * User database column name
     */
    public static final String USERNAME = "Username";
    /**
     * User database column name
     */
    public static final String PASSWORD = "Password";
    /**
     * User database column name
     */
    public static final String MAJOR = "Major";
    /**
     * User database column name
     */
    public static final String BANSTATUS = "Banstatus";
    /**
     * User database column name
     */
    public static final String LOCKSTATUS = "Lockstatus";
    /**
     * User database column name
     */
    public static final String FULLNAME = "Fullname";
    /**
     * User database column name
     */
    public static final String INTEREST = "Interest";
    /**
     * User database column name
     */
    public static final String EMAIL = "Email";
    /**
     * User database column name
     */
    public static final String ADMINSTATUS = "AdminStatus";
    /**
     * User database column name
     */
    public static final String DATABASE_NAME = "Users.db";
    /**
     * User database name
     */
    public static final String DATABASEMOVIE_NAME = "Movie.db";
    /**
     * User database version
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Movie database column name
     */
    public static final String MOVIE = "Movie";
    /**
     * Movie database column name
     */
    public static final String MOVIENAME = "Name";
    /**
     * Movie database column name
     */
    public static final String RATING = "Rating";
    /**
     * Movie database column name
     */
    public static final String CSRATING = "CSRating";
    /**
     * Movie database column name
     */
    public static final String MERATING = "MERating";
    /**
     * Movie database column name
     */
    public static final String CERATING = "CERating";
    /**
     * Movie database column name
     */
    public static final String EERATING = "EERating";
    /**
     * Movie database column name
     */
    public static final String RATEDPEOPLE = "RatedPeople";
    /**
     * Movie database column name
     */
    public static final String CSPEOPLERATED = "CSRatedPeople";
    /**
     * Movie database column name
     */
    /**
     * Movie database column name
     */
    public static final String MEPEOPLERATED = "MERatedPeople";
    /**
     * Movie database column name
     */
    public static final String CEPEOPLERATED = "CERatedPeople";
    /**
     * Movie database column name
     */
    public static final String EEPEOPLERATED = "EERatedPeople";
    /**
     * String name
     */
    private static final String TNN = " text not null, ";
    /**
     * Database creation for Users.db
     */
    private static final String DATABASE_CREATE = "CREATE TABLE " + USER
            + " (" + USERNAME + TNN + PASSWORD + TNN + EMAIL + TNN
            + FULLNAME + TNN + MAJOR + TNN + INTEREST
            + TNN + LOCKSTATUS + TNN + BANSTATUS + TNN
            + ADMINSTATUS + " text not null)";
    /**
     * Database creation for Movie.db
     */
    private static final String MOVIEDATABASE_CREATE = "CREATE TABLE " + MOVIE + " ("+MOVIENAME +TNN + RATING + TNN
            + CSRATING + TNN + MERATING + TNN + CERATING + TNN +
            EERATING + TNN + RATEDPEOPLE + TNN + CSPEOPLERATED + TNN + MEPEOPLERATED
            + TNN + CEPEOPLERATED + TNN + EEPEOPLERATED + " text not null)";

    /**
     * Constructor
     * @param context context of the activity
     * @param name name of the database
     */
    public DatabaseWrapper(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        db.execSQL(MOVIEDATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        db.execSQL("DROP TABLE IF EXISTS " + MOVIE);
        onCreate(db);
    }
}
