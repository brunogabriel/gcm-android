package com.example.brunogabriel.nativegcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.brunogabriel.nativegcm.util.RegistrationIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by brunogabriel on 1/21/16.
 */
public class MainActivity extends AppCompatActivity {

    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static MainActivity instance;

    // UI element
    private TextView mUniqueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;
        mUniqueId = (TextView) findViewById(R.id.uniqueId);

        if(isEnablePlayService()) {
            // Register GCM with this app
            Intent mIntent = new Intent(this, RegistrationIntentService.class);
            startService(mIntent);
        }


    }

    /**
     * Check if Google Play Service is enable
     * @return true or false
     */
    private boolean isEnablePlayService() {
        GoogleApiAvailability mGoogleAPI = GoogleApiAvailability.getInstance();
        int mResultCodeAPI = mGoogleAPI.isGooglePlayServicesAvailable(this);
        if(mResultCodeAPI!= ConnectionResult.SUCCESS) {
            if (mGoogleAPI.isUserResolvableError(mResultCodeAPI)) {
                mGoogleAPI.getErrorDialog(this, mResultCodeAPI, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                // device not support Google Play Services
                finish();
            }
            return false;
        }

        return true;
    }

    /**
     * Plot id in screen
     * @param mRegisterId
     */
    public static void updateCredentials(final String mRegisterId) {
        try {
            if (instance !=null) {
                instance.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mRegisterId == null || mRegisterId.isEmpty()) {
                            instance.mUniqueId.setText("Identifier empty.");
                        } else {
                            instance.mUniqueId.setText("" + mRegisterId);
                        }

                    }
                });
            }
        } catch (Exception e) {
            Log.d("MainActivity", "Failed to update UI Thread on MainActivity: " + e.getMessage());
        }

    }

}
