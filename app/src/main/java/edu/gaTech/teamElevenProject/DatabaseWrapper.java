package edu.gaTech.teamElevenProject;

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
    public static final String banStatus = "banStatus";

    /**
     * User database column name
     */
    public static final String lockStatus = "lockStatus";

    /**
     * User database column name
     */
    public static final String fullName = "fullName";

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
    public static final String adminStatus = "AdminStatus";

    /**
     * User database column name
     */
    public static final String DATABASE_NAME = "Users.db";

    /**
     * User database name
     */
    public static final String DATABASE_MOVIE_NAME = "Movie.db";

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
    public static final String MOVIE_NAME = "Name";

    /**
     * Movie database column name
     */
    public static final String RATING = "Rating";

    /**
     * Movie database column name
     */
    public static final String csRating = "CSRating";

    /**
     * Movie database column name
     */
    public static final String meRating = "MERating";

    /**
     * Movie database column name
     */
    public static final String ceRating = "CERating";

    /**
     * Movie database column name
     */
    public static final String eeRating = "EERating";

    /**
     * Movie database column name
     */
    public static final String ratedPeople = "RatedPeople";

    /**
     * Movie database column name
     */
    public static final String csPeopleRated = "CSRatedPeople";

    /**
     * Movie database column name
     */

    /**
     * Movie database column name
     */
    public static final String mePeopleRated = "MERatedPeople";

    /**
     * Movie database column name
     */
    public static final String cePeopleRated = "CERatedPeople";

    /**
     * Movie database column name
     */
    public static final String eePeopleRated = "EERatedPeople";

    /**
     * String name
     */
    private static final String TNN = " text not null, ";

    /**
     * Database creation for Users.db
     */
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + USER + " (" + USERNAME + TNN + PASSWORD + TNN
            + EMAIL + TNN + fullName + TNN + MAJOR + TNN
            + INTEREST + TNN + lockStatus + TNN + banStatus
            + TNN + adminStatus + " text not null)";

    /**
     * Database creation for Movie.db
     */
    private static final String MOVIE_DATABASE_CREATE
            = "CREATE TABLE " + MOVIE + " (" + MOVIE_NAME
            + TNN + RATING + TNN + csRating + TNN + meRating
            + TNN + ceRating + TNN + eeRating + TNN + ratedPeople
            + TNN + csPeopleRated + TNN + mePeopleRated + TNN
            + cePeopleRated + TNN + eePeopleRated + " text not null)";

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
        db.execSQL(MOVIE_DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        db.execSQL("DROP TABLE IF EXISTS " + MOVIE);
        onCreate(db);
    }
}
