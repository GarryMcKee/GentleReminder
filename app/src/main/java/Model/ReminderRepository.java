package Model;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import Database.ReminderCursorWrapper;
import Database.ReminderDataBaseSchema;
import Database.ReminderDbHelper;

import static Database.ReminderDataBaseSchema.ReminderTable.*;

/**
 * Created by Garry on 01/06/2017.
 */

public class ReminderRepository {
    private static ReminderRepository instance;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private ReminderRepository(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ReminderDbHelper(mContext).getReadableDatabase();
    }

    public static ReminderRepository getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            instance = new ReminderRepository(context);
            return instance;
        }
    }

    public Reminder getReminder(UUID id) {
        ReminderCursorWrapper cursor = queryReminders(
                ReminderDataBaseSchema.ReminderTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            } else {
                cursor.moveToFirst();
                return cursor.getReminder();
            }

        } finally {
            cursor.close();
        }
    }

    public List<Reminder> getReminders() {

        List<Reminder> reminderList = new ArrayList<>();


        //Get all reminders in table

        ReminderCursorWrapper cursor = queryReminders(null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                reminderList.add(cursor.getReminder());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }


        return reminderList;
    }

    public static ContentValues getContentValues(Reminder reminder) {
        ContentValues values = new ContentValues();
        values.put(ReminderDataBaseSchema.ReminderTable.Cols.UUID, reminder.getUUID().toString());
        values.put(ReminderDataBaseSchema.ReminderTable.Cols.SUBJECT, reminder.getSubject());
        values.put(ReminderDataBaseSchema.ReminderTable.Cols.BODY, reminder.getBody());
        values.put(ReminderDataBaseSchema.ReminderTable.Cols.DATE, reminder.getLastModified().getTime());

        return values;
    }

    public void addReminder(Reminder reminder) {
        ContentValues values = getContentValues(reminder);
        mDatabase.insert(ReminderDataBaseSchema.ReminderTable.NAME, null, values);
    }

    public void updateReminder(Reminder reminder) {
        String uuid = reminder.getUUID().toString();
        ContentValues values = getContentValues(reminder);
        mDatabase.update(ReminderDataBaseSchema.ReminderTable.NAME,
                values,
                ReminderDataBaseSchema.ReminderTable.Cols.UUID + " = ?",
                new String[] {uuid});
    }

    public void deleteReminder(UUID uuid) {
        String uuidString = uuid.toString();
        mDatabase.delete(ReminderDataBaseSchema.ReminderTable.NAME,
                ReminderDataBaseSchema.ReminderTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    public ReminderCursorWrapper queryReminders(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ReminderDataBaseSchema.ReminderTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ReminderCursorWrapper(cursor);
    }
}
