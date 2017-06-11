package garrymckee.mellobit.com.gentlereminder.UI;

import java.util.List;
import java.util.UUID;

import model.Reminder;

/**
 * Created by Garry on 01/06/2017.
 */

public interface PresenterContract {

    interface ReminderListPresenter {
        List<Reminder> getReminderList();
        Reminder getReminderAt(int position);
        void addReminder(Reminder reminder);
    }

    interface ReminderPresenter {
        Reminder getReminder(UUID id);
        void setReminder(Reminder reminder);
        void deleteReminder(UUID id);
    }
}
