package com.example.tamafit;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.ApiException;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;


import android.os.Bundle;


import android.os.Build;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.ApiException;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import com.google.android.gms.common.SignInButton;

import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

import android.util.Log;
import android.widget.ImageButton;
import java.text.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    GoogleSignInOptions gso;

    GoogleSignInClient mGoogleSignInClient;

    //signInButton.setSize(SignInButton.SIZE_STANDARD);

    //SignInButton ib1 = (SignInButton)findViewById(R.id.sign_in_button).setOnClickListener(this);
    static final int RC_SIGN_IN = 1;

    private static final int ACTIVITY_RECOGNITION_PERMISSION_CODE = 100;

    // Function to request and check permission for google fit activity data collection
    // source: https://www.geeksforgeeks.org/android-how-to-request-permissions-in-android-application/

    //signInButton.setSize(SignInButton.SIZE_STANDARD);

    //SignInButton ib1 = (SignInButton)findViewById(R.id.sign_in_button).setOnClickListener(this);

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        /*
        if (account != null){
            System.out.println("already signed up yussssssssssssss");
            checkPermission(Manifest.permission.ACTIVITY_RECOGNITION,
                    ACTIVITY_RECOGNITION_PERMISSION_CODE);
        }

         */

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        //Configure sign-in to request the user's ID, email address, and basic
        //// profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        signInButton.setOnClickListener(this);


        //// Build a GoogleSignInClient with the options specified by gso.


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public void onClick(View view) {


        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();



        if (view.getId() == R.id.sign_in_button){
            //System.out.print("clicked!!!!");
            signIn();

        }

        /*
        switch (view.getId() == ) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }

         */


    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {

            int a = 0;
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            System.out.println("YEEET SUCCESSS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");

            //check permission
            checkPermission(Manifest.permission.ACTIVITY_RECOGNITION,
                    ACTIVITY_RECOGNITION_PERMISSION_CODE);

            //go to homepage
            setContentView(R.layout.homepage);

            //set name
            TextView fl1 = (TextView) findViewById(R.id.put_step_count);
            fl1.setText("WOWOOWOWOWO");

            ImageButton sprite = (ImageButton) findViewById(R.id.put_sprite_here);


            if (a == 0){
                sprite.setImageResource(R.drawable.raw_egg);
            }

            if (a == 10){
                sprite.setImageResource(R.drawable.cooked_egg);
            }



        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("replace with TAG" , "signInResult:failed code=" + e.getStatusCode());
            setContentView(R.layout.homepage);
            TextView fl1 = (TextView) findViewById(R.id.put_step_count);
            fl1.setText("WOWOOWOWOWO");
        }
    }

    // Function to request and check permission for google fit activity data collection
    // source: https://www.geeksforgeeks.org/android-how-to-request-permissions-in-android-application/
    public void checkPermission(String permission, int requestCode)
    {

        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                MainActivity.this,
                permission)
                == PackageManager.PERMISSION_DENIED) {
            // if not granted, request permissions
            ActivityCompat
                    .requestPermissions(
                            MainActivity.this,
                            new String[] { permission },
                            requestCode);
        }
        else {
            Toast.makeText(MainActivity.this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.
    // source: https://www.geeksforgeeks.org/android-how-to-request-permissions-in-android-application/
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == ACTIVITY_RECOGNITION_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,
                        "Activity Recognition Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(MainActivity.this,
                        "Activity Recognition Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            // if the request code isn't recognized
        } else {
            Toast.makeText(MainActivity.this,
                    "Err: Request Code not recognized",
                    Toast.LENGTH_SHORT)
                    .show();
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


}
