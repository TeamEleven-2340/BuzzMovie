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
    private static final String DATABASE_NAME = "Users.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE " + USER
            + " (" + USERNAME + " text not null, " + PASSWORD + " text not null)";

    public DatabaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        onCreate(db);
    }
}
