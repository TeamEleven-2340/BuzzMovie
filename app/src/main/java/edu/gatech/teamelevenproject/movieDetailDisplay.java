package edu.gatech.teamelevenproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class movieDetailDisplay extends AppCompatActivity {

    private Movie movie;
    private RatingBar ratingBar;
    private TextView ratingView;
    private String combinedterms;
    UserManagementFacade ufmdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        movie = (Movie) getIntent().getSerializableExtra("movie");
        combinedterms = (String) getIntent().getSerializableExtra("key");
        if (movie.getPeopleRated() == 0) {
            movie.setRating(0);
        }
        ufmdd = new UserManager(this);
        TextView titleView = (TextView) findViewById(R.id.titleView);
        TextView genreView = (TextView) findViewById(R.id.genreView);
        TextView actorsView = (TextView) findViewById(R.id.actorsView);
        TextView lengthView = (TextView) findViewById(R.id.lengthView);
        TextView releasedView = (TextView) findViewById(R.id.releasedView);
        ratingView = (TextView) findViewById(R.id.ratingView);
        Button ratingButton = (Button) findViewById(R.id.ratingButton);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        String rating = String.format("%.2g%n", movie.getRating());
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
        String major = ufmdd.getCurrentUsername().major;
        double majorRating;
        double currentMajorRating;
        double rating = ratingBar.getRating();
        if (!major.equals("")) {
            if (movie.getPeopleByMajors().get(major) != null) {
                if (movie.getPeopleByMajors().containsKey(major)) {
                    majorRating = movie.getRatingByMajors().get(major) + 0.0;
                    currentMajorRating = movie.getPeopleByMajors().get(major) * majorRating;
                    currentMajorRating += rating;
                    movie.setPeopleByMajors(major);
                    movie.setRatingsByMajors(major, currentMajorRating / movie.getPeopleByMajors().get(major));
                }
            } else {
                movie.setPeopleByMajors(major);
                movie.setRatingsByMajors(major, rating);
            }
        }
        double currentRating = movie.getRating() * movie.getPeopleRated();
        rating = currentRating + rating;
        movie.setPeopleRated(movie.getPeopleRated() + 1);
        rating = rating / movie.getPeopleRated();
        movie.setRating(rating);
        String ratingString = String.format("%.2g%n", rating);
        ratingView.setText(ratingString);
        Movies.ITEM_MAP.put(combinedterms, movie);
        List<Movie> a = Movies.ITEMS;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getName().equals(movie.getName())) {
                a.remove(i);
            }
        }
        a.add(movie);
    }
}