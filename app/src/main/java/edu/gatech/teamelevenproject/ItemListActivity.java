package edu.gatech.teamelevenproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemListActivity displays the lists of movies that a user searched for.
 */

public class ItemListActivity extends AppCompatActivity {

    private List<Movie> movies;
    private ListView lv;
    private RequestQueue queue;
    private String response;
    String combinedterms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        queue = Volley.newRequestQueue(this);
        lv = (ListView) findViewById(R.id.content);
        movies = (List<Movie>) getIntent().getSerializableExtra("movies");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_name);


        for (Movie s : movies) {
            arrayAdapter.add(s.toString());
        }

        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Sets what happens when an item in the listview is clicked
             * @param parent the view that the listview is using
             * @param view the current view
             * @param position position on the array
             * @param id id of the each item
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Movie selectedMovie = movies.get(position);
                String title = selectedMovie.getName();
                combinedterms = title.replace(' ', '+');
                String url = "http://www.omdbapi.com/?t=" + combinedterms + "&type=movie&y=&plot=short&r=json";
                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject resp) {
                                String key = combinedterms;
                                JSONObject obj1 = resp;
                                if (obj1 != null) {
                                    assert obj1 != null;
                                    selectedMovie.setName(obj1.optString("Title"));
                                    selectedMovie.setGenre(obj1.optString("Genre"));
                                    selectedMovie.setLength(obj1.optString("Runtime"));
                                    selectedMovie.setReleased(obj1.optString("Released"));
                                    selectedMovie.setActors(obj1.optString("Actors"));
                                    if (!Movies.ITEM_MAP.containsKey(combinedterms)) {
                                        Movies.ITEM_MAP.put(combinedterms, selectedMovie);
                                    }
                                    Movie s = Movies.ITEM_MAP.get(combinedterms);
                                    changeView(s);
                                } else {
                                    String text = "No Movies with the search term were found!";
                                    Context context = getApplicationContext();
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast t = Toast.makeText(context, text, duration);
                                    t.show();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                String response = "JSon Request Failed!!";
                            }
                        });
                //this actually queues up the async response with Volley
                queue.add(jsObjRequest);
            }


            /**
             * Changes the view
             * @param movie the selected movie
             */
            private void changeView(Movie movie) {
                Intent intent = new Intent(getBaseContext(), movieDetailDisplay.class);
                intent.putExtra("movie", movie);
                intent.putExtra("key", combinedterms);
                startActivity(intent);
            }
        });
    }
}
