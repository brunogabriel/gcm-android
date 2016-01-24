package com.example.brunogabriel.nativegcm.util;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.brunogabriel.nativegcm.MainActivity;
import com.example.brunogabriel.nativegcm.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by brunogabriel on 1/21/16.
 */
public class RegistrationIntentService extends IntentService {

    private static final String REGISTRATION_INTENT_SERVICE = "REGISTRATION_INTENT_SERVICE";
    private static final String REGISTRATION_DEBUG = "RegistrationDebug";
    public static final String REGISTRATION_ID_PREFERENCE = "RegistrationIdPreference";
    public static final String REGISTRATION_COMPLETE = "RegistrationComplete";

    public RegistrationIntentService() {
        super(REGISTRATION_INTENT_SERVICE);
    }

    public RegistrationIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Preferences to get registration id from device
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mNextId = mSharedPreferences.getString(REGISTRATION_ID_PREFERENCE, null);
        try {
            InstanceID mInstanceID = InstanceID.getInstance(this);
            String mToken = mInstanceID.getToken(getString(R.string.gcm_default_sender_id),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                executeBusinessRule(mToken);

            if(mNextId!=null && mNextId!=mToken) {
                mSharedPreferences.edit().putString(REGISTRATION_ID_PREFERENCE, mToken).apply();
                mNextId = mToken;
            }

        } catch (Exception e) {
            Log.d(REGISTRATION_DEBUG, "Failed to refresh current token: " + e.getMessage());
        }

        // Complete process
        if(mNextId!=null) {
            Intent mRegisterIntent = new Intent(REGISTRATION_COMPLETE);
            LocalBroadcastManager.getInstance(this).sendBroadcast(mRegisterIntent);
        }
    }

    /**
     * In my case, I only debug token, in real application, its common
     * to send a service to register
     * or save in your local database, use to send a message in chat like whatsapp etc.
     * @param mToken user token register
     */
    public void executeBusinessRule(String mToken) {
        if(mToken!=null && mToken.length()>0) {
            Log.d(REGISTRATION_DEBUG, "Current token: " + mToken);
            MainActivity.updateCredentials(mToken);
        }
    }
}
