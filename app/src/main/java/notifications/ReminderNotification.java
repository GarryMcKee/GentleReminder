package notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.UUID;

import garrymckee.mellobit.com.gentlereminder.R;
import garrymckee.mellobit.com.gentlereminder.UI.ReminderActivity;

import static android.media.AudioManager.STREAM_NOTIFICATION;

/**
 * Created by Garry on 05/06/2017.
 */

public class ReminderNotification {

    private static final String LOG_TAG = ReminderNotification.class.getSimpleName();

    private static final int CONTENT_TITLE_CHAR_LIMIT = 30;
    private static final int CONTENT_TEXT_CHAR_LIMIT = 40;

    private Notification mNotification;
    private Context mContext;

    public ReminderNotification(String subject, String body, UUID reminderId, Context context) {
        mContext = context;
        Uri sonificationUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.sonification1);
        String contentTitle = subject;
        if(contentTitle.length() >CONTENT_TITLE_CHAR_LIMIT) {
            contentTitle = contentTitle.substring(0, 26) + "...";
        }

        String contentText = body;
        if(contentText.length() > CONTENT_TEXT_CHAR_LIMIT) {
            contentText = contentText.substring(0, 36) + "...";
        }

        Intent intent = ReminderActivity.getIntent(context, reminderId);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mNotification = new NotificationCompat.Builder(mContext).
                setSmallIcon(R.drawable.ic_mood_black_18dp)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSound(sonificationUri, STREAM_NOTIFICATION)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
    }

    public void reminderNotify() {

        if(mNotification != null) {
            NotificationManager notificationManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, mNotification);
        } else {
            Log.d(LOG_TAG, "Notification was null");
        }

    }


}
