package edu.gatech.teamelevenproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import edu.gatech.teamelevenproject.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserManagementFacade um = new UserManager();
        um.addUser("test", "pass");
    }

    public void onLoginButtonClicked(View view) {
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }
    public void onRegistrationButtonClicked(View view) {
        Intent registerintent = new Intent (getBaseContext(), RegisterActivity.class);
        startActivity(registerintent);
    }
}