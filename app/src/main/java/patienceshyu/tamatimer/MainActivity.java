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
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    private Timer timer;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private int hearts = 3;

    private final long startTime = 3600000; // 1 hour
    private final long interval = 1000;  // 1 second

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start Button
        startB = (Button) this.findViewById(R.id.StartButton);
        startB.setOnClickListener(this);

        // Countdown TextView
        text = (TextView) this.findViewById(R.id.timer);

        timer = new Timer(startTime, interval, text);


        startService(new Intent(getApplicationContext(), LockService.class));

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
    public void onClick(View v)
    {
        if (!timerHasStarted)
        {
            timer.start();
            timerHasStarted = true;
            startB.setText("I Want To Give Up!");
        }
        else
        {

            timer.cancel();
            timerHasStarted = false;
            startB.setText("START");
            text.setText("");
        }


    }


    BroadcastReceiver broadcastReceiver =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Bundle b = intent.getExtras();

            int message = b.getInt("message");

            if (message == 1) {

                Log.e("test", "activity knows userpresent");

                hearts--;
            }

            Log.e("newmessage", "" + message);
        }
    };


}
