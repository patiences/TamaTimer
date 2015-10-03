package patienceshyu.tamatimer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

/**
 * Created by Patience on 15-09-25.
 */

public class Timer extends CountDownTimer {

    TextView countdown;
    //public long timeToEnd;
    private Sprite sprite;
    long duration;
    boolean timerHasStarted;
    Activity activity;


    public Timer(long duration, long interval, TextView countdown, Sprite sprite, Activity activity) {
        super(duration, interval);
        this.duration = duration;
        this.countdown = countdown;
        this.sprite = sprite;
        timerHasStarted = false;
        this.activity = activity;
    }

    @Override
    public void onFinish() {
        // Remove the countdown
        countdown.setText("HATCHED!");
        activity.finish();


    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onTick(long millisUntilFinished) {

        // Change milliseconds into MM:SS
        String timeLeft = String.format("%d : %02d",
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

        // Display the countdown
        countdown.setText(timeLeft);

        // Check to see if it's time to change the sprite
        if (!sprite.omelette) {
            if (((millisUntilFinished <= 0.8 * duration) && sprite.eggStatus == 1) ||       //80% done
                    ((millisUntilFinished <= 0.6 * duration) && sprite.eggStatus == 2) ||   //60% done
                    ((millisUntilFinished <= 0.4 * duration) && sprite.eggStatus == 3) ||   //40% done
                    ((millisUntilFinished <= 0.2 * duration) && sprite.eggStatus == 4)) {   //20% done

                sprite.nextStatus();

            }
        }

    }


}
