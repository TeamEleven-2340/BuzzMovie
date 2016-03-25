package edu.gatech.teamelevenproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminSearch extends AppCompatActivity {
    private ListView lv;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv = (ListView) findViewById(R.id.userList);
        UserManagementFacade um = new UserManager(this);
        final ArrayList<User> userList = um.getUserList();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_name);

        for (User s : userList) {
            if (!s.isAdminStatus()) {
                arrayAdapter.add(s.toStringBannedStatus());
            }
        }
        if (arrayAdapter.isEmpty()) {
            arrayAdapter.add("User List is Empty");
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
                final User clicked = userList.get(position);
                changeView(clicked);
            }


            /**
             * Changes the view
             * @param display the selected movie
             */
            private void changeView(User display) {
                Intent intent = new Intent(getBaseContext(), userDetailDisplay.class);
                intent.putExtra("user", display.name);
                startActivity(intent);
            }
        });


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


