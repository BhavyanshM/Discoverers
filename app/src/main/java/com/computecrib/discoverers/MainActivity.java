package com.computecrib.discoverers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

//import com.google.android.gms.auth.api.Auth;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.auth.api.signin.GoogleSignInResult;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
@SuppressLint("RestrictedApi")
public class MainActivity extends AppCompatActivity {
    static Challenge currentChallenge;

    private static final String TAG = "MainActivity";
    private static final int PLACE_PICKER_REQUEST = 102;
    private static final int CHALLENGE_SOLUTION_REQUEST = 101;
    private static final int RC_SIGN_IN = 9001;
    private static final int RC_UNUSED = 49001;
    private static final int RC_MAKE_CHALLENGE = 4001;

    public static Boolean successful = null;
    private Button okayButton;
//    private GoogleSignInClient mGoogleSignInClient;
//    private AchievementsClient mAchievementsClient;
//    private LeaderboardsClient mLeaderboardsClient;
//    private EventsClient mEventsClient;
//    private PlayersClient mPlayersClient;
//    private SignInButton mSignInButton;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        okayButton = (Button) findViewById(R.id.bv_clear_frag);
//        okayButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                final View fadeBackground = findViewById(R.id.fadeBackground);
//                fadeBackground.setVisibility(View.INVISIBLE);
//                //fadeBackground.animate().alpha(0.0f);
//                MainActivity.successful = false;
//                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.suc_frag);
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .remove(fragment).commit();
//                okayButton.setVisibility(View.INVISIBLE);
//            }
//        });

        final FloatingActionsMenu menuFab = (FloatingActionsMenu) findViewById(R.id.fab_menu);

        FloatingActionButton basicChallengeBtn = (FloatingActionButton) findViewById(R.id.add_basic_challenge);
        basicChallengeBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, MakeChallengeActivity.class);
                startActivityForResult(intent, RC_MAKE_CHALLENGE);
                menuFab.collapseImmediately();
            }
        });

        FloatingActionButton seqChallengeBtn = (FloatingActionButton) findViewById(R.id.add_seq_challenge);
        seqChallengeBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this, "Sequence Challenge", Toast.LENGTH_SHORT).show();
                menuFab.collapseImmediately();
            }
        });

        FloatingActionButton quizChallengeBtn = (FloatingActionButton) findViewById(R.id.add_quiz_challenge);
        seqChallengeBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this, "Quiz Challenge", Toast.LENGTH_SHORT).show();
                menuFab.collapseImmediately();
            }
        });

    //    menuFab.addButton(basicChallengeBtn);
        //menuFab.addButton(seqChallengeBtn);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MakeChallengeActivity.class);
                startActivityForResult(intent, RC_MAKE_CHALLENGE);
            }
        });*/

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new GamePagerAdapter(getSupportFragmentManager()));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

//        mGoogleSignInClient = GoogleSignIn.getClient(this,
//                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());
//
//        mSignInButton = (SignInButton) findViewById(R.id.bv_sign_in_button);
//        mSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startSignInIntent();
//            }
//        });

    }


//    @SuppressLint("RestrictedApi")
//    public boolean isSignedIn(){
//        return GoogleSignIn.getLastSignedInAccount(this) != null;
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHALLENGE_SOLUTION_REQUEST) {
            if(resultCode == Activity.RESULT_OK){
                String loc_name = data.getStringExtra("loc_name");
                String loc_address = data.getStringExtra("loc_address");

                if (loc_name.equals(currentChallenge.getName())
                        && loc_address.equals(currentChallenge.getAddress())) {
                    Toast.makeText(this, "CORRECT", Toast.LENGTH_LONG).show();

                    successful = true;
                }
                else {
                    Toast.makeText(this, "INCORRECT", Toast.LENGTH_LONG).show();
                    successful = false;
                }

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }else if (requestCode == RC_MAKE_CHALLENGE) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//
//    @SuppressLint("RestrictedApi")
//    private void signInSilently() {
//        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
//                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
//        signInClient.silentSignIn().addOnCompleteListener(this,
//                new OnCompleteListener<GoogleSignInAccount>() {
//                    @Override
//                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
//                        if (task.isSuccessful()) {
//                            // The signed in account is stored in the task's result.
//                            GoogleSignInAccount signedInAccount = task.getResult();
//                        } else {
//                            // Player will need to sign-in explicitly using via UI
//                        }
//                    }
//                });
//    }
//
//
//    private void startSignInIntent() {
//        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
//                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
//        Intent intent = signInClient.getSignInIntent();
//        startActivityForResult(intent, RC_SIGN_IN);
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            if (result.isSuccess()) {
//                // The signed in account is stored in the result.
//                GoogleSignInAccount signedInAccount = result.getSignInAccount();
//            } else {
//                String message = result.getStatus().getStatusMessage();
//                if (message == null || message.isEmpty()) {
//                    message = getString(R.string.signin_other_error);
//                }
//                new AlertDialog.Builder(this).setMessage(message)
//                        .setNeutralButton(android.R.string.ok, null).show();
//            }
//        }
//    }
//
    @Override
    protected void onResume() {
        super.onResume();

        if (successful != null)
        {
            if (successful)
            {

                Log.d(TAG, "onResume: DISPLAYING SUCCESS FRAG");
                SuccessFragment successFragment = new SuccessFragment();
                // Supply num input as an argument.
                Bundle args = new Bundle();
                args.putBoolean("successful", true);
                successFragment.setArguments(args);
                successFragment.show(getSupportFragmentManager(), "fragment_edit_name");
//            final View fadeBackground = findViewById(R.id.fadeBackground);
//            fadeBackground.setVisibility(View.VISIBLE);
//            fadeBackground.animate().alpha(0.5f);
//
//            okayButton.setVisibility(View.VISIBLE);
//            // Create a new Fragment to be placed in the activity layout
//            SuccessFragment successFragment = new SuccessFragment();
//
//            // In case this activity was started with special instructions from an
//            // Intent, pass the Intent's extras to the fragment as arguments
//
//            // Add the fragment to the 'fragment_container' FrameLayout
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container, successFragment).commitAllowingStateLoss();
            } else if (!successful)
            {

                SuccessFragment successFragment = new SuccessFragment();
                // Supply num input as an argument.
                Bundle args = new Bundle();
                args.putBoolean("successful", false);
                successFragment.setArguments(args);
                successFragment.show(getSupportFragmentManager(), "fragment_edit_name");

            }
        }
//        signInSilently();
    }
}
