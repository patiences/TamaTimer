package patienceshyu.tamatimer;

import android.os.CountDownTimer;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

/**
 * Created by Patience on 15-09-25.
 */

public class Timer extends CountDownTimer {

    TextView countdown;
    public long timeToEnd;
    private Sprite sprite;
    long duration;


    public Timer(long duration, long interval, TextView countdown, Sprite sprite) {
        super(duration, interval);
        this.duration = duration;
        this.countdown = countdown;
        timeToEnd = duration;
        this.sprite = sprite;
    }

    @Override
    public void onFinish() {
        // Remove the countdown
        countdown.setText("");

    }

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
        timeToEnd = millisUntilFinished;

        // Check to see if it's time to change the sprite
        if (!sprite.omelette) {
            if ((timeToEnd <= 0.8 * duration && sprite.eggStatus == 1) ||   //80% done
                    (timeToEnd <= 0.6 * duration && sprite.eggStatus == 2) ||   //60% done
                    (timeToEnd <= 0.4 * duration && sprite.eggStatus == 3) ||   //40% done
                    (timeToEnd <= 0.2 * duration && sprite.eggStatus == 4)) {   //20% done
                sprite.nextStatus();
            }
        }


    }

}
