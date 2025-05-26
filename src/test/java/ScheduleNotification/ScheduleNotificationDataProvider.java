package ScheduleNotification;

import org.testng.annotations.DataProvider;

public class ScheduleNotificationDataProvider {

    @DataProvider(name = "notificationData")
    public Object[][] notificationData() {
        return new Object[][] {
                { ScheduleNotificationPayload.validNotificationPayload() },
        };
    }


}
