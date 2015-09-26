package patienceshyu.tamatimer;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;
import patienceshyu.tamatimer.MainActivity;

/**
 * Created by Patience on 15-09-25.
 */

public class Timer extends CountDownTimer {

    TextView text;


    public Timer(long startTime, long interval, TextView text) {
        super(startTime, interval);
        this.text = text;
    }

    @Override
    public void onFinish() {

        // Remove the countdown
        text.setText("");
    }

    @Override
    public void onTick(long millisUntilFinished) {

        // Change milliseconds into MM:SS
        String timeLeft = String.format("%d : %d",
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

        // Display the countdown
        text.setText(timeLeft);



    }

}
