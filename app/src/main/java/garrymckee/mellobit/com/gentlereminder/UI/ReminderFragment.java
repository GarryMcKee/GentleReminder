package garrymckee.mellobit.com.gentlereminder.UI;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Date;
import java.util.UUID;

import Model.Reminder;
import Notification.ReminderNotification;
import butterknife.BindView;
import butterknife.ButterKnife;
import garrymckee.mellobit.com.gentlereminder.R;

/**
 * Created by Garry on 01/06/2017.
 */

public class ReminderFragment extends Fragment implements TimePickerDialog.OnTimeSetListener{

    private static final String ARGS_REMINDER_ID = "reminder_id";

    PresenterContract.ReminderPresenter mPresenter;

    @BindView(R.id.reminder_edit_subject)
    EditText reminderSubjectEditText;

    @BindView(R.id.reminder_edit_body)
    EditText reminderBodyEditText;

    private Reminder mReminder;

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
        mReminder = new Reminder(getReminderId());
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
                mPresenter.deleteReminder(getReminderId());
                getActivity().finish();
                break;
            case R.id.set_reminder_alarm_item:
                AlarmTimeFragment alarmTimeFragment = new AlarmTimeFragment(getActivity(), this, 0, 0, false);
                alarmTimeFragment.show();
                break;
            case R.id.dummy_notification_item:
                Reminder reminder = mPresenter.getReminder(getReminderId());
                reminder.setSubject(reminderSubjectEditText.getText().toString());
                reminder.setBody(reminderBodyEditText.getText().toString());
                new ReminderNotification(reminder, getActivity());
        }

        return true;
    }

    @Override
    public void onPause() {
        super.onPause();

        String subject = reminderSubjectEditText.getText().toString();
        String body = reminderBodyEditText.getText().toString();

        if(subject.trim().isEmpty()  && body.trim().isEmpty()) {
            mPresenter.deleteReminder(getReminderId());
            return;
        }

        mReminder.setSubject(subject);
        mReminder.setBody(body);
        mReminder.setLastModified(new Date());

        mPresenter.setReminder(mReminder);
    }

    private UUID getReminderId() {
        return (UUID) getArguments().getSerializable(ARGS_REMINDER_ID);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mReminder.setAlarmHour(hourOfDay);
        mReminder.setAlarmMinute(minute);
    }
}
