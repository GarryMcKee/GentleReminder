package garrymckee.mellobit.com.gentlereminder.UI;

import java.util.List;

import Model.Reminder;
import Model.ReminderRepository;

/**
 * Created by Garry on 01/06/2017.
 */

public class ReminderListFragmentPresenter implements PresenterContract.ReminderListPresenter{

    private ReminderRepository mReminderRepository;

    public ReminderListFragmentPresenter() {
        mReminderRepository = ReminderRepository.getInstance();
    }

    @Override
    public List<Reminder> getReminderList() {
        return mReminderRepository.getReminders();
    }

    @Override
    public Reminder getReminderAt(int position) {
        return mReminderRepository.getReminders().get(position);
    }
}
