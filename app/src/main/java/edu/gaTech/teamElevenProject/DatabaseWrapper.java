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
    public static final String user = "Users";

    /**
     * User database column name
     */
    public static final String userName = "Username";

    /**
     * User database column name
     */
    public static final String passWord = "Password";

    /**
     * User database column name
     */
    public static final String major = "Major";

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
    public static final String interest = "Interest";

    /**
     * User database column name
     */
    public static final String email = "Email";

    /**
     * User database column name
     */
    public static final String adminStatus = "AdminStatus";

    /**
     * User database column name
     */
    public static final String databaseName = "Users.db";

    /**
     * User database name
     */
    public static final String databaseMovieName = "Movie.db";

    /**
     * User database version
     */
    private static final int databaseVersion = 1;

    /**
     * Movie database column name
     */
    public static final String movie = "Movie";

    /**
     * Movie database column name
     */
    public static final String movieName = "Name";

    /**
     * Movie database column name
     */
    public static final String rating = "Rating";

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
    public static final String csRatedPeople = "CSRatedPeople";

    /**
     * Movie database column name
     */

    /**
     * Movie database column name
     */
    public static final String meRatedPeople = "MERatedPeople";

    /**
     * Movie database column name
     */
    public static final String ceRatedPeople = "CERatedPeople";

    /**
     * Movie database column name
     */
    public static final String eeRatedPeople = "EERatedPeople";

    /**
     * String name
     */
    private static final String tnn = " text not null, ";

    /**
     * Database creation for Users.db
     */
    private static final String databaseCreate = "CREATE TABLE "
            + user + " (" + userName + tnn + passWord + tnn
            + email + tnn + fullName + tnn + major + tnn
            + interest + tnn + lockStatus + tnn + banStatus
            + tnn + adminStatus + " text not null)";

    /**
     * Database creation for Movie.db
     */
    private static final String movieDatabaseCreate = "CREATE TABLE " + movie + " (" + movieName
            + tnn + rating + tnn + csRating + tnn + meRating
            + tnn + ceRating + tnn + eeRating + tnn + ratedPeople
            + tnn + csRatedPeople + tnn + meRatedPeople
            + tnn + ceRatedPeople + tnn + eeRatedPeople + " text not null)";

    /**
     * Constructor for a DatabaseWrapper.
     * @param context context of the activity
     * @param name name of the database
     */
    public DatabaseWrapper(Context context, String name) {
        super(context, name, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(databaseCreate);
        db.execSQL(movieDatabaseCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + user);
        db.execSQL("DROP TABLE IF EXISTS " + movie);
        onCreate(db);
    }
}
