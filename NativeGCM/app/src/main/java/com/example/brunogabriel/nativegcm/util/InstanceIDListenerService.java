package com.example.brunogabriel.nativegcm.util;

import android.content.Intent;

/**
 * Created by brunogabriel on 1/21/16.
 */
public class InstanceIDListenerService extends com.google.android.gms.iid.InstanceIDListenerService {

    /**
     * This will be called when InstanceId is changed/updated (e.g. security). The provider
     * initialize this functionality.
     */
    @Override
    public void onTokenRefresh() {
        Intent mIntent = new Intent(this, RegistrationIntentService.class);
        startService(mIntent);
    }
}
