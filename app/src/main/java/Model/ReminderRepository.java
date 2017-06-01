package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Garry on 01/06/2017.
 */

public class ReminderRepository {
    private static ReminderRepository instance;
    private ArrayList<Reminder> mReminders;

    private ReminderRepository() {
        mReminders = new ArrayList<>();
        Reminder dummyReminder = new Reminder();
        dummyReminder.setSubject("Diane...");
        dummyReminder.setBody("Visited the town of Twin Peaks today Diane, strange place. Had a damn fine cup of coffee in the hotel and cherry pie in the local diner for lunch");
        dummyReminder.setLastModified(new Date());
        mReminders.add(dummyReminder);
    }

    public static ReminderRepository getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new ReminderRepository();
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
