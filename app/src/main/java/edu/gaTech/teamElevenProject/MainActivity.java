package edu.gaTech.teamElevenProject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Main activity class that handles clicking the
 * login and register buttons on the main screen.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DatabaseWrapper dbHelper = new DatabaseWrapper(this,
                DatabaseWrapper.DATABASENAME);
        final SQLiteDatabase rdb = dbHelper.getReadableDatabase();
        final UserManagementFacade um = new UserManager(dbHelper, rdb);
        if (um.findUserById("test") == null) {
            um.addAdmin("test", "pass");
        }
        if (um.findUserById("locked") == null) {
            Log.d("qweru", "qwertu");
            um.addUser("locked", "lock");
            um.setLockStatus(true);
        }
        if (um.findUserById("banned") == null) {
            Log.d("qwertyu", "qwertyu");
            um.addUser("banned", "ban");
            um.setBannedStatus(true);
        }

        //User user1 = um.findUserById("test");
        //um.setCurrentUsername(user1);
    }

    /**
     * Creates an instance of the login activity.
     * @param view the current view
     */
    public void onLoginButtonClicked(View view) {
        final Intent intent = new Intent(getBaseContext(),
                LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Creates an instance of the registration activity.
     * @param view the current view
     */
    public void onRegistrationButtonClicked(View view) {
        final Intent registerIntent = new Intent(getBaseContext(), RegisterActivity.class);
        startActivity(registerIntent);
    }
}