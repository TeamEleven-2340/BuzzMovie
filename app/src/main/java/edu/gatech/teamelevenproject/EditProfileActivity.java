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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
    private String currentMajorSelected = "";
    EditText nameEditText;
    EditText emailEditText;
    EditText majorEditText;
    EditText interestEditText;
    UserManagementFacade afepa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        afepa = new UserManager(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.majors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(afepa.getCurrentUsername().major));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentMajorSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentMajorSelected = afepa.getCurrentUsername().major;
            }
        });
        TextView usernamedisplay = (TextView) findViewById(R.id.usernamedisplayText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        interestEditText = (EditText) findViewById(R.id.interestEditText);
        email = afepa.getCurrentUsername().email;
        name = afepa.getCurrentUsername().name;
        fullname = afepa.getCurrentUsername().fullname;
        interest = afepa.getCurrentUsername().interest;
        major = afepa.getCurrentUsername().major;
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
        int id = item.getItemId();
        if (id == R.id.done) {
            afepa.getCurrentUsername().fullname = nameEditText.getText().toString();
            afepa.getCurrentUsername().interest = interestEditText.getText().toString();
            afepa.getCurrentUsername().major = currentMajorSelected;
            if (!(emailEditText.getText().toString().contains("@"))
                    && !(emailEditText.getText().toString().equals(afepa.getCurrentUsername().email))) {
                String text = "Not a valid email address!";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast t = Toast.makeText(context, text, duration);
                t.show();
            } else {
                afepa.getCurrentUsername().email = emailEditText.getText().toString();
                afepa.updateDatabase();
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
