package edu.gatech.teamelevenproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.Map;

public class recommendationActivity extends AppCompatActivity {
    private String major;
    private List<Movie> a;
    private int c;
    private int d;
    private int e;
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
        a = Movies.ITEMS;
        if (major.equals("None")) {
            for (Movie b : a) {
                c++;
                if (b.getPeopleRated() != 0) {
                    arrayAdapter.add("Title: " + b.getName() + "\n" + "Ratings: " + b.getRating());
                }
            }
        } else {
            for (Movie b : a) {
                d++;
                if (b.getRatingByMajors() != null) {
                    e++;
                    if (b.getRatingByMajors().get(major) != null) {
                        c++;
                        arrayAdapter.add(b.toString2(major));
                    }
                }
            }
        }
        Log.d("QWERTY", major);
        Log.d("QWERTY", "" + c);
        Log.d("QWERTY", "" + d);
        Log.d("QWERTY", "" + e);
        listView.setAdapter(arrayAdapter);
    }

}
