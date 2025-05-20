package ScheduleNotification;

import Routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static WebAdminLoginTest.LoginTest.authToken;
import static io.restassured.RestAssured.given;

public class ScheduleNotificationEndpoint {
    public static Response getNotificationTemplateEndpoint() {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .contentType(ContentType.JSON);

        return request.when().get(Routes.getAllScheduledNotification);
    }

    public static Response scheduleNotification(Object payload) {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .contentType(ContentType.JSON)
                .body(payload);


        return request.when().post(Routes.scheduleNotification);
    }


    public static Response cancleNotification(String cancelNotification) {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .pathParam("cancel_notification",cancelNotification)
                .contentType(ContentType.JSON);



        return request.when().delete(Routes.deleteScheduledNotification);
    }

}
