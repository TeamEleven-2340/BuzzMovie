package edu.gaTech.teamElevenProject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * RegisterActivity defines the actions that happen during registration.
 */

public class RegisterActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_register);
        final Toolbar toolbar
                = (Toolbar) findViewById(R.id.toolbar);
        dbHelper = new DatabaseWrapper(this,
                DatabaseWrapper.databaseName);
        rdb = dbHelper.getReadableDatabase();
        setSupportActionBar(toolbar);
    }

    /**
     * Defines what happens during a standard registration
     * activity when the registration button is clicked.
     * @param v the current view
     */
    public void onRegisterClicked(View v) {
        final EditText registerNameBox
                = (EditText) findViewById(R.id.IDEditText);
        final EditText registerPassBox
                = (EditText) findViewById(R.id.passwordEditText);
        final UserManagementFacade rg
                = new UserManager(dbHelper, rdb);
        CharSequence text;

        if ("".equals(registerPassBox.getText().toString())
                || "".equals(registerNameBox
                .getText().toString())) {
            text = "No Username or Password Entered!";
            final Context context = getApplicationContext();
            final int duration = Toast.LENGTH_SHORT;
            final Toast t = Toast.makeText(context, text, duration);
            t.show();
        } else {
            if (rg.handleRegisterRequest(registerNameBox.getText().toString(),
                    registerPassBox.getText().toString())) {
                text = "Register Success!";
                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;
                final Toast t = Toast.makeText(context, text, duration);
                t.show();
                rg.addUser(registerNameBox.getText().toString(),
                        registerPassBox.getText().toString());
                final Intent intent = new Intent(getBaseContext(),
                        LoginActivity.class);
                startActivity(intent);
            } else {
                text = "User already exists!";
                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;
                final Toast t = Toast.makeText(context, text, duration);
                t.show();
            }
        }
    }
}
