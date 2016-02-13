package edu.gatech.teamelevenproject;

import android.content.Context;
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
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {

    private String email = "";
    private String name = "";
    private String fullname = "";
    EditText nameEditText;
    EditText emailEditText;
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
        email = afepa.getCurrentUsername().email;
        name =afepa.getCurrentUsername().name;
        fullname = afepa.getCurrentUsername().fullname;
        emailEditText.setText(email);
        usernamedisplay.setText(name);
        nameEditText.setText(fullname);
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
            if (emailEditText.getText().toString().contains("@")) {
                afepa.getCurrentUsername().email = emailEditText.getText().toString();
            } else {
                String text = "Not a valid email address!";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast t = Toast.makeText(context, text, duration);
                t.show();
            }
            afepa.getCurrentUsername().email = nameEditText.getText().toString();
            Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
            startActivity(intent);
        }
        if (id == R.id.cancel) {
            Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    }
