package edu.gaTech.teamElevenProject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import org.json.JSONObject;

import java.util.List;

/**
 * ItemListActivity displays the lists of movies that a user searched for.
 */

public class ItemListActivity extends AppCompatActivity {

    /**
     * List of movies
     */
    private List<Movie> movies;

    /**
     * RequestQueue used in ItemListActivity
     */
    private RequestQueue queue;

    /**
     * String that contains the name of the movie
     */
    private String combinedTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final MyClickHandler clickHandler = new MyClickHandler();
        queue = Volley.newRequestQueue(this);
        final ListView lv = (ListView) findViewById(R.id.content);
        movies = (List<Movie>) getIntent().getSerializableExtra("movies");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_name);


        for (final Movie s : movies) {
            arrayAdapter.add(s.toString());
        }

        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(clickHandler);
    }
    /**
     * Changes the view
     * @param movie the selected movie
     */
    private void changeView(Movie movie) {
        final Intent intent = new Intent(getBaseContext(),
                MovieDetailDisplay.class);
        intent.putExtra("movie", movie);
        intent.putExtra("key", combinedTerms);
        startActivity(intent);
    }

    /**
     * Class to handle clicks in the list of items.
     */
    class MyClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent,
                                View view,
                                int position,
                                long id) {
            final Movie selectedMovie = movies.get(position);
            final MyResponseHandler responseHandler = new MyResponseHandler(selectedMovie);
            final String title = selectedMovie.getName();
            combinedTerms = title.replace(' ', '+');
            final String url = "http://www.omdbapi.com/?t="
                    + combinedTerms + "&type=movie&y=&plot=short&r=json";
            final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request
                    .Method.GET, url, null, responseHandler,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            final String response = "JSon Request Failed!!";
                        }
                    });
            //this actually queues up the async response with Volley
            queue.add(jsObjRequest);
        }
    }

    /**
     * Class to handle responses in the list of items.
     */
    class MyResponseHandler implements Response.Listener<JSONObject> {

        /**
         * Movie that is selected
         */
        private Movie selectedMovie;

        /**
         * Constructor for a response handler
         * @param movie movie that is selected
         */
        public MyResponseHandler(Movie movie) {
            selectedMovie = movie;
        }

        @Override
        public void onResponse(JSONObject resp) {
            final  JSONObject obj1 = resp;
            if (obj1 != null) {
                assert obj1 != null;
                selectedMovie.setName(obj1.optString("Title"));
                selectedMovie.setGenre(obj1.optString("Genre"));
                selectedMovie.setLength(obj1.optString("Runtime"));
                selectedMovie.setReleased(obj1.optString("Released"));
                selectedMovie.setActors(obj1.optString("Actors"));
                if (!Movies.ITEM_MAP.containsKey(combinedTerms)) {
                    Movies.ITEM_MAP.put(combinedTerms, selectedMovie);
                }
                final Movie s = Movies.ITEM_MAP.get(combinedTerms);
                changeView(s);
            } else {
                final String text = "No Movies with the "
                    + "search term were found!";
                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;
                final Toast t = Toast.makeText(context, text, duration);
                t.show();
            }
        }
    }
}
