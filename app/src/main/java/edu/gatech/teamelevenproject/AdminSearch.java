package edu.gatech.teamelevenproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminSearch extends AppCompatActivity {
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
        setContentView(R.layout.activity_admin_search);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView lv = (ListView) findViewById(R.id.userList);
        dbHelper = new DatabaseWrapper(this, DatabaseWrapper.DATABASE_NAME);
        rdb = dbHelper.getReadableDatabase();
        final UserManagementFacade um = new UserManager(dbHelper, rdb);
        final ArrayList<User> userList = um.getUserList();
        final ArrayList<User> usersToRemove = new ArrayList<User>();
        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, R.layout.item_name);

        for (final User s : userList) {
            if (!s.isAdminStatus()) {
                arrayAdapter.add(s.toStringBannedStatus());
            } else {
                usersToRemove.add(s);
            }
        }
        userList.removeAll(usersToRemove);
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
                final Intent intent = new Intent(getBaseContext(), UserDetailDisplay.class);
                intent.putExtra("user", display.getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            final Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
            startActivity(intent);
            return true;
        }
        return false;
    }
}


