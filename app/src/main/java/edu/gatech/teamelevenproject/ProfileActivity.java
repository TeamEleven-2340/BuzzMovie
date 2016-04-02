package edu.gatech.teamelevenproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * ProfileActivity defines the actions that happen during the visitation of a user's profile.
 */

public class ProfileActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new DatabaseWrapper(this, DatabaseWrapper.DATABASE_NAME);
        rdb = dbHelper.getReadableDatabase();
        final UserManagementFacade ufpa = new UserManager(dbHelper, rdb);
        final TextView namedisplayText = (TextView)findViewById(R.id.namedisplayText);
        final TextView emaildisplayText = (TextView)findViewById(R.id.emaildisplayText);
        final TextView usernamedisplayText = (TextView)findViewById(R.id.usernamedisplayText);
        final TextView interestText = (TextView) findViewById(R.id.interestTextView);
        final TextView majorText = (TextView) findViewById(R.id.majorEditText);
        emaildisplayText.setText(ufpa.getCurrentUsername().getEmail());
        usernamedisplayText.setText(ufpa.getCurrentUsername().getName());
        namedisplayText.setText(ufpa.getCurrentUsername().getFullname());
        interestText.setText(ufpa.getCurrentUsername().getInterest());
        majorText.setText(ufpa.getCurrentUsername().getMajor());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.edit) {
            final Intent intent = new Intent(getBaseContext(), EditProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
