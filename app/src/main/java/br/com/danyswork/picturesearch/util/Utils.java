package br.com.danyswork.picturesearch.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

    public static boolean isNetworkAvailable(Context context) {
        boolean result = false;
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo network = cm.getActiveNetworkInfo();
                if (network != null) {
                    result = network.isConnected();
                }
            }
        }
        return result;
    }
}
