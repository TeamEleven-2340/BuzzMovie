package edu.gaTech.teamElevenProject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
 * MovieSearch defines the selection of movies.
 */
public class MovieSearch extends AppCompatActivity {

    /**
     * RequestQueue sued in this activity
     */
    private RequestQueue queue;

    /**
     * the string that contains the current major
     */
    private String currentMajor;

    /**
     * the editText of the movie search
     */
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);
        queue = Volley.newRequestQueue(this);
        searchEditText = ((EditText) findViewById(R.id.searchEditText));
        final Spinner spinner = (Spinner) findViewById(R.id.majorFilter);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.majorsFilter, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view,
                                       int position,
                                       long id) {
                currentMajor = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.moviesearchmenu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        if (id == R.id.logout) {
            final Intent back = new Intent(getBaseContext(),
                    MainActivity.class);
            back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(back);
            final String text = "Logout Success!";
            final Context context = getApplicationContext();
            final int duration = Toast.LENGTH_SHORT;
            final Toast t = Toast.makeText(context, text, duration);
            t.show();
        } else if (id == R.id.profile) {
            final Intent profile = new Intent(getBaseContext(),
                    ProfileActivity.class);
            startActivity(profile);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method implements volley which allows us to use API
     * into our app. Using the API, if there is an error we show
     * error messages. We also receive search words from the
     * users to search the movies that they want to see.
     * @param view view
     */
    public void onGetMovie(View view) {
        final String searchTerm = searchEditText.getText().toString();
        int count = 1;
        String a = "";
        final ArrayList<String> terms = new ArrayList<>();
        while (count <= searchTerm.length()) {
            for (int i = 0; i < searchTerm.length(); i++) {
                if (searchTerm.charAt(i) != ' ') {
                    a = a + searchTerm.charAt(i);
                    count++;
                } else {
                    terms.add(a);
                    count++;
                    a = "";
                }
            }
        }
        terms.add(a);
        String combinedTerms = terms.get(0);
        if (terms.size() > 1) {
            for (int i = 1; i < terms.size(); i++) {
                combinedTerms += "+" + terms.get(i);
            }
        }
        final String url = "http://www.omdbapi.com/?s="
                + combinedTerms + "&type=movie&y=&plot=short&r=json";
        final MovieResponseHandler responseHandler = new MovieResponseHandler();
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, responseHandler, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        final String response = "JSon Request Failed!!";
                        // show error on phone
                        //TextView view
                        // = (TextView) findViewById(R.id.textView2);
                        //view.setText(response);
                    }
                });
        //this actually queues up the async response with Volley
        queue.add(jsObjRequest);
    }

    /**
     * Change the view,
     * @param movies movies to show
     */
    private void changeView(List<Movie> movies) {
        final Intent intent = new Intent(this, ItemListActivity.class);
        // this is where we save the info
        // note the State object must be Serializable
        intent.putExtra("movies", (ArrayList<Movie>) movies);
        startActivity(intent);
    }

    /**
     * Button to get recommendation from system
     * @param view Current view
     */
    public void recommendButtonClicked(View view) {
        final Intent intent = new Intent(this, RecommendationActivity.class);
        intent.putExtra("major", currentMajor);
        final DatabaseWrapper dbHelper = new DatabaseWrapper(this,
                DatabaseWrapper.databaseMovieName);
        final SQLiteDatabase rdb = dbHelper.getReadableDatabase();
        final List<Movie> movieList = Movies.getMovieList(rdb);
        boolean a = false;
        if (movieList.size() != 0) {
            if (!"None".equals(currentMajor)) {
                for (int i = 0; i < movieList.size(); i++) {
                    if (movieList.get(i).getPeopleByMajors()
                            .get(currentMajor) != 0) {
                        a = true;
                    }
                }
            } else {
                for (int i = 0; i < movieList.size(); i++) {
                    if (movieList.get(i).getPeopleRated() != 0) {
                        a = true;
                    }
                }
            }
        }
        if (a) {
            startActivity(intent);
        } else {
            final String text = "No recommendation right now!";
            final Context context = getApplicationContext();
            final int duration = Toast.LENGTH_SHORT;
            final Toast t = Toast.makeText(context, text, duration);
            t.show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final Intent intent = new Intent(getBaseContext(),
                    MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * Class to responses in the list of movies.
     */
    class MovieResponseHandler implements Response.Listener<JSONObject> {

        @Override
        public void onResponse(JSONObject resp) {
            JSONObject obj1 = null;

            obj1 = resp;

            assert obj1 != null;
            // From that object, we extract the array of actual
            // data labeled result
            final JSONArray array = obj1.optJSONArray("Search");
            if (array != null) {
                final ArrayList<edu.gaTech.teamElevenProject.Movie> movies = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {

                    try {
                        //for each array element, we have to create an object
                        final JSONObject jsonObject = array.getJSONObject(i);
                        final Movie s = new Movie();
                        assert jsonObject != null;
                        s.setName(jsonObject.optString("Title"));
                        s.setYear(jsonObject.optString("Year"));
                        //save the object for later
                        movies.add(s);
                        if (!Movies.ITEMS.contains(s)) {
                            Movies.ITEMS.add(s);
                        }


                    } catch (JSONException e) {
                        Log.d("VolleyApp", "Failed to get JSON object");
                    }
                }

                //once we have all data, then go to list screen
                changeView(movies);
            } else {
                final String text = "No Movies with the search "
                    + "term were found!";
                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;
                final Toast t = Toast.makeText(context, text, duration);
                t.show();
            }
        }
    }
}
