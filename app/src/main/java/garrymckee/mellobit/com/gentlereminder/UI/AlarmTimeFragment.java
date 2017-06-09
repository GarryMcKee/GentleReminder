package garrymckee.mellobit.com.gentlereminder.UI;

import android.app.TimePickerDialog;
import android.content.Context;

/**
 * Created by Garry on 09/06/2017.
 */

public class AlarmTimeFragment extends TimePickerDialog {

    public AlarmTimeFragment(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
    }
}
