package garrymckee.mellobit.com.gentlereminder.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

import Model.Reminder;
import Model.ReminderRepository;
import butterknife.BindView;
import butterknife.ButterKnife;
import garrymckee.mellobit.com.gentlereminder.R;

/**
 * Created by Garry on 29/05/2017.
 */

public class ReminderListFragment extends Fragment {

    private ReminderListFragmentPresenter mPresenter;

    @BindView(R.id.reminder_list) RecyclerView reminderListView;
    @BindView(R.id.add_reminder_button) FloatingActionButton addReminderButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ReminderListFragmentPresenter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reminder_list, container, false);
        ButterKnife.bind(this, v);
        refreshUi();

        addReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reminder reminder = new Reminder();
                mPresenter.addReminder(reminder);
                Intent intent = ReminderActivity.getIntent(getActivity(), reminder.getUUID());
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUi();
    }

    private void refreshUi() {
        List<Reminder> reminders = mPresenter.getReminderList();
        reminderListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        reminderListView.setAdapter(new ReminderAdapter(reminders));
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.reminder_subject)
        TextView reminderSubject;
        @BindView(R.id.reminder_body_preview)
        TextView reminderBody;

        public ReminderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {
            Reminder reminder = mPresenter.getReminderAt(this.getAdapterPosition());
        }
    }


    private class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder> {

        private List<Reminder> mReminders;

        public ReminderAdapter(List<Reminder> reminders) {
            mReminders = reminders;
        }

        @Override
        public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.list_item_reminder, parent, false);
            return new ReminderViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ReminderViewHolder holder, int position) {
            holder.reminderSubject.setText(mReminders.get(position).getSubject());
            holder.reminderBody.setText(mReminders.get(position).getBody());
        }

        @Override
        public int getItemCount() {
            return mReminders.size();
        }
    }

}
