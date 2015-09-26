package patienceshyu.tamatimer;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by jeaniewu on 15-09-26.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInsatanceState) {
        super.onCreate(savedInsatanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setBackgroundColor(Color.WHITE);
    }
}
