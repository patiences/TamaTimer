package patienceshyu.tamatimer;

import android.annotation.TargetApi;
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


    public Timer(long duration, long interval, TextView countdown, Sprite sprite) {
        super(duration, interval);
        this.duration = duration;
        this.countdown = countdown;
        //timeToEnd = duration;
        this.sprite = sprite;
        timerHasStarted = false;
    }

    @Override
    public void onFinish() {
        // Remove the countdown
        countdown.setText("");

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

        // Set the time left
        //timeToEnd = millisUntilFinished;

        // Check to see if it's time to change the sprite
        if (!sprite.omelette) {
            if ((millisUntilFinished <= 0.8 * 60000 && sprite.eggStatus == 1) ||       //80% done
                    (millisUntilFinished <= 0.6 * 60000 && sprite.eggStatus == 2) ||   //60% done
                    (millisUntilFinished <= 0.4 * 60000 && sprite.eggStatus == 3) ||   //40% done
                    (millisUntilFinished <= 0.2 * 60000 && sprite.eggStatus == 4)) {   //20% done
                sprite.nextStatus();
            }
        }
        Log.e("omelette", "true");



    }

}
