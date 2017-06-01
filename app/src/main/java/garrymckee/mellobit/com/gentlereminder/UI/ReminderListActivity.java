package garrymckee.mellobit.com.gentlereminder.UI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import garrymckee.mellobit.com.gentlereminder.R;

public class ReminderListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);

        FragmentManager fm = getSupportFragmentManager();
        Fragment reminderListFragment = new ReminderListFragment();
        fm.beginTransaction()
                .add(R.id.container, reminderListFragment)
                .commit();
    }
}
