package patienceshyu.tamatimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    private Timer timer;
    private Button startB;
    private TextView countdown;

    private Heart heart1;
    private Heart heart2;
    private Heart heart3;

    private ImageView heart1Display;
    private ImageView heart2Display;
    private ImageView heart3Display;

    private boolean omelette;//lol

    private Sprite sprite;
    private ImageView spriteDisplay;

    private final long startTime = 60000; // 1 hour
    private final long interval = 1000;  // 1 second

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //timerHasStarted = false;

        // Start Button
        startB = (Button) this.findViewById(R.id.StartButton);
        startB.setOnClickListener(this);

        // Make a sprite
        spriteDisplay = (ImageView) this.findViewById(R.id.sprite);
        sprite = new Sprite(spriteDisplay);

        // Countdown TextView
        countdown = (TextView) this.findViewById(R.id.timer);
        timer = new Timer(startTime, interval, countdown, sprite);

        heart1Display = (ImageView) this.findViewById(R.id.heart1Display);
        heart2Display = (ImageView) this.findViewById(R.id.heart2Display);
        heart3Display = (ImageView) this.findViewById(R.id.heart3Display);

        registerReceiver(broadcastReceiver, new IntentFilter("screenActivityBroadcast"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (!timer.timerHasStarted) {
            startService(new Intent(getApplicationContext(), LockService.class));
            timer.start();
            timer.timerHasStarted = true;

            // Set Button
            startB.setText("I Want To Give Up!");

            // Make a sprite
            spriteDisplay = (ImageView) this.findViewById(R.id.sprite);
            sprite = new Sprite(spriteDisplay);

            heart1 = new Heart(heart1Display);
            heart2 = new Heart(heart2Display);
            heart3 = new Heart(heart3Display);

        } else {
            stopService(new Intent(getApplicationContext(), LockService.class));
            timer.cancel();
            timer.timerHasStarted = false;
            startB.setText("START");
            countdown.setText("");
            // User decides to give up
            sprite.cooked();

        }

    }

    public void killAHeart() {

        if (heart1.alive) {
            heart1.die();
        } else if (heart2.alive) {
            heart2.die();
        } else if (heart3.alive) {
            heart3.die();
        } else {
            sprite.cooked();
        }
    }




    BroadcastReceiver broadcastReceiver =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Bundle b = intent.getExtras();

            int message = b.getInt("message");

            if (message == 1) {

                Log.e("test", "activity knows userpresent");

                // Lose a heart
                killAHeart();
            }

            Log.e("newmessage", "" + message);
        }
    };


}
