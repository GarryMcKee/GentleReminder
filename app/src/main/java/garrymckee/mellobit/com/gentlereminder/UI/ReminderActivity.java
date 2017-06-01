package garrymckee.mellobit.com.gentlereminder.UI;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import garrymckee.mellobit.com.gentlereminder.R;

/**
 * Created by Garry on 01/06/2017.
 */

public class ReminderActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_reminder);

        FragmentManager fm = getSupportFragmentManager();
        ReminderFragment fragment = new ReminderFragment();
        fm.beginTransaction()
                .add(R.id.reminder_fragment_container, fragment)
                .commit();
    }
}
