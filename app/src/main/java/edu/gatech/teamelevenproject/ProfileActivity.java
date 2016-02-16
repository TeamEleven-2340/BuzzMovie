package edu.gatech.teamelevenproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Class that defines the actions that happen during the visitation of a user's profile.
 */

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UserManagementFacade ufpa = new UserManager();
        TextView namedisplayText = (TextView)findViewById(R.id.namedisplayText);
        TextView emaildisplayText = (TextView)findViewById(R.id.emaildisplayText);
        TextView usernamedisplayText = (TextView)findViewById(R.id.usernamedisplayText);
        emaildisplayText.setText(ufpa.getCurrentUsername().email);
        usernamedisplayText.setText(ufpa.getCurrentUsername().name);
        namedisplayText.setText(ufpa.getCurrentUsername().fullname);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.edit) {
            Intent intent = new Intent(getBaseContext(), EditProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
