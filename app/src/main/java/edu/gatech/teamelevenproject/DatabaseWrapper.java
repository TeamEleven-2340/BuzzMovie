package edu.gatech.teamelevenproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 3/21/2016.
 */
public class DatabaseWrapper extends SQLiteOpenHelper {
    public static final String USER = "Users";
    public static final String USERNAME = "Username";
    public static final String PASSWORD = "Password";
    public static final String MAJOR = "Major";
    public static final String BANSTATUS = "Banstatus";
    public static final String LOCKSTATUS = "Lockstatus";
    public static final String FULLNAME = "Fullname";
    public static final String INTEREST = "Interest";
    public static final String EMAIL = "Email";
    public static final String ADMINSTATUS = "AdminStatus";
    public static final String DATABASE_NAME = "Users.db";
    public static final String DATABASEMOVIE_NAME = "Movie.db";
    private static final int DATABASE_VERSION = 1;
    public static final String MOVIE = "Movie";
    public static final String MOVIENAME = "Name";
    public static final String RATING = "Rating";
    public static final String CSRATING = "CSRating";
    public static final String MERATING = "MERating";
    public static final String CERATING = "CERating";
    public static final String EERATING = "EERating";
    public static final String RATEDPEOPLE = "RatedPeople";
    public static final String CSPEOPLERATED = "CSRatedPeople";
    public static final String MEPEOPLERATED = "MERatedPeople";
    public static final String CEPEOPLERATED = "CERatedPeople";
    public static final String EEPEOPLERATED = "EERatedPeople";

    private static final String tnn = " text not null, ";

    private static final String DATABASE_CREATE = "CREATE TABLE " + USER
            + " (" + USERNAME + tnn + PASSWORD + tnn + EMAIL + tnn
            + FULLNAME + tnn + MAJOR + tnn + INTEREST
            + tnn + LOCKSTATUS + tnn + BANSTATUS + tnn
            + ADMINSTATUS + " text not null)";
    private static final String MOVIEDATABASE_CREATE = "CREATE TABLE " + MOVIE + " ("+MOVIENAME +tnn + RATING + tnn
            + CSRATING + tnn + MERATING + tnn + CERATING + tnn +
            EERATING + tnn + RATEDPEOPLE + tnn + CSPEOPLERATED + tnn + MEPEOPLERATED
            + tnn + CEPEOPLERATED + tnn + EEPEOPLERATED + " text not null)";

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
