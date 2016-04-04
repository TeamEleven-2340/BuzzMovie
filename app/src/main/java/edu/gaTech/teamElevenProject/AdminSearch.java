package edu.gaTech.teamElevenProject;

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

/**
 * Class that handles searching of administrators.
 */
public class AdminSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView lv = (ListView) findViewById(R.id.userList);
        final DatabaseWrapper dbHelper = new DatabaseWrapper(this, DatabaseWrapper.DATABASENAME);
        final SQLiteDatabase rdb = dbHelper.getReadableDatabase();
        final UserManagementFacade um = new UserManager(dbHelper, rdb);
        final ArrayList<User> userList = (ArrayList<User>) um.getUserList();
        final ArrayList<User> usersToRemove = new ArrayList<>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_name);

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
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
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
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return false;
    }
}


