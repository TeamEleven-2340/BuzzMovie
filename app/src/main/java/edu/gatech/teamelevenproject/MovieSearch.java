package edu.gatech.teamelevenproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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


/**
 * Class that defines the selection of movies.
 */
public class MovieSearch extends AppCompatActivity {

    private RequestQueue queue;
    private String response;
    private String searchTerm;
    private EditText searcheditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);
        queue = Volley.newRequestQueue(this);
        searcheditText = ((EditText) findViewById(R.id.searcheditText));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.moviesearchmenu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            Intent back = new Intent(getBaseContext(), MainActivity.class);
            back.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
            startActivity(back);
            String text = "Logout Success!";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(context, text, duration);
            t.show();
        } else if (id == R.id.profile) {
             Intent profile = new Intent (getBaseContext(), ProfileActivity.class);
            startActivity(profile);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method implements volley which allows us to use API into our app. Using the API, if there is an error we show error messages.
     * We also receive search words from the users to search the movies that they want to see.
     * @param view
     */
    public void onGetMovie(View view) {
        searchTerm = searcheditText.getText().toString();
        int count = 1;
        String a = "";
        ArrayList<String> terms = new ArrayList<>();
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
        String combinedterms = terms.get(0);
        if (terms.size() > 1) {
            for (int i = 1; i < terms.size(); i++) {
                combinedterms += "+" + terms.get(i);
            }
        }
        Log.d("EAGEAGEAG", combinedterms);
        String url = "http://www.omdbapi.com/?s=" + combinedterms + "&type=movie&y=&plot=short&r=json";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        //handle a valid response coming back.  Getting this string mainly for debug
                        response = resp.toString();
                        //printing first 500 chars of the response.  Only want to do this for debug
                        //TextView view = (TextView) findViewById(R.id.textView2);
                        //view.setText(response.substring(0, 500));

                        //Now we parse the information.  Looking at the format, everything encapsulated in RestResponse object
                        JSONObject obj1 = null;

                        obj1 = resp;

                        assert obj1 != null;
                        //From that object, we extract the array of actual data labeled result
                        JSONArray array = obj1.optJSONArray("Search");
                        if (array != null) {
                            ArrayList<edu.gatech.teamelevenproject.Movie> movies = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {

                                try {
                                    //for each array element, we have to create an object
                                    JSONObject jsonObject = array.getJSONObject(i);
                                    Movie s = new Movie();
                                    assert jsonObject != null;
                                    s.setName(jsonObject.optString("Title"));
                                    s.setYear(jsonObject.optString("Year"));
                                    //save the object for later
                                    movies.add(s);


                                } catch (JSONException e) {
                                    Log.d("VolleyApp", "Failed to get JSON object");
                                    e.printStackTrace();
                                }
                            }

                            //once we have all data, then go to list screen
                            changeView(movies);
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
                        response = "JSon Request Failed!!";
                        //show error on phone
                        //TextView view = (TextView) findViewById(R.id.textView2);
                        //view.setText(response);
                    }
                });
        //this actually queues up the async response with Volley
        queue.add(jsObjRequest);
    }

    /**
     * Change the view,
     * @param states
     */
    private void changeView(ArrayList<edu.gatech.teamelevenproject.Movie> states) {
        Intent intent = new Intent(this, ItemListActivity.class);
        //this is where we save the info.  note the State object must be Serializable
        intent.putExtra("states", states);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
            startActivity(intent);
            return true;
        }
        return false;
    }
}
