package edu.gaTech.teamElevenProject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * LoginActivity defines the actions that happen during login.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action
        // bar if it is present.
        getMenuInflater().inflate(R.menu.loginactivity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cancel) {
            final Intent back = new Intent(getBaseContext(),
                    MainActivity.class);
            back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(back);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Defines what happens during a standard login activity
     * when the login button is clicked.
     * @param v the current view
     */
    public void loginButtonClicked(View v) {
        Log.d("LOGIN ACTIVITY", "Login Button Pressed");
        final DatabaseWrapper dbHelper
                = new DatabaseWrapper(this,
                DatabaseWrapper.databaseName);
        final SQLiteDatabase rdb = dbHelper.getReadableDatabase();
        final AuthenticationFacade af
                = new UserManager(dbHelper, rdb);
        final UserManagementFacade uf
                = new UserManager(dbHelper, rdb);
        final EditText nameBox = (EditText) findViewById(R.id.idEditText);
        final EditText passBox = (EditText) findViewById(R.id.passwordEditText);
        CharSequence text;

        if (af.handleLoginRequest(nameBox.getText().toString(),
                passBox.getText().toString())) {
            final User loggedInUser
                    = uf.findUserById(nameBox.getText().toString());
            uf.setCurrentUsername(loggedInUser);
            if (uf.getBannedStatus()) {
                text = "Login Failure! Account is Banned!";
                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;
                final Toast t = Toast.makeText(context, text, duration);
                t.show();
            } else if (uf.getLockStatus()) {
                text = "Login Failure! Account is Locked!";
                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;
                final Toast t = Toast.makeText(context, text, duration);
                t.show();
            } else if (uf.isAdminStatus()) {
                text = "Admin Login Success!";
                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;
                final Toast t = Toast.makeText(context, text, duration);
                t.show();
                final Intent i = new Intent(getBaseContext(),
                        AdminSearch.class);
                startActivity(i);
            } else {
                text = "Login Success!";
                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;
                final Toast t = Toast.makeText(context, text, duration);
                t.show();
                final Intent i = new Intent(getBaseContext(),
                        MovieSearch.class);
                startActivity(i);
            }
        } else {
            text = "Login Failure!";
            final Context context = getApplicationContext();
            final int duration = Toast.LENGTH_SHORT;
            final Toast t = Toast.makeText(context, text, duration);
            t.show();
        }
    }
}
