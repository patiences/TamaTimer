package patienceshyu.tamatimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;


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

    //private long startTime = 60000 ; // 30 seconds
    private static long startTime;
    private final long interval = 1000;  // 1 second

   SharedPreferences preferences;
   SharedPreferences.OnSharedPreferenceChangeListener listener;
/*
    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
            //getParent().finish();
            startActivityForResult(new Intent(getParent(), SettingsActivity.class),1);
        }
    };

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadActivity();

    }

    public void loadActivity() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {

            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                Log.e("startTime is", Long.toString(startTime));
                //getParent().finish();
                loadActivity();

                Log.e("startTime is", Long.toString(startTime));
            }
        };
        String startTimeString = preferences.getString("Timer", "60");
        startTime = Long.valueOf(startTimeString);


        // Start Button
        startB = (Button) this.findViewById(R.id.StartButton);
        startB.setOnClickListener(this);

        // Make a sprite
        spriteDisplay = (ImageView) this.findViewById(R.id.sprite);
        sprite = new Sprite(spriteDisplay);

        // Countdown TextView
        countdown = (TextView) this.findViewById(R.id.timer);
        timer = new Timer(startTime * 60000, interval, countdown, sprite, this);

        heart1Display = (ImageView) this.findViewById(R.id.heart1Display);
        heart2Display = (ImageView) this.findViewById(R.id.heart2Display);
        heart3Display = (ImageView) this.findViewById(R.id.heart3Display);

        registerReceiver(broadcastReceiver, new IntentFilter("screenActivityBroadcast"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.menu_settings:
                startActivityForResult(new Intent(this, SettingsActivity.class),1);
                preferences.registerOnSharedPreferenceChangeListener(listener);

        }

        return true;
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
            // User decides to give up
            sprite.cooked();
            finish();

        }

    }

    public void finish() {
        stopService(new Intent(getApplicationContext(), LockService.class));
        timer.cancel();
        timer.timerHasStarted = false;
        startB.setText("START");
        if(!sprite.omelette) {
            countdown.setText("Hatched!");
        } else {
            countdown.setText("PWNED");
        }



    }


    public void killAHeart() {

        if (heart1.alive) {
            heart1.die();
        } else if (heart2.alive) {
            heart2.die();
        } else if (heart3.alive) {
            sprite.cooked();
            heart3.die();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You have used your phone way too many times!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            LinearLayout toastLayout = (LinearLayout) toast.getView();
            TextView toastTV = (TextView) toastLayout.getChildAt(0);
            toastTV.setTextSize(30);
            toast.show();
            finish();
        }
    }


/*
    @Override
    public void onPause() {

        super.onPause();

        preferences.registerOnSharedPreferenceChangeListener(null);

    }
*/



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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;

    }



}
