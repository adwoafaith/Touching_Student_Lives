package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class WaitListEndpoint {
    public static Response getWaitListEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParam("status", "expired")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");

        Response response = request.when().get(Routes.getWaitList);
        return response;

    }
}