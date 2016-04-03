package edu.gaTech.teamElevenProject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class MovieDetailDisplay extends AppCompatActivity {

    /**
     * current movie
     */
    private Movie movie;
    /**
     * Bar that shows rating
     */
    private RatingBar ratingBar;
    /**
     * Text view that prints rating
     */
    private TextView ratingView;
    /**
     * The title of the movie
     */
    private String combinedterms;
    /**
     * User management facade used in this activity
     */
    private UserManagementFacade ufmdd;
    /**
     * DatabaseWrapper used in this activity
     */
    private DatabaseWrapper moviedbHelper;
    /**
     * SQLiteDatabase used in this activity
     */
    private SQLiteDatabase rdb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_display);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        moviedbHelper = new DatabaseWrapper(this, DatabaseWrapper.DATABASE_MOVIE_NAME);
        rdb = moviedbHelper.getReadableDatabase();
        movie = (Movie) getIntent().getSerializableExtra("movie");
        combinedterms = (String) getIntent().getSerializableExtra("key");
        final List<Movie> movieList = Movies.getMovieList(rdb);
        for (int i = 0; i < movieList.size(); i++) {
            if (movie.getName().equals(movieList.get(i).getName())) {
                movie = movieList.get(i);
            }
        }
        if (movie.getPeopleRated() == 0) {
            movie.setRating(0);
        }
        ufmdd = new UserManager(moviedbHelper, rdb);
        final TextView titleView = (TextView) findViewById(R.id.titleView);
        final TextView genreView = (TextView) findViewById(R.id.genreView);
        final TextView actorsView = (TextView) findViewById(R.id.actorsView);
        final TextView lengthView = (TextView) findViewById(R.id.lengthView);
        final TextView releasedView = (TextView) findViewById(R.id.releasedView);
        ratingView = (TextView) findViewById(R.id.ratingView);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        final String rating = String.format("%.2g%n", movie.getRating());
        titleView.setText(movie.getName());
        genreView.setText(movie.getGenre());
        actorsView.setText(movie.getActors());
        lengthView.setText(movie.getLength());
        releasedView.setText(movie.getReleased());
        ratingView.setText(rating);
    }

    /**
     * Update the rating of the movie as the user inputs a rate
     * @param v the current view
     */
    public void onRateButtonClicked(View v) {
        final String major = ufmdd.getCurrentUsername().getMajor();
        double majorRating;
        double currentMajorRating;
        double rating = ratingBar.getRating();
        if (!"".equals(major)) {
            if (movie.getPeopleByMajors().get(major) != null) {
                if (movie.getPeopleByMajors().containsKey(major)) {
                    majorRating = movie.getRatingByMajors().get(major) + 0.0;
                    currentMajorRating = movie.getPeopleByMajors().get(major) * majorRating;
                    currentMajorRating += rating;
                    movie.setPeopleByMajors(major, movie.getPeopleByMajors().get(major) + 1);
                    movie.setRatingsByMajors(major, currentMajorRating / movie.getPeopleByMajors().get(major));
                }
            } else {
                movie.setPeopleByMajors(major, 1);
                movie.setRatingsByMajors(major, rating);
            }
        }
        final double currentRating = movie.getRating() * movie.getPeopleRated();
        rating = currentRating + rating;
        movie.setPeopleRated(movie.getPeopleRated() + 1);
        rating = rating / movie.getPeopleRated();
        movie.setRating(rating);
        final String ratingString = String.format("%.2g%n", rating);
        ratingView.setText(ratingString);
        Movies.ITEM_MAP.put(combinedterms, movie);
        final List<Movie> a = Movies.ITEMS;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getName().equals(movie.getName())) {
                a.remove(i);
            }
        }
        a.add(movie);
        final List<Movie> movieList = Movies.getMovieList(rdb);
        Boolean movieExist = false;
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getName().equals(movie.getName())) {
                movieExist = true;
            }
        }
        if (movieExist) {
            updateMovie(movie);
        } else {
            addMovie(movie);
        }
    }

    /**
     * Add movie to the database
     * @param mov movie that you are adding
     */
    private void addMovie(Movie mov) {
        final SQLiteDatabase db = moviedbHelper.getWritableDatabase();
        double csRating = 0;
        double meRating = 0;
        double eeRating = 0;
        double ceRating = 0;
        final int ratedPeople = mov.getPeopleRated();
        int csRated = 0;
        int meRated = 0;
        int ceRated = 0;
        int eeRated = 0;

        if (!(mov.getRatingByMajors().get("CS") == null)) {
            csRating = mov.getRatingByMajors().get("CS");
            csRated = mov.getPeopleByMajors().get("CS");
        }

        if (!(mov.getRatingByMajors().get("ME") == null)) {
            meRating = mov.getRatingByMajors().get("ME");
            meRated = mov.getPeopleByMajors().get("ME");
        }

        if (!(mov.getRatingByMajors().get("CE") == null)) {
            ceRating = mov.getRatingByMajors().get("CE");
            ceRated = mov.getPeopleByMajors().get("CE");
        }

        if (!(mov.getRatingByMajors().get("EE") == null)) {
            eeRating = mov.getRatingByMajors().get("EE");
            eeRated = mov.getPeopleByMajors().get("EE");
        }

        final String comma = "','";
        final String query = "INSERT INTO Movie (Name,Rating,CSRating,MERating,CERating,"
                + "EERating,RatedPeople,CSRatedPeople,MERatedPeople,CERatedPeople,EERatedPeople) VALUES('"+mov.getName()+comma+mov.getRating()+"',"
                + "'"+Double.toString(csRating)+comma+Double.toString(meRating)+comma+Double.toString(ceRating)+"',"
                + "'"+Double.toString(eeRating)+comma+Integer.toString(ratedPeople)+comma+Integer.toString(csRated)+"',"
                +"'"+Integer.toString(meRated)+comma+Integer.toString(ceRated)+comma+Integer.toString(eeRated)+"');";
        db.execSQL(query);
    }

    /**
     * Update the database movie.db
     * @param mov the movie that has to be updated
     */
    private void updateMovie(Movie mov) {
        final SQLiteDatabase db = moviedbHelper.getWritableDatabase();
        double csRating = 0;
        double meRating = 0;
        double eeRating = 0;
        double ceRating = 0;
        final int ratedPeople = mov.getPeopleRated();
        int csRated = 0;
        int meRated = 0;
        int ceRated = 0;
        int eeRated = 0;

        if (!(mov.getRatingByMajors().get("CS") == null)) {
            csRating = mov.getRatingByMajors().get("CS");
            csRated = mov.getPeopleByMajors().get("CS");
        }

        if (!(mov.getRatingByMajors().get("ME") == null)) {
            meRating = mov.getRatingByMajors().get("ME");
            meRated = mov.getPeopleByMajors().get("ME");
        }

        if (!(mov.getRatingByMajors().get("CE") == null)) {
            ceRating = mov.getRatingByMajors().get("CE");
            ceRated = mov.getPeopleByMajors().get("CE");
        }

        if (!(mov.getRatingByMajors().get("EE") == null)) {
            eeRating = mov.getRatingByMajors().get("EE");
            eeRated = mov.getPeopleByMajors().get("EE");
        }
        final ContentValues values = new ContentValues();
        values.put(DatabaseWrapper.MOVIE_NAME, mov.getName());
        values.put(DatabaseWrapper.RATING, mov.getRating());
        values.put(DatabaseWrapper.csRating, csRating);
        values.put(DatabaseWrapper.meRating, meRating);
        values.put(DatabaseWrapper.ceRating, ceRating);
        values.put(DatabaseWrapper.eeRating, eeRating);
        values.put(DatabaseWrapper.ratedPeople, ratedPeople);
        values.put(DatabaseWrapper.csPeopleRated, csRated);
        values.put(DatabaseWrapper.mePeopleRated, meRated);
        values.put(DatabaseWrapper.cePeopleRated, ceRated);
        values.put(DatabaseWrapper.eePeopleRated, eeRated);
        final String[] whereArgs = {mov.getName()};
        db.update(DatabaseWrapper.MOVIE, values, DatabaseWrapper.MOVIE_NAME + "= ?", whereArgs);
    }
}