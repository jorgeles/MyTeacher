package com.example.jmrosell.myteacher;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import  com.example.jmrosell.myteacher.R;

/**
 * Clase que simplifica el uso de un unico tracker para la aplicacion.
 * @author Antonio Toro
 */
public class GAHelper {
    public static Tracker tracker = null;

    public GAHelper(Context context) {
        if (tracker == null)
            tracker = GoogleAnalytics.getInstance(context).newTracker(R.xml.app_tracker);
    }
}
