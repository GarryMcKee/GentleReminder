package Model;

import java.util.ArrayList;

/**
 * Created by Garry on 01/06/2017.
 */

public class ReminderList {
    private static ReminderList instance;
    private ArrayList<Reminder> mReminders;

    private ReminderList () {

    }

    public static ReminderList getIsntance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new ReminderList();
            return instance;
        }
    }

    public ArrayList<Reminder> getReminders() {
        return mReminders;
    }

    public void setReminders(ArrayList<Reminder> reminders) {
        mReminders = reminders;
    }
}
