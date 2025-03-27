package endpoints;

import Payload.QuizPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class LeaderboadEndpoint {

    public static Response leaderBoardDailyEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParams("page", 1, "limit", 10, "order", "desc", "period","daily")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");
        Response response = request.when().get(Routes.leaderboard);
        return response;
    }

    public static Response leaderBoardWeeklyEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParams("page", 1, "limit", 10, "order", "desc", "period","weekly")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");
        Response response = request.when().get(Routes.leaderboard);
        return response;
    }
    public static Response leaderBoardQuarterlyEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParams("page", 1, "limit", 10, "order", "desc", "period","quarterly")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");
        Response response = request.when().get(Routes.leaderboard);
        return response;
    }

    public static Response leaderBoardMonthlyEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParams("page", 1, "limit", 10, "order", "desc", "period","monthly")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");
        Response response = request.when().get(Routes.leaderboard);
        return response;
    }


    public static Response leaderBoardYearlyEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParams("page", 1, "limit", 10, "order", "desc", "period","yearly")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");
        Response response = request.when().get(Routes.leaderboard);
        return response;
    }
    public static Response leaderBoardFristQuarterEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParams("page", 1, "limit", 10, "order", "desc", "period","first-quarter")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");
        Response response = request.when().get(Routes.leaderboard);
        return response;
    }

    public static Response leaderBoardSecondQuarterEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParams("page", 1, "limit", 10, "order", "desc", "period","second-quarter")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");
        Response response = request.when().get(Routes.leaderboard);
        return response;
    }
    public static Response leaderBoardThirdQuarterEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParams("page", 1, "limit", 10, "order", "desc", "period","third-quarter")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");
        Response response = request.when().get(Routes.leaderboard);
        return response;
    }

    public static Response leaderBoardFourthQuarterEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParams("page", 1, "limit", 10, "order", "desc", "period","fourth-quarter")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");
        Response response = request.when().get(Routes.leaderboard);
        return response;
    }
    public static Response leaderBoardAllTimeQuarterEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParams("page", 1, "limit", 10, "order", "desc", "period","all-time")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");
        Response response = request.when().get(Routes.leaderboard);
        return response;
    }
}
