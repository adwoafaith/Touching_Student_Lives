package endpoints;

import Payload.QuizPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class QuizEndpoints {
    public static Response createQuiz(QuizPayload payload) {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .body(payload);

        Response response = request.when().post(Routes.createQuiz);
        return response;
    }
}
