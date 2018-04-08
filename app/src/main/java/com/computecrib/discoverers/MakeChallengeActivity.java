package com.computecrib.discoverers;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
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
    private static final int PLACE_PICKER_REQUEST = 102;


    // Widgets
    private EditText mChallengeTitle;
    private EditText mChallengeDesc;
    private EditText mChallengeHint;
    private EditText mChallengeScore;
    private Button mChallengeButton;
    private Button mLocationButton;
    private TextView mLocationName;
    private TextView mLocationAddress;

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
        mLocationButton = (Button) findViewById(R.id.bv_set_location);
        mLocationName = (TextView) findViewById(R.id.tv_location_name);
        mLocationAddress = (TextView) findViewById(R.id.tv_location_address);


        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1);
                    }
                }
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(MakeChallengeActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }

        });

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
                Long tsLong = System.currentTimeMillis()/1000;
                String locationName = mLocationName.getText().toString();
                String locationAddress = mLocationAddress.getText().toString();

                MainActivity.currentChallenge = new Challenge(title,
                        desc,
                        hint,
                        locationName,
                        locationAddress,
                        score);

                Log.d(TAG, "onClick: Title: "  + title + " Desc: " + desc + " Hint: " + hint + " Score: " + score);
                // Create a new user with a first and last name
                Map<String, Object> challenge = new HashMap<>();
                challenge.put("title", title);
                challenge.put("desc", desc);
                challenge.put("hint", hint);
                challenge.put("score", score);
                challenge.put("loc_name", locationName);
                challenge.put("loc_address", locationAddress);
                challenge.put("timestamp", tsLong);

// Add a new document with a generated ID
                db.collection("challenges")
                        .add(challenge)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(MakeChallengeActivity.this, "Submitted Challenge", Toast.LENGTH_SHORT);

                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("result","Submitted Challenge");
                                setResult(Activity.RESULT_OK,returnIntent);
                                finish();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PLACE_PICKER_REQUEST){
            if(resultCode == RESULT_OK){
                com.google.android.gms.location.places.Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                mLocationName.setText(place.getName());
                mLocationAddress.setText(place.getAddress());
//                places.add(new Place(place.getName().toString(), place.getAddress().toString(), places.size()));
//                adapter.notifyDataSetChanged();
            }
        }
    }
}
