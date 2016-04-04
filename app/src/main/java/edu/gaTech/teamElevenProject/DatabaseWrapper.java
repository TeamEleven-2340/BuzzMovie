package edu.gaTech.teamElevenProject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Wrapper for database magic.
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
    public static final String BANSTATUS = "banStatus";

    /**
     * User database column name
     */
    public static final String LOCKSTATUS = "lockStatus";

    /**
     * User database column name
     */
    public static final String FULLNAME = "fullName";

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
    public static final String DATABASENAME = "Users.db";

    /**
     * User database name
     */
    public static final String DATABASEMOVIENAME = "Movie.db";

    /**
     * User database version
     */
    private static final int DATABASEVERSION = 1;

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
    public static final String CSRATEDPEOPLE = "CSRatedPeople";

    /**
     * Movie database column name
     */

    /**
     * Movie database column name
     */
    public static final String MERATEDPEOPLE = "MERatedPeople";

    /**
     * Movie database column name
     */
    public static final String CERATEDPEOPLE = "CERatedPeople";

    /**
     * Movie database column name
     */
    public static final String EERATEDPEOPLE = "EERatedPeople";

    /**
     * String name
     */
    private static final String TNN = " text not null, ";

    /**
     * Database creation for Users.db
     */
    private static final String DATABASECREATE = "CREATE TABLE "
            + USER + " (" + USERNAME + TNN + PASSWORD + TNN
            + EMAIL + TNN + FULLNAME + TNN + MAJOR + TNN
            + INTEREST + TNN + LOCKSTATUS + TNN + BANSTATUS
            + TNN + ADMINSTATUS + " text not null)";

    /**
     * Database creation for Movie.db
     */
    private static final String MOVIEDATABASECREATE = "CREATE TABLE " + MOVIE + " (" + MOVIENAME
            + TNN + RATING + TNN + CSRATING + TNN + MERATING
            + TNN + CERATING + TNN + EERATING + TNN + RATEDPEOPLE
            + TNN + CSRATEDPEOPLE + TNN + MERATEDPEOPLE
            + TNN + CERATEDPEOPLE + TNN + EERATEDPEOPLE + " text not null)";

    /**
     * Constructor for a DatabaseWrapper.
     * @param context context of the activity
     * @param name name of the database
     */
    public DatabaseWrapper(Context context, String name) {
        super(context, name, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASECREATE);
        db.execSQL(MOVIEDATABASECREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        db.execSQL("DROP TABLE IF EXISTS " + MOVIE);
        onCreate(db);
    }
}
