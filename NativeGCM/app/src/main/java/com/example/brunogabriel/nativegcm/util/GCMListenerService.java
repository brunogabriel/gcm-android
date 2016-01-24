package com.example.brunogabriel.nativegcm.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.brunogabriel.nativegcm.MainActivity;
import com.example.brunogabriel.nativegcm.R;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by brunogabriel on 1/21/16.
 */
public class GCMListenerService  extends GcmListenerService {

    private static final String GCM_LISTENER_DEBUG = "GCM_LISTENER_DEBUG";

    /**
     * This function will be called when a new message is received
     * @param from SenderID reference
     * @param data bundle that contains the message
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        Log.d(GCM_LISTENER_DEBUG, "Data receive: " + data);
        sendNotification(data);
    }

    /**
     * This function will be called to show a single notification
     * @param data
     */
    private void sendNotification(Bundle data) {
        if(data!=null) {
            try {
                String mTitle = data.getString("title", "title is empty");
                String mMessage = data.getString("message", "message is empty");
                String mSubtitle = data.getString("subtitle", "subtitle is empty");

                // Clear activity top or open, as necessary, you need to implement your rule
                Intent mIntent = new Intent(this, MainActivity.class);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent mPendingIntent = PendingIntent.getActivity(this, 0, mIntent,
                        PendingIntent.FLAG_ONE_SHOT);

                // Notification sound alert (default from device)
                Uri mDefaultSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                // This class will create local notification with image, message etc.
                NotificationCompat.Builder mNotificationBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(mTitle)
                        .setSubText(mSubtitle)
                        .setContentText(mMessage)
                        .setAutoCancel(true)
                        .setSound(mDefaultSoundURI)
                        .setContentIntent(mPendingIntent);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                // notify your device, finally ;)
                notificationManager.notify(0, mNotificationBuilder.build());
            } catch (Exception e) {
                Log.d(GCM_LISTENER_DEBUG, "Exception on sendNotification: " + e.getMessage());
            }

        }
    }
}
