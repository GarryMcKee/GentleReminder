package garrymckee.mellobit.com.gentlereminder.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

import garrymckee.mellobit.com.gentlereminder.R;

/**
 * Created by Garry on 01/06/2017.
 */

public class ReminderActivity extends SingleFragmentActivity{

    public static final String EXTRA_REMINDER_ID = "com.garrymckee.mellotbit.com.gentlereminder.reminderid";

    public static Intent getIntent (Context context, UUID reminderId) {
        Intent intent = new Intent(context, ReminderActivity.class);
        intent.putExtra(EXTRA_REMINDER_ID, reminderId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID uuid = (UUID) getIntent().getExtras().getSerializable(EXTRA_REMINDER_ID);
        return ReminderFragment.newInstance(uuid);
    }
}
