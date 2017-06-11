package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static database.ReminderDataBaseSchema.ReminderTable.*;

/**
 * Created by Garry on 04/06/2017.
 */

public class ReminderDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "reminders.db";

    public ReminderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + "(" +
                ReminderDataBaseSchema.ReminderTable.Cols.UUID + "," +
                ReminderDataBaseSchema.ReminderTable.Cols.SUBJECT + "," +
                ReminderDataBaseSchema.ReminderTable.Cols.BODY + "," +
                ReminderDataBaseSchema.ReminderTable.Cols.ALARM_HOUR + "," +
                ReminderDataBaseSchema.ReminderTable.Cols.ALARM_MINUTE + "," +
                ReminderDataBaseSchema.ReminderTable.Cols.HAS_ALARM + "," +
                ReminderDataBaseSchema.ReminderTable.Cols.DATE +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
