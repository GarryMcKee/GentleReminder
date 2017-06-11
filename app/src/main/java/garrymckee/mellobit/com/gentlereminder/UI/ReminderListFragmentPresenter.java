package garrymckee.mellobit.com.gentlereminder.UI;

import android.content.Context;

import java.util.List;

import model.Reminder;
import model.ReminderRepository;

/**
 * Created by Garry on 01/06/2017.
 */

public class ReminderListFragmentPresenter implements PresenterContract.ReminderListPresenter{

    private ReminderRepository mReminderRepository;
    private Context mContext;

    public ReminderListFragmentPresenter(Context context) {
        mReminderRepository = ReminderRepository.getInstance(context);
    }

    @Override
    public List<Reminder> getReminderList() {
        return mReminderRepository.getReminders();
    }

    @Override
    public Reminder getReminderAt(int position) {
        return mReminderRepository.getReminders().get(position);
    }

    @Override
    public void addReminder(Reminder reminder) {
        mReminderRepository.addReminder(reminder);
    }
}
