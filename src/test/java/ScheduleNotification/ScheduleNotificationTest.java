package ScheduleNotification;

import io.restassured.response.Response;
import org.testng.annotations.Test;


public class ScheduleNotificationTest {

    @Test
    public void getAllScheduledNotification() {

        Response response = ScheduleNotificationEndpoint.getNotificationTemplateEndpoint(); //send API request
        response.then().log().body();

    }
}
