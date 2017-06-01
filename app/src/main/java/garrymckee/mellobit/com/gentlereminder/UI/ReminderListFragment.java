package garrymckee.mellobit.com.gentlereminder.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import garrymckee.mellobit.com.gentlereminder.R;

/**
 * Created by Garry on 29/05/2017.
 */

public class ReminderListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reminder_list, container);
        return v;
    }
}
