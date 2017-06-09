package Database;

/**
 * Created by Garry on 01/06/2017.
 */

public class ReminderDataBaseSchema {

    public class ReminderTable {
        public static final String NAME = "reminders";

        public class Cols {
            public static final String UUID = "uuid";
            public static final String SUBJECT = "subject";
            public static final String BODY = "body";
            public static final String ALARM_HOUR = "alarm_hour";
            public static final String ALARM_MINUTE = "alarm_minute";
            public static final String HAS_ALARM = "has_alarm";
            public static final String DATE = "date";
        }
    }
}
