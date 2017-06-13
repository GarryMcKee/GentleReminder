package garrymckee.mellobit.com.gentlereminder.UI;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;
import java.util.UUID;

import alarm.ReminderAlarm;
import model.Reminder;
import notifications.ReminderNotification;
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

    @BindView(R.id.alarm_panel)
    View alarmPanelLayout;

    @BindView(R.id.alarm_time_label)
    TextView alarmTimeLabel;

    @BindView(R.id.alarm_set_icon)
    ImageView alarmSetIcon;

    @BindView(R.id.clear_alarm_icon)
    ImageView clearAlarmIcon;

    private Reminder mReminder;
    private UUID mUUID;

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

        if(savedInstanceState != null) {
            mUUID = (UUID) savedInstanceState.getSerializable(ARGS_REMINDER_ID);
        } else {
            mUUID = (UUID) getArguments().getSerializable(ARGS_REMINDER_ID);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter = new ReminderPresenter(getActivity());
        mReminder = mPresenter.getReminder(mUUID);

        if(mReminder == null) {
            mReminder = new Reminder(mUUID);
            mPresenter.setReminder(mReminder);
        }

        reminderSubjectEditText.setText(mReminder.getSubject());
        reminderBodyEditText.setText(mReminder.getBody());

        toggleAlarmPanelActive();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reminder, container, false);
        ButterKnife.bind(this, v);

        alarmPanelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlarmDialog();
            }
        });

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
                mPresenter.deleteReminder(mUUID);
                getActivity().finish();
                break;
            case R.id.set_reminder_alarm_item:
                showAlarmDialog();
                break;
            case R.id.dummy_notification_item:
                Reminder reminder = mPresenter.getReminder(mUUID);
                reminder.setSubject(reminderSubjectEditText.getText().toString());
                reminder.setBody(reminderBodyEditText.getText().toString());
                new ReminderNotification(reminder.getSubject(), reminder.getBody(), reminder.getUUID(), getActivity());
        }

        return true;
    }

    @Override
    public void onPause() {
        super.onPause();

        String subject = reminderSubjectEditText.getText().toString();
        String body = reminderBodyEditText.getText().toString();

        if(subject.trim().isEmpty()  && body.trim().isEmpty()) {
            mPresenter.deleteReminder(mUUID);
        } else {
           saveReminder(subject, body);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARGS_REMINDER_ID, mUUID);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mReminder.setAlarmHour(hourOfDay);
        mReminder.setAlarmMinute(minute);
        mReminder.setHasAlarm(true);
        toggleAlarmPanelActive();
        ReminderAlarm.registerAlarm(mReminder, getActivity());
    }

    private void showAlarmDialog() {
        AlarmTimeFragment alarmTimeFragment = new AlarmTimeFragment(getActivity(), this, 0, 0, false);
        alarmTimeFragment.show();
    }

    private void saveReminder(String subject, String body) {
        mReminder.setSubject(subject);
        mReminder.setBody(body);
        mReminder.setLastModified(new Date());
        mPresenter.setReminder(mReminder);
    }

    private void setAlarmTimeLabel(){

        if(mReminder.isHasAlarm()) {
            String alarmTimeString = mReminder.getAlarmHour() + ":" + mReminder.getAlarmMinute();
            alarmTimeLabel.setText(alarmTimeString);
        } else {
            alarmTimeLabel.setText(R.string.no_alarm_set);
        }

    }

    private void toggleAlarmPanelActive() {
        if (mReminder.isHasAlarm()) {
            alarmSetIcon.setImageResource(R.drawable.ic_alarm_black_36dp);
            alarmSetIcon.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
            setAlarmTimeLabel();
            clearAlarmIcon.setVisibility(View.VISIBLE);
            alarmPanelLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPageWhite));
        } else {
            alarmSetIcon.setImageResource(R.drawable.ic_alarm_off_black_36dp);
            alarmSetIcon.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorInactiveAlarmIcon));
            setAlarmTimeLabel();
            clearAlarmIcon.setVisibility(View.GONE);
            alarmPanelLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.cardview_dark_background));
        }
    }
}
