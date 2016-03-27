package edu.gatech.teamelevenproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import edu.gatech.teamelevenproject.R;

/**
 * Main activity class.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserManagementFacade um = new UserManager(this);
        if(um.findUserById("test") == null) {
            um.addAdmin("test", "pass");
        }
        if (um.findUserById("locked") == null) {
            Log.d("QWERU", "QWERTU");
            um.addUser("locked", "lock");
            um.setLockStatus(true);
        }
        if (um.findUserById("banned") == null) {
            Log.d("QWERTYU", "QWERTYU");
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
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Creates an instance of the registration activity.
     * @param view the current view
     */
    public void onRegistrationButtonClicked(View view) {
        Intent registerintent = new Intent (getBaseContext(), RegisterActivity.class);
        startActivity(registerintent);
    }
}