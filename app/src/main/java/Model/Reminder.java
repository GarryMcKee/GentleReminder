package Model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Garry on 01/06/2017.
 */

public class Reminder {

    private UUID mUUID;
    private String mSubject;
    private String mBody;
    private Date mLastModified;

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

    public void setLastModified(Date lastModified) {
        mLastModified = lastModified;
    }

    public Date getLastModified() {
        return mLastModified;
    }
}
