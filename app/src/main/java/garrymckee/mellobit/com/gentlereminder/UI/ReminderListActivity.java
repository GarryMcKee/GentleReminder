package garrymckee.mellobit.com.gentlereminder.UI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;

import garrymckee.mellobit.com.gentlereminder.R;

public class ReminderListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ReminderListFragment();
    }
}
