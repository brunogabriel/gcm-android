package com.example.brunogabriel.nativegcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.brunogabriel.nativegcm.util.RegistrationIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by brunogabriel on 1/21/16.
 */
public class MainActivity extends AppCompatActivity {

    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
