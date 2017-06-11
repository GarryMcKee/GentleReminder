package model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Garry on 01/06/2017.
 */

public class Reminder {

    private UUID mUUID;
    private String mSubject;
    private String mBody;
    private int mAlarmHour;
    private int mAlarmMinute;
    private Date mLastModified;
    private boolean mHasAlarm;

    public Reminder() {
        mUUID = UUID.randomUUID();
        mLastModified = new Date();
    }

    public Reminder(UUID uuid) {
        mUUID = uuid;
        mLastModified = new Date();
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public String getBody() {
        return mBody;
    }

    public void setAlarmHour(int alarmHour) {
        mAlarmHour = alarmHour;
    }

    public int getAlarmHour() {
        return mAlarmHour;
    }

    public void setAlarmMinute(int alarmMinute) {
        mAlarmMinute = alarmMinute;
    }

    public int getAlarmMinute() {
        return mAlarmMinute;
    }

    public void setHasAlarm(boolean hasAlarm) {
        mHasAlarm = hasAlarm;
    }

    public boolean isHasAlarm() {
        return mHasAlarm;
    }

    public void setLastModified(Date lastModified) {
        mLastModified = lastModified;
    }

    public Date getLastModified() {
        return mLastModified;
    }
}
