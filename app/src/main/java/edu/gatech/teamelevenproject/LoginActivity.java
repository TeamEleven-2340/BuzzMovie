package edu.gatech.teamelevenproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loginactivity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cancel) {
            Intent back = new Intent(getBaseContext(), MainActivity.class);
            back.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
            startActivity(back);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Defines what happens during a standard login activity when the login button is clicked.
     * @param v the current view
     */
    public void LoginButtonClicked(View v) {
        Log.d("LOGIN ACTIVITY", "Login Button Pressed");
        AuthenticationFacade af = new UserManager();
        UserManagementFacade uf = new UserManager();
        EditText nameBox = (EditText) findViewById(R.id.idEditText);
        EditText passBox = (EditText) findViewById(R.id.passwordEditText);
        CharSequence text;

        if (af.handleLoginRequest(nameBox.getText().toString(), passBox.getText().toString())) {
            User loggedinUser = uf.findUserById(nameBox.getText().toString());
            uf.setCurrentUsername(loggedinUser);
            if (uf.getCurrentUsername().banStatus == true) {
                text = "Login Failure! Account is Banned!";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast t = Toast.makeText(context, text, duration);
                t.show();
            } else if (uf.getCurrentUsername().isAdminStatus() == true) {
                text = "Admin Login Success!";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast t = Toast.makeText(context, text, duration);
                t.show();
                Intent i = new Intent(getBaseContext(), AdminSearch.class);
                startActivity(i);
            } else {
                text = "Login Success!";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast t = Toast.makeText(context, text, duration);
                t.show();
                Intent i = new Intent(getBaseContext(), MovieSearch.class);
                startActivity(i);
            }
        } else {
            text = "Login Failure!";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(context, text, duration);
            t.show();
        }
    }
}
