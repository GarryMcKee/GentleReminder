package Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import Model.Reminder;
import garrymckee.mellobit.com.gentlereminder.R;

import static android.media.AudioManager.STREAM_NOTIFICATION;

/**
 * Created by Garry on 05/06/2017.
 */

public class ReminderNotification {

    private static final int CONTENT_TITLE_CHAR_LIMIT = 30;
    private static final int CONTENT_TEXT_CHAR_LIMIT = 40;

    private Notification mNotification;
    private Context mContext;

    public ReminderNotification(Reminder reminder, Context context) {
        mContext = context;
        Uri sonificationUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.sonification1);
        String contentTitle = reminder.getSubject();
        if(contentTitle.length() >CONTENT_TITLE_CHAR_LIMIT) {
            contentTitle = contentTitle.substring(0, 26) + "...";
        }

        String contentText = reminder.getBody();
        if(contentText.length() > CONTENT_TEXT_CHAR_LIMIT) {
            contentText = contentText.substring(0, 36) + "...";
        }

        mNotification = new NotificationCompat.Builder(mContext).
                setSmallIcon(R.drawable.ic_mood_black_18dp)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSound(sonificationUri, STREAM_NOTIFICATION)
                .build();

        NotificationManager notificationManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mNotification);

    }


}
