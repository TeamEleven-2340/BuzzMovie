package edu.gatech.teamelevenproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Class that allows the user to edit his or her profile, which contains the users email,
 * username, and full name.
 */

public class EditProfileActivity extends AppCompatActivity {

    private String email = "";
    private String name = "";
    private String fullname = "";
    private String major = "";
    private String interest = "";
    EditText nameEditText;
    EditText emailEditText;
    EditText majorEditText;
    EditText interestEditText;
    UserManagementFacade afepa = new UserManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView usernamedisplay = (TextView) findViewById(R.id.usernamedisplayText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        majorEditText = (EditText) findViewById(R.id.majorEditText);
        interestEditText = (EditText) findViewById(R.id.interestEditText);
        email = afepa.getCurrentUsername().email;
        name = afepa.getCurrentUsername().name;
        fullname = afepa.getCurrentUsername().fullname;
        interest = afepa.getCurrentUsername().interest;
        major = afepa.getCurrentUsername().major;
        emailEditText.setText(email);
        usernamedisplay.setText(name);
        nameEditText.setText(fullname);
        majorEditText.setText(major);
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
        int id = item.getItemId();
        if (id == R.id.done) {
            afepa.getCurrentUsername().fullname = nameEditText.getText().toString();
            afepa.getCurrentUsername().interest = interestEditText.getText().toString();
            afepa.getCurrentUsername().major = majorEditText.getText().toString();
            if (!(emailEditText.getText().toString().contains("@"))
                    && !(emailEditText.getText().toString().equals(afepa.getCurrentUsername().email))) {
                String text = "Not a valid email address!";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast t = Toast.makeText(context, text, duration);
                t.show();
            } else {
                afepa.getCurrentUsername().email = emailEditText.getText().toString();
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}