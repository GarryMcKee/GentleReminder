package garrymckee.mellobit.com.gentlereminder.UI;

import android.content.Context;

import java.util.UUID;

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
    public Reminder getReminder(UUID id) {
        return mReminderRepository.getReminder(id);
    }

    @Override
    public void setReminder(Reminder reminder) {
        mReminderRepository.updateReminder(reminder);
    }

    @Override
    public void deleteReminder(UUID uuid) {
        mReminderRepository.deleteReminder(uuid);
    }
}
