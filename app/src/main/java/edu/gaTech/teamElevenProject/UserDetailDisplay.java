package edu.gaTech.teamElevenProject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * UserDetailDisplay handles a user's information, including
 * if it is a banned user or not.
 */
public class UserDetailDisplay extends AppCompatActivity {
    /**
     * ban status of the user
     */
    private String currentBanStatus;

    /**
     * User management facade used in UserDetailDisplay
     */
    private UserManagementFacade afepa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final  DatabaseWrapper dbHelper = new DatabaseWrapper(this, DatabaseWrapper.databaseName);
        final SQLiteDatabase rdb = dbHelper.getReadableDatabase();
        afepa = new UserManager(dbHelper, rdb);
        setContentView(R.layout.activity_user_detail_display);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String username = getIntent().getStringExtra("user");
        final TextView username1 = (TextView) findViewById(R.id.username);
        final TextView lockStatusText = (TextView) findViewById(R.id.lockStatus);
        username1.setText(username);
        final User curr = afepa.findUserById(username);
        lockStatusText.setText(Boolean.toString(curr.getLockStatus()));
        afepa.setCurrentUsername(curr);


        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.banStatus, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(Boolean.
                toString(afepa.getCurrentUsername().getBanStatus())));
        spinner.setOnItemSelectedListener(new AdapterView
                .OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view,
                                       int position,
                                       long id) {
                currentBanStatus = parent
                        .getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentBanStatus = Boolean.toString(afepa.getCurrentUsername().getBanStatus());
            }
        });

        /**
         * This button unlocks a user. It only changes
         * user lock status to false
         */
    }

    /**
     * Unlocks the user.
     * @param v the current view
     */
    public void unlockButton(View v) {
        afepa.setLockStatus(false);
        final Intent i = new Intent(this.getBaseContext(),
                AdminSearch.class);
        startActivity(i);
    }

    /**
     * This button changes a user/s banned status based
     * on what the admin selects
     * @param v current view
     */
    public void buttonClicked(View v) {
        final Boolean setStatus = Boolean
                .parseBoolean(currentBanStatus);
        afepa.setBannedStatus(setStatus);
        final Intent i = new Intent(this.getBaseContext(),
                AdminSearch.class);
        startActivity(i);
    }

}
