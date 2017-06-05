package garrymckee.mellobit.com.gentlereminder.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

import Model.Reminder;
import butterknife.BindView;
import butterknife.ButterKnife;
import garrymckee.mellobit.com.gentlereminder.R;

/**
 * Created by Garry on 01/06/2017.
 */

public class ReminderFragment extends Fragment {

    public static final String ARGS_REMINDER_ID = "reminder_id";

    PresenterContract.ReminderPresenter mPresenter;

    @BindView(R.id.reminder_edit_subject)
    EditText reminderSubjectEditText;

    @BindView(R.id.reminder_edit_body)
    EditText reminderBodyEditText;

    public static Fragment newInstance(UUID uuid) {

        Bundle args = new Bundle();
        args.putSerializable(ARGS_REMINDER_ID, uuid);

        Fragment fragment = new ReminderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPresenter = new ReminderPresenter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Reminder reminder = mPresenter.getReminder(getReminderId());
        View v = inflater.inflate(R.layout.fragment_reminder, container, false);
        ButterKnife.bind(this, v);
        reminderSubjectEditText.setText(reminder.getSubject());
        reminderBodyEditText.setText(reminder.getBody());
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.reminder_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_reminder_item :
                UUID reminderId = getReminderId();
                mPresenter.deleteReminder(reminderId);
                getActivity().finish();
                break;
            case R.id.add_reminder_button:
                //Add Reminder
                break;
        }

        return true;
    }

    @Override
    public void onPause() {
        super.onPause();

        UUID reminderId = getReminderId();
        String subject = reminderSubjectEditText.getText().toString();
        String body = reminderBodyEditText.getText().toString();

        if(subject.trim().isEmpty()  && body.trim().isEmpty()) {
            mPresenter.deleteReminder(reminderId);
            return;
        }

        Reminder reminder = new Reminder(reminderId);
        reminder.setSubject(subject);
        reminder.setBody(body);
        reminder.setLastModified(new Date());

        mPresenter.setReminder(reminder);
        Log.d("CHECKSAVE", "Saved reminder!");
    }

    private UUID getReminderId() {
        return (UUID) getArguments().getSerializable(ARGS_REMINDER_ID);
    }
}
