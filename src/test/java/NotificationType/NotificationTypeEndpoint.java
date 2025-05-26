package NotificationType;

import Routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static WebAdminLoginTest.LoginTest.authToken;
import static io.restassured.RestAssured.given;

public class NotificationTypeEndpoint {

    public static Response getNotificationType() {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .contentType(ContentType.JSON);

        return request.when().get(Routes.getNotificationtype);
    }
}
