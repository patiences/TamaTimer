package patienceshyu.tamatimer;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Patience on 15-10-03.
 */
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the preferences fragment as the content of the activity
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment()).commit();
    }
}
