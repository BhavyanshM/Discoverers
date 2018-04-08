package com.computecrib.discoverers;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

public class ChallengeDetailActivity extends AppCompatActivity {

    // Consts
    private static final int PLACE_PICKER_REQUEST = 102;

    // Widgets
    private TextView mChallengeTitle;
    private TextView mChallengeDesc;
    private TextView mChallengeHint;
    private TextView mChallengeScore;
    private TextView mSelectedLocationName;
    private TextView mSelectedLocationAddress;
    private Button mSubmitButton;
    private Button mSetLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        mChallengeTitle = (TextView) findViewById(R.id.tv_title);
        mChallengeTitle.setText("Title: " + MainActivity.currentChallenge.getTitle());

        mChallengeDesc = (TextView) findViewById(R.id.tv_description);
        mChallengeDesc.setText("Description: " + MainActivity.currentChallenge.getDescription());

        mChallengeHint = (TextView) findViewById(R.id.tv_hint);
        mChallengeHint.setText("Hint: " + MainActivity.currentChallenge.getHint());

        mChallengeScore = (TextView) findViewById(R.id.tv_score);
        mChallengeScore.setText(Integer.toString(MainActivity.currentChallenge.getReward()));

        mSelectedLocationName = (TextView) findViewById(R.id.tv_selected_loc_name);
        mSelectedLocationAddress = (TextView) findViewById(R.id.tv_selected_loc_address);
        mSubmitButton = (Button) findViewById(R.id.bv_submit_challenge);
        mSetLocationButton = (Button) findViewById(R.id.bv_locate);

        mSetLocationButton.setOnClickListener(new View.OnClickListener() {
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
                    startActivityForResult(builder.build(ChallengeDetailActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }


        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("loc_name", mSelectedLocationName.getText().toString());
                returnIntent.putExtra("loc_address", mSelectedLocationAddress.getText().toString());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
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
                mSelectedLocationName.setText(place.getName());
                mSelectedLocationAddress.setText(place.getAddress());
//                places.add(new Place(place.getName().toString(), place.getAddress().toString(), places.size()));
//                adapter.notifyDataSetChanged();
            }
        }
    }
}
