package garrymckee.mellobit.com.gentlereminder.UI;

import java.util.List;

import Model.Reminder;

/**
 * Created by Garry on 01/06/2017.
 */

public interface PresenterContract {

    interface ReminderListPresenter {
        List<Reminder> getReminderList();
        Reminder getReminderAt(int position);
    }
}
