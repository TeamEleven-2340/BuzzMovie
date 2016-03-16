package edu.gatech.teamelevenproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        UserManagementFacade um = new UserManager();
        um.addAdmin("test", "pass");
        if (um.findUserById("locked") == null) {
            um.addUser("locked", "lock");
            User user1 = um.findUserById("locked");
            user1.setLockStatus(true);
        }
        if (um.findUserById("banned") == null) {
            um.addUser("banned", "ban");

            User user2 = um.findUserById("banned");
            user2.setBanStatus(true);
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