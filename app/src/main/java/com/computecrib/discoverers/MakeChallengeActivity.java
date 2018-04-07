package com.computecrib.discoverers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MakeChallengeActivity extends AppCompatActivity
{

    // Consts
    private static final String TAG = "MakeChallengeActivity";

    // Widgets
    private EditText mChallengeTitle;
    private EditText mChallengeDesc;
    private EditText mChallengeHint;
    private EditText mChallengeScore;
    private Button mChallengeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_challenge);

        mChallengeTitle = (EditText) findViewById(R.id.et_challenge_title);
        mChallengeDesc = (EditText) findViewById(R.id.et_challenge_description);
        mChallengeHint = (EditText) findViewById(R.id.et_challenge_hint);
        mChallengeScore = (EditText) findViewById(R.id.et_challenge_score);

        mChallengeButton = (Button) findViewById(R.id.challenge_btn);
        mChallengeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String title = mChallengeTitle.getText().toString();
                String desc = mChallengeDesc.getText().toString();
                String hint = mChallengeHint.getText().toString();
                int score = Integer.parseInt(mChallengeScore.getText().toString());

                Log.d(TAG, "onClick: Title: "  + title + " Desc: " + desc + " Hint: " + hint + " Score: " + score);
            }
        });
    }

}
