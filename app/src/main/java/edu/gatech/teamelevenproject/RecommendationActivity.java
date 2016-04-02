package edu.gatech.teamelevenproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class RecommendationActivity extends AppCompatActivity {
    /**
     * DatabaseWrapper used in this activity
     */
    private DatabaseWrapper dbHelper;
    /**
     * SQLiteDatabase used in this activity
     */
    private SQLiteDatabase rdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView listView = (ListView)findViewById(R.id.recListView);
        final String major = (String) getIntent().getSerializableExtra("major");
        Log.d("QWERTY", (String) getIntent().getSerializableExtra("major"));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_name);
        dbHelper = new DatabaseWrapper(this, DatabaseWrapper.DATABASEMOVIE_NAME);
        rdb = dbHelper.getReadableDatabase();
        final List<Movie> a = Movies.getMovieList(rdb);
        if ("None".equals(major)) {
            for (final Movie b : a) {
                if (b.getPeopleRated() != 0) {
                    arrayAdapter.add("Title: " + b.getName() + "\n" + "Ratings: " + b.getRating());
                }
            }
        } else {
            for (final Movie b : a) {
                if (b.getRatingByMajors() != null && b.getPeopleByMajors().get(major) != 0) {
                    arrayAdapter.add(b.toString2(major));
                }
            }
        }
        listView.setAdapter(arrayAdapter);
    }
}
