package patienceshyu.tamatimer;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by jeaniewu on 15-09-26.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

}
