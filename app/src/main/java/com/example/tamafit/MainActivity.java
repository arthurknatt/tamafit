package com.example.tamafit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Defining Permission codes.
    // We can give any value
    // but unique for each permission.
    private static final int ACTIVITY_RECOGNITION_PERMISSION_CODE = 100;
    private static final int REQUEST_OAUTH_REQUEST_CODE = 1;

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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // check permissions on startup
        checkPermission(Manifest.permission.ACTIVITY_RECOGNITION,
                ACTIVITY_RECOGNITION_PERMISSION_CODE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This method sets up our custom logger, which will print all log messages to the device
        // screen, as well as to adb logcat.
        // src: https://github.com/googlearchive/android-fit/blob/master/BasicRecordingApi/app/
        //      src/main/java/com/google/android/gms/fit/samples/basicrecordingapi/MainActivity.java

        FitnessOptions fitnessOptions =
                FitnessOptions.builder().addDataType(DataType.TYPE_ACTIVITY_SAMPLES).build();

        // Check if the user has permissions to talk to Fitness APIs, otherwise authenticate the
        // user and request required permissions.
        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    this,
                    REQUEST_OAUTH_REQUEST_CODE,
                    GoogleSignIn.getLastSignedInAccount(this),
                    fitnessOptions);


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
