package patienceshyu.tamatimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Patience on 15-09-26.
 */
public class ScreenReceiver extends BroadcastReceiver {

    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(final Context context, final Intent intent) {

        Bundle extras = intent.getExtras();
        Intent i = new Intent("screenActivityBroadcast");

        Log.e("test", "onReceive");

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

            // Screen is off, so user is still on track
            wasScreenOn = false;
            Log.e("test","wasScreenOn"+wasScreenOn);

            // Pass 0 to MainActivity
            i.putExtra("message", 0);

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

            // User has used phone!
            wasScreenOn = false;
            Log.e("test","wasScreenOn"+wasScreenOn);

            // Pass 1 to MainActivity
            i.putExtra("message", 0);

        } else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){

            // User has used phone!
            wasScreenOn = true;
            Log.e("test", "userpresent");

            // Pass 1 to MainActivity
            i.putExtra("message", 1);


            Toast toast = Toast.makeText(context, "You should not be on your phone!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            LinearLayout toastLayout = (LinearLayout) toast.getView();
            TextView toastTV = (TextView) toastLayout.getChildAt(0);
            toastTV.setTextSize(30);
            toast.show();

            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(400);

        }

        // Send Broadcast to MainActivity!
        context.sendBroadcast(i);
    }
}