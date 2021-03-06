package edu.gaTech.teamElevenProject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class puts group of movies into an array called Movies.
 */
public final class Movies {

    /**
     * unused constructor
     */
    private Movies() {

    }

    /**
     * An array of Movie objects.
     */
    public static final List<Movie> ITEMS = new ArrayList<>();

    /**
     * A map of states  by Name.
     */
    public static final Map<String, Movie> ITEM_MAP = new HashMap<>();

    /** Getter method for a list of Movies
     * @param rdb SQLiteDatabase of this activity
     * @return Movies the list of movies
     */
    public static List<Movie> getMovieList(SQLiteDatabase rdb) {
        final List movieList = new ArrayList();
        final String[] columns = {
            DatabaseWrapper.MOVIENAME,
            DatabaseWrapper.RATING,
            DatabaseWrapper.CSRATING,
            DatabaseWrapper.MERATING,
            DatabaseWrapper.CERATING,
            DatabaseWrapper.EERATING,
            DatabaseWrapper.RATEDPEOPLE,
            DatabaseWrapper.CSRATEDPEOPLE,
            DatabaseWrapper.MERATEDPEOPLE,
            DatabaseWrapper.CERATEDPEOPLE,
            DatabaseWrapper.EERATEDPEOPLE
        };
        final Cursor cursor = rdb.query(DatabaseWrapper.MOVIE,
                columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final String name = cursor.getString(0);
            final String rating = cursor.getString(1);
            final String csRating = cursor.getString(2);
            final String meRating = cursor.getString(3);
            final String ceRating = cursor.getString(4);
            final String eeRating = cursor.getString(5);
            final String ratedPeople = cursor.getString(6);
            final String csRatedPeople = cursor.getString(7);
            final String meRatedPeople = cursor.getString(8);
            final String ceRatedPeople = cursor.getString(9);
            final String eeRatedPeople = cursor.getString(10);
            final Movie movie = new Movie();
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
