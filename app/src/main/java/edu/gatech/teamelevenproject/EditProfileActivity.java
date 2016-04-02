package edu.gatech.teamelevenproject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Class that allows the user to edit his or her profile, which contains the users email,
 * username, and full name.
 */

public class EditProfileActivity extends AppCompatActivity {

    /**
     * major of the user
     */
    private String currentMajorSelected = "";
    /**
     * EditText of the name
     */
    private EditText nameEditText;
    /**
     * EditText of the email
     */
    private EditText emailEditText;
    /**
     * EditText of the interest
     */
    private EditText interestEditText;
    /**
     * User Management Facade used in EditProfileActivity
     */
    private UserManagementFacade afepa;
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
        setContentView(R.layout.activity_edit_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        dbHelper = new DatabaseWrapper(this, DatabaseWrapper.DATABASE_NAME);
        rdb = dbHelper.getReadableDatabase();
        final UserManagementFacade um = new UserManager(dbHelper, rdb);
        afepa = new UserManager(dbHelper, rdb);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.majors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(afepa.getCurrentUsername().getMajor()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentMajorSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentMajorSelected = afepa.getCurrentUsername().getMajor();
            }
        });
        final TextView usernamedisplay = (TextView) findViewById(R.id.usernamedisplayText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        interestEditText = (EditText) findViewById(R.id.interestEditText);
        final String email = afepa.getCurrentUsername().getEmail();
        final String name = afepa.getCurrentUsername().getName();
        final String fullname = afepa.getCurrentUsername().getFullname();
        final String interest = afepa.getCurrentUsername().getInterest();
        emailEditText.setText(email);
        usernamedisplay.setText(name);
        nameEditText.setText(fullname);
        interestEditText.setText(interest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.editprofile_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.done) {
            afepa.getCurrentUsername().setFullname(nameEditText.getText().toString());
            afepa.getCurrentUsername().setInterest(interestEditText.getText().toString());
            afepa.getCurrentUsername().setInterest(currentMajorSelected);
            if (!(emailEditText.getText().toString().contains("@"))
                    && !(emailEditText.getText().toString().equals(afepa.getCurrentUsername().getEmail()))) {
                final String text = "Not a valid email address!";
                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;
                final Toast t = Toast.makeText(context, text, duration);
                t.show();
            } else {
                afepa.getCurrentUsername().setEmail(emailEditText.getText().toString());
                afepa.updateDatabase();
                final Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            final Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
