package endpoints;

import Payload.QuizPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class LeaderboadEndpoint {

    public static Response leaderBoardDalyEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParams("page", 1, "limit", 10, "order", "desc", "period","daily")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");
        Response response = request.when().get(Routes.leaderboard);
        return response;
    }
}
