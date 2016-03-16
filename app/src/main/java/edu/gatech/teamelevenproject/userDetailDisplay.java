package edu.gatech.teamelevenproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class userDetailDisplay extends AppCompatActivity {
    String currentBanStatus;
    UserManagementFacade afepa = new UserManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String username = getIntent().getStringExtra("user");
        TextView username1 = (TextView) findViewById(R.id.username);
        username1.setText(username);
        User curr = afepa.findUserById(username);
        afepa.setCurrentUsername(curr);


        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.banStatus, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(Boolean.toString(afepa.getCurrentUsername().getBanStatus())));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentBanStatus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentBanStatus = Boolean.toString(afepa.getCurrentUsername().getBanStatus());
            }
        });

    }

    public void ButtonClicked(View v) {
        Boolean setStatus = Boolean.parseBoolean(currentBanStatus);
        afepa.setBannedStatus(setStatus);
        Intent i = new Intent(this.getBaseContext(),AdminSearch.class);
        startActivity(i);
    }

}
