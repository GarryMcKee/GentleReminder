package Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import Model.Reminder;

/**
 * Created by Garry on 04/06/2017.
 */

public class ReminderCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ReminderCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Reminder getReminder() {
        String uuid = getString(getColumnIndex(ReminderDataBaseSchema.ReminderTable.Cols.UUID));
        String subject = getString(getColumnIndex(ReminderDataBaseSchema.ReminderTable.Cols.SUBJECT));
        String body = getString(getColumnIndex(ReminderDataBaseSchema.ReminderTable.Cols.BODY));
        int alarmHour = getInt(getColumnIndex(ReminderDataBaseSchema.ReminderTable.Cols.ALARM_HOUR));
        int alarmMinute = getInt(getColumnIndex(ReminderDataBaseSchema.ReminderTable.Cols.ALARM_MINUTE));
        boolean hasAlarm = getInt(getColumnIndex(ReminderDataBaseSchema.ReminderTable.Cols.HAS_ALARM)) > 0;
        Date date = new Date(getLong(getColumnIndex(ReminderDataBaseSchema.ReminderTable.Cols.DATE)));

        Reminder reminder = new Reminder(UUID.fromString(uuid));
        reminder.setSubject(subject);
        reminder.setBody(body);
        reminder.setAlarmHour(alarmHour);
        reminder.setAlarmMinute(alarmMinute);
        reminder.setHasAlarm(hasAlarm);
        reminder.setLastModified(date);

        return reminder;
    }
}
