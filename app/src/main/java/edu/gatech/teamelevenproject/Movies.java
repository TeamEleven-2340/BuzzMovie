package edu.gatech.teamelevenproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class puts group of movies into an array called Movies.
 */
public class Movies {

    /**
     * An array of Movie objects.
     */
    public static final List<Movie> ITEMS = new ArrayList<>();

    /**
     * A map of states  by Name.
     */
    public static final Map<String, Movie> ITEM_MAP = new HashMap<>();

    /** Getter method for a list of Movies
     * @return Movies the list of movies
     */
    public static List<Movie> getMovieList (Context context){
        List movieList = new ArrayList();
        DatabaseWrapper moviedbHelper = new DatabaseWrapper(context, DatabaseWrapper.DATABASEMOVIE_NAME);
        SQLiteDatabase rdb = moviedbHelper.getReadableDatabase();
        String[] columns = {
                DatabaseWrapper.MOVIENAME,
                DatabaseWrapper.RATING,
                DatabaseWrapper.CSRATING,
                DatabaseWrapper.MERATING,
                DatabaseWrapper.CERATING,
                DatabaseWrapper.EERATING,
                DatabaseWrapper.RATEDPEOPLE,
                DatabaseWrapper.CSPEOPLERATED,
                DatabaseWrapper.MEPEOPLERATED,
                DatabaseWrapper.CEPEOPLERATED,
                DatabaseWrapper.EEPEOPLERATED
        };
        Cursor cursor = rdb.query(DatabaseWrapper.MOVIE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            String rating = cursor.getString(1);
            String csRating = cursor.getString(2);
            String meRating = cursor.getString(3);
            String ceRating = cursor.getString(4);
            String eeRating = cursor.getString(5);
            String ratedPeople = cursor.getString(6);
            String csRatedPeople = cursor.getString(7);
            String meRatedPeople = cursor.getString(8);
            String ceRatedPeople = cursor.getString(9);
            String eeRatedPeople = cursor.getString(10);
            Movie movie = new Movie();
            movie.setName(name);
            movie.setRating(Double.parseDouble(rating));
            movie.setPeopleRated(Integer.parseInt(ratedPeople));
            movie.setRatingsByMajors("CS", Double.parseDouble(csRating));
            movie.setRatingsByMajors("ME", Double.parseDouble(meRating));
            movie.setRatingsByMajors("CE", Double.parseDouble(ceRating));
            movie.setRatingsByMajors("EE", Double.parseDouble(eeRating));
            movie.setPeopleByMajors("CS", Integer.parseInt(csRatedPeople));
            movie.setPeopleByMajors("ME", Integer.parseInt(meRatedPeople));
            movie.setPeopleByMajors("CE", Integer.parseInt(ceRatedPeople));
            movie.setPeopleByMajors("EE", Integer.parseInt(eeRatedPeople));
            movieList.add(movie);
            cursor.moveToNext();
        }
        return movieList;
    }
}
