package patienceshyu.tamatimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Patience on 15-09-26.
 */
public class ScreenReceiver extends BroadcastReceiver {

    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.e("test", "onReceive");
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // do whatever you need to do here
            wasScreenOn = false;
            Log.e("test","wasScreenOn"+wasScreenOn);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here
            wasScreenOn = true;
            Log.e("test","wasScreenOn"+wasScreenOn);
        }else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            Log.e("test","userpresent");
        }
    }
}