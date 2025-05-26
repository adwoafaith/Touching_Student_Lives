package ScheduleNotification;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static NotificationType.NotificationTypeTest.notificationTypeId;


public class ScheduleNotificationTest {

    public static String scheduleNotificationId;
    @Test(priority = 2)
    public void getAllScheduledNotification() {
        Response response = ScheduleNotificationEndpoint.getNotificationTemplateEndpoint(); //send API request
        response.then().log().body();

    }

    @Test(priority = 1, dataProvider = "notificationData", dataProviderClass = ScheduleNotificationDataProvider.class)
    public void testScheduleNotification(Map<String, Object> payload) {
        Response response = ScheduleNotificationEndpoint.scheduleNotification(payload);

        JsonPath jsonPath = response.jsonPath();
        response.then().log().body();

        String message = jsonPath.getString("message");
        String title = jsonPath.getString("data.title");
        String status = jsonPath.getString("data.status");

        String expectedTitle = "Automation notification";
        String expectedStatus = "pending";

        String notification_type_id = jsonPath.getString("data.notification_type_id");
        scheduleNotificationId = jsonPath.getString("data.id");
        System.out.println(scheduleNotificationId);

        Assert.assertEquals(title, expectedTitle, "Title mismatch");
        Assert.assertEquals(message, "Notification scheduled successfully","message validation failed");
        Assert.assertEquals(notification_type_id,notificationTypeId," id does not match");
        Assert.assertEquals(expectedStatus,status,"Status should be pending");
        response.then().statusCode(201);

    }

    @Test(priority = 3)
    public void cancelScheduledNotification() {
        Response response = ScheduleNotificationEndpoint.cancleNotification(scheduleNotificationId); //send API request
        response.then().log().body();

        Assert.assertEquals(response.statusCode(),200, "Expected status code is 200");
        boolean success = response.jsonPath().getBoolean("success");
        String message = response.jsonPath().getString("message");

        Assert.assertEquals(message, "Notification cancelled successfully", "Message mismatch");
        Assert.assertTrue(success,"Expected 'success' to be true");


    }

}
