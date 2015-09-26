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
import android.content.Intent;
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

    private long startTime = 30000 ; // 30 seconds
    private final long interval = 1000;  // 1 second

    //private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //showUserSettings();
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //startTime = Long.valueOf(sharedPreferences.getString("timer", "1"));
        //timerHasStarted = false;

        // Start Button
        startB = (Button) this.findViewById(R.id.StartButton);
        startB.setOnClickListener(this);

        // Make a sprite
        spriteDisplay = (ImageView) this.findViewById(R.id.sprite);
        sprite = new Sprite(spriteDisplay);

        // Countdown TextView
        countdown = (TextView) this.findViewById(R.id.timer);
        timer = new Timer(startTime, interval, countdown, sprite, this);

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

            //case R.id.menu_settings:
              //  Intent i = new Intent(this, SettingsActivity.class);
                //startActivityForResult(i, RESULT_SETTINGS);
                //break;


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

    /*private void showUserSettings() {
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        StringBuilder builder = new StringBuilder();

        //builder.append("\n Username: "
        //        + sharedPrefs.getString("prefUsername", "NULL"));

        //builder.append("\n Send report:"
        //        + sharedPrefs.getBoolean("prefSendReport", false));

        //builder.append("\n Sync Frequency: "
        //        + sharedPrefs.getString("prefSyncFrequency", "NULL"));

        TextView settingsTextView = (TextView) findViewById(R.id.textUserSettings);

        settingsTextView.setText(builder.toString());
    }
    */




}
