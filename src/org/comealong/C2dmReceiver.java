package org.comealong;

import android.content.*;
import android.util.Log;

/**
 * Created by IntelliJ IDEA.
 * User: marlog
 * Date: 1/24/12
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class C2dmReceiver extends BroadcastReceiver {
    private static String KEY = "c2dmPref";
    private static String REGISTRATION_KEY = "registrationKey";

    private Context context;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("cdm", "something received");

        this.context = context;
        if (intent.getAction().equals("com.google.android.c2dm.intent.REGISTRATION")) {
            handleRegistration(context, intent);
        } else if (intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
            handleMessage(context, intent);
        }
    }

    private void handleRegistration(Context context, Intent intent) {
        String registration = intent.getStringExtra("registration_id");
        if (intent.getStringExtra("error") != null) {

            Log.d("c2dm", "registration failed");
            String error = intent.getStringExtra("error");
            if (error == "SERVICE_NOT_AVAILABLE") {
                Log.d("c2dm", "SERVICE_NOT_AVAILABLE");
            } else if (error == "ACCOUNT_MISSING") {
                Log.d("c2dm", "ACCOUNT_MISSING");
            } else if (error == "AUTHENTICATION_FAILED") {
                Log.d("c2dm", "AUTHENTICATION_FAILED");
            } else if (error == "TOO_MANY_REGISTRATIONS") {
                Log.d("c2dm", "TOO_MANY_REGISTRATIONS");
            } else if (error == "INVALID_SENDER") {
                Log.d("c2dm", "INVALID_SENDER");
            } else if (error == "PHONE_REGISTRATION_ERROR") {
                Log.d("c2dm", "PHONE_REGISTRATION_ERROR");
            }
        } else if (intent.getStringExtra("unregistered") != null) {

            Log.d("c2dm", "unregistered");

        } else if (registration != null) {
            Log.d("c2dm", registration);
            SharedPreferences.Editor editor =
                    context.getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
            editor.putString(REGISTRATION_KEY, registration);
            editor.commit();

        }
    }

    private void handleMessage(Context context, Intent intent) {
        Log.d("c2dm","Message received");

    }
}
