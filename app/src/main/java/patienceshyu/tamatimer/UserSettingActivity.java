package patienceshyu.tamatimer;

/**
 * Created by jeaniewu on 15-09-26.
 */
import android.app.Activity;
import android.os.Bundle;

public class UserSettingActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();

    }
}
