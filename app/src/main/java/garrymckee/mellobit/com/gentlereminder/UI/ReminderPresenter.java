package garrymckee.mellobit.com.gentlereminder.UI;

import android.content.Context;

import Model.Reminder;
import Model.ReminderRepository;

/**
 * Created by Garry on 04/06/2017.
 */

public class ReminderPresenter implements PresenterContract.ReminderPresenter {

    ReminderRepository mReminderRepository;

    public ReminderPresenter (Context context) {
        mReminderRepository = ReminderRepository.getInstance(context);
    }

    @Override
    public Reminder getReminder() {
        return null;
    }

    @Override
    public void setReminder(Reminder reminder) {
        mReminderRepository.updateReminder(reminder);
    }
}
