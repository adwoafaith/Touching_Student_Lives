package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class QuestionsEndpoint {

    public static Response createQuestionsEndpoint(String question) {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .body(question)
                .header("x-client-type", "web");

        Response response = request.when().post(Routes.createQuestion);
        return response;
    }

}
