package edu.gatech.teamelevenproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void OnRegisterClicked(View v) {
        EditText registerNameBox = (EditText) findViewById(R.id.IDEditText);
        EditText registerPassBox = (EditText) findViewById(R.id.passwordEditText);
        UserManagementFacade rg = new UserManager();
        CharSequence text;
        if (registerPassBox.getText().toString().equals("") || registerNameBox.getText().toString().equals("")) {
            text = "No Username or Password Entered!";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(context, text, duration);
            t.show();
        }
        if (rg.handleRegisterRequest(registerNameBox.getText().toString(), registerPassBox.getText().toString())) {

            text = "Register Success!";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(context, text, duration);
            t.show();
            rg.addUser(registerNameBox.getText().toString(),registerPassBox.getText().toString());
            Intent i = new Intent(getBaseContext(), MovieSelector.class);
            startActivity(i);

        } else {
            text = "User already exists!";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(context, text, duration);
            t.show();
        }
    }
}
