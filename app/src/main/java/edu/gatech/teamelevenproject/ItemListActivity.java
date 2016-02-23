package edu.gatech.teamelevenproject;

import android.database.Cursor;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemListActivity which displays the lists of movies that a user searched for
 */
public class ItemListActivity extends AppCompatActivity {

    private List<Movie> states;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.content);
        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("foo");
        your_array_list.add("bar");
        states = (List<Movie>) getIntent().getSerializableExtra("states");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_name);


        for (Movie s : states) {
            States.addItem(s);
            arrayAdapter.add(s.toString());
        }
        lv.setAdapter(arrayAdapter);

    }






}
