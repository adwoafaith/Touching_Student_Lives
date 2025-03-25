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

    public static Response GetAllQuestionsEndpoint() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");

        Response response = request.when().get(Routes.getAllQuestions);
        return response;
    }
    public static Response GetSingleQuestionEndpoint(String question_id) {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .pathParam("question_id",question_id)
                .header("x-client-type", "web");

        Response response = request.when().post(Routes.getQuestionById);
        return response;
    }
    public static Response deleteQuestionsEndpoint(String question_id) {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .pathParam("question_id",question_id)
                .header("x-client-type", "web");

        Response response = request.when().get(Routes.deleteQuestion);
        return response;
    }


}
