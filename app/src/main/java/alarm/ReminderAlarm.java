package alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import model.Reminder;
import notifications.NotificationReceiver;

/**
 * Created by Garry on 09/06/2017.
 */

public class ReminderAlarm extends BroadcastReceiver {

    public static int REQ_REMINDER_NOTIFY = 9876;

    public static void registerAlarm(Reminder reminder, Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, reminder.getAlarmHour());
        calendar.set(Calendar.MINUTE, reminder.getAlarmHour());

        if(reminder.getSubject() == null) {
            Log.d("CHECKNULL", "SUBJECT IS NULL");
        }

        Intent intent = new Intent(context, notifications.NotificationReceiver.class);
        intent.putExtra(NotificationReceiver.EXTRA_REMINDER_SUBJECT, reminder.getSubject());
        intent.putExtra(NotificationReceiver.EXTRA_REMINDER_BODY, reminder.getBody());
        intent.putExtra(NotificationReceiver.EXTRA_REMINDER_UUID, reminder.getUUID());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQ_REMINDER_NOTIFY, intent, PendingIntent.FLAG_ONE_SHOT);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
