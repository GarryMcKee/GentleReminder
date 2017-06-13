package notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.UUID;

import alarm.ReminderAlarm;

/**
 * Created by Garry on 09/06/2017.
 */

public class NotificationReceiver extends BroadcastReceiver {

    public static final String EXTRA_REMINDER_UUID = "com.mellobit.garrymckee.extra_reminder_uuid";
    public static final String EXTRA_REMINDER_SUBJECT = "reminderSubject";
    public static final String EXTRA_REMINDER_BODY = "reminderBody";


    @Override
    public void onReceive(Context context, Intent intent) {

        String subject = intent.getStringExtra(EXTRA_REMINDER_SUBJECT);
        String body = intent.getStringExtra(EXTRA_REMINDER_BODY);
        UUID reminderId = (UUID) intent.getSerializableExtra(EXTRA_REMINDER_UUID);

        ReminderNotification notification = new ReminderNotification(subject, body, reminderId, context);
        notification.reminderNotify();

    }
}
