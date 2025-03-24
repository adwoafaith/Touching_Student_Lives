package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class QuestionBankEndpoint {

    public static Response createQuestionBank(Map<String, String> formData) {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .contentType(ContentType.JSON)
                .body(formData);
        return request.when().post(Routes.createQuestionBank);

    }
}
