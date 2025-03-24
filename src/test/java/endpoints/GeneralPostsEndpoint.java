package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class GeneralPostsEndpoint {

    public static Response createGeneralPosts(Map<String, String> formData) {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .contentType(ContentType.JSON)
                .body(formData);
        return request.when().post(Routes.createGeneralPost);
    }
    public static Response deleteGeneralPosts(String generaPostId) {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .pathParams("deletePostId",generaPostId)
                .contentType(ContentType.JSON);

        return request.when().delete(Routes.deleteGeneralPost);
    }


}
