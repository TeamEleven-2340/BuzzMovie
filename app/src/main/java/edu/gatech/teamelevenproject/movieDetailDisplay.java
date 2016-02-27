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

public class movieDetailDisplay extends AppCompatActivity {

    private Movie movie;
    private RatingBar ratingBar;
    private TextView ratingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        movie = (Movie) getIntent().getSerializableExtra("movie");
        if (movie.getPeopleRated() == 0) {
            movie.setRating(0);
        }
        TextView titleView = (TextView) findViewById(R.id.titleView);
        TextView genreView = (TextView) findViewById(R.id.genreView);
        TextView actorsView = (TextView) findViewById(R.id.actorsView);
        TextView lengthView = (TextView) findViewById(R.id.lengthView);
        TextView releasedView = (TextView) findViewById(R.id.releasedView);
        ratingView = (TextView) findViewById(R.id.ratingView);
        Button ratingButton = (Button) findViewById(R.id.ratingButton);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        String rating = Float.toString(movie.getRating());
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
        float rating = ratingBar.getRating();
        float currentRating = movie.getRating() * movie.getPeopleRated();
        rating = currentRating + rating;
        movie.setPeopleRated(movie.getPeopleRated() + 1);
        rating = rating / movie.getPeopleRated();
        movie.setRating(rating);
        String ratingString = String.format("%.2g%n", rating);
        ratingView.setText(ratingString);
    }
}