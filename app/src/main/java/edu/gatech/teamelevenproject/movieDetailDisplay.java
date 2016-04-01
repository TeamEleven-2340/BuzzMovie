package edu.gatech.teamelevenproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class movieDetailDisplay extends AppCompatActivity {

    private Movie movie;
    private RatingBar ratingBar;
    private TextView ratingView;
    private String combinedterms;
    private UserManagementFacade ufmdd;
    private DatabaseWrapper moviedbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_display);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        moviedbHelper = new DatabaseWrapper(this, DatabaseWrapper.DATABASEMOVIE_NAME);
        movie = (Movie) getIntent().getSerializableExtra("movie");
        combinedterms = (String) getIntent().getSerializableExtra("key");
        final List<Movie> movieList = Movies.getMovieList(this);
        for (int i = 0; i < movieList.size(); i++) {
            if (movie.getName().equals(movieList.get(i).getName())) {
                movie = movieList.get(i);
            }
        }
        if (movie.getPeopleRated() == 0) {
            movie.setRating(0);
        }
        ufmdd = new UserManager(this);
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
        final String major = ufmdd.getCurrentUsername().major;
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
        final List<Movie> movieList = Movies.getMovieList(this);
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
     * @param movie movie that you are adding
     */
    private void addMovie(Movie movie) {
        final SQLiteDatabase db = moviedbHelper.getWritableDatabase();
        double csRating = 0;
        double meRating = 0;
        double eeRating = 0;
        double ceRating = 0;
        final int ratedPeople = movie.getPeopleRated();
        int csRated = 0;
        int meRated = 0;
        int ceRated = 0;
        int eeRated = 0;

        if (!(movie.getRatingByMajors().get("CS") == null)) {
            csRating = movie.getRatingByMajors().get("CS");
            csRated = movie.getPeopleByMajors().get("CS");
        }

        if (!(movie.getRatingByMajors().get("ME") == null)) {
            meRating = movie.getRatingByMajors().get("ME");
            meRated = movie.getPeopleByMajors().get("ME");
        }

        if (!(movie.getRatingByMajors().get("CE") == null)) {
            ceRating = movie.getRatingByMajors().get("CE");
            ceRated = movie.getPeopleByMajors().get("CE");
        }

        if (!(movie.getRatingByMajors().get("EE") == null)) {
            eeRating = movie.getRatingByMajors().get("EE");
            eeRated = movie.getPeopleByMajors().get("EE");
        }

        final String comma = "','";
        final String query = "INSERT INTO Movie (Name,Rating,CSRating,MERating,CERating,"
                + "EERating,RatedPeople,CSRatedPeople,MERatedPeople,CERatedPeople,EERatedPeople) VALUES('"+movie.getName()+comma+movie.getRating()+"',"
                + "'"+Double.toString(csRating)+comma+Double.toString(meRating)+comma+Double.toString(ceRating)+"',"
                + "'"+Double.toString(eeRating)+comma+Integer.toString(ratedPeople)+comma+Integer.toString(csRated)+"',"
                +"'"+Integer.toString(meRated)+comma+Integer.toString(ceRated)+comma+Integer.toString(eeRated)+"');";
        db.execSQL(query);
    }

    /**
     * Update the database movie.db
     * @param movie the movie that has to be updated
     */
    private void updateMovie(Movie movie) {
        final SQLiteDatabase db = moviedbHelper.getWritableDatabase();
        double csRating = 0;
        double meRating = 0;
        double eeRating = 0;
        double ceRating = 0;
        final int ratedPeople = movie.getPeopleRated();
        int csRated = 0;
        int meRated = 0;
        int ceRated = 0;
        int eeRated = 0;

        if (!(movie.getRatingByMajors().get("CS") == null)) {
            csRating = movie.getRatingByMajors().get("CS");
            csRated = movie.getPeopleByMajors().get("CS");
        }

        if (!(movie.getRatingByMajors().get("ME") == null)) {
            meRating = movie.getRatingByMajors().get("ME");
            meRated = movie.getPeopleByMajors().get("ME");
        }

        if (!(movie.getRatingByMajors().get("CE") == null)) {
            ceRating = movie.getRatingByMajors().get("CE");
            ceRated = movie.getPeopleByMajors().get("CE");
        }

        if (!(movie.getRatingByMajors().get("EE") == null)) {
            eeRating = movie.getRatingByMajors().get("EE");
            eeRated = movie.getPeopleByMajors().get("EE");
        }
        final ContentValues values = new ContentValues();
        values.put(DatabaseWrapper.MOVIENAME, movie.getName());
        values.put(DatabaseWrapper.RATING, movie.getRating());
        values.put(DatabaseWrapper.CSRATING, csRating);
        values.put(DatabaseWrapper.MERATING, meRating);
        values.put(DatabaseWrapper.CERATING, ceRating);
        values.put(DatabaseWrapper.EERATING, eeRating);
        values.put(DatabaseWrapper.RATEDPEOPLE, ratedPeople);
        values.put(DatabaseWrapper.CSPEOPLERATED, csRated);
        values.put(DatabaseWrapper.MEPEOPLERATED, meRated);
        values.put(DatabaseWrapper.CEPEOPLERATED, ceRated);
        values.put(DatabaseWrapper.EEPEOPLERATED, eeRated);
        final String[] whereArgs = {movie.getName()};
        db.update(DatabaseWrapper.MOVIE, values, DatabaseWrapper.MOVIENAME + "= ?", whereArgs);
    }
}