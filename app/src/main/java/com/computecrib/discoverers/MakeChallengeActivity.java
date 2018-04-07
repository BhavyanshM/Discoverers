package com.computecrib.discoverers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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

    // Access a Cloud Firestore instance from your Activity
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_challenge);

        db = FirebaseFirestore.getInstance();

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
                // Create a new user with a first and last name
                Map<String, Object> challenge = new HashMap<>();
                challenge.put("title", title);
                challenge.put("desc", desc);
                challenge.put("hint", hint);
                challenge.put("score", score);

// Add a new document with a generated ID
                db.collection("challenges")
                        .add(challenge)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(MakeChallengeActivity.this, "Submitted Challenge", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(MakeChallengeActivity.this, MainActivity.class);
                                startActivity(intent);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                                Toast.makeText(MakeChallengeActivity.this, "Error Ocurred.", Toast.LENGTH_SHORT);

                            }
                        });

            }
        });

    }

}
