package WaitList;

import Routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static WebAdminLoginTest.LoginTest.authToken;

public class WaitListEndpoint {
    public static Response getWaitList() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .queryParam("limit",100)
                .header("x-client-type", "web");

        Response response = request.when().get(Routes.getWaitList);
        return response;

    }
}