package garrymckee.mellobit.com.gentlereminder.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import garrymckee.mellobit.com.gentlereminder.R;

/**
 * Created by Garry on 01/06/2017.
 */

public class ReminderListModules {

    public class ReminderViewHolder extends RecyclerView.ViewHolder {
        public ReminderViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_reminder, parent));
        }
    }

    public class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder> {

        @Override
        public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ReminderViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}
