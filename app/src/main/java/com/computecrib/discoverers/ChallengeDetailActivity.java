package com.computecrib.discoverers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ChallengeDetailActivity extends AppCompatActivity {

    private Button mSubmitButton;
    private Button mSetLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        mSubmitButton = (Button) findViewById(R.id.bv_submit_challenge);
        mSetLocationButton = (Button) findViewById(R.id.bv_locate);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "CHALLENGE WON");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }

}
