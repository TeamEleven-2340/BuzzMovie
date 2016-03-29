package edu.gatech.teamelevenproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class recommendationActivity extends AppCompatActivity {
    private String major;
    private List<Movie> a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView listView = (ListView)findViewById(R.id.recListView);
        major = (String) getIntent().getSerializableExtra("major");
        Log.d("QWERTY", (String) getIntent().getSerializableExtra("major"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_name);
        a = Movies.getMovieList(this);
        if (major.equals("None")) {
            for (Movie b : a) {
                if (b.getPeopleRated() != 0) {
                    arrayAdapter.add("Title: " + b.getName() + "\n" + "Ratings: " + b.getRating());
                }
            }
        } else {
            for (Movie b : a) {
                if (b.getRatingByMajors() != null) {
                    if (b.getPeopleByMajors().get(major) != 0) {
                        arrayAdapter.add(b.toString2(major));
                    }
                }
            }
        }
        listView.setAdapter(arrayAdapter);
    }
}
