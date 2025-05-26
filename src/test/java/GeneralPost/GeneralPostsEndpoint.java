package GeneralPost;

import Routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static WebAdminLoginTest.LoginTest.authToken;

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
    public static Response updateGeneralPosts(String generalPostId,Map<String, String> formData) {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .pathParams("updatePostId",generalPostId)
                .body(formData)
                .contentType(ContentType.JSON);

        return request.when().put(Routes.updateGeneralPost);
    }
    public static Response deleteGeneralPosts(String generalPostId) {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .pathParams("deletePostId",generalPostId)
                .contentType(ContentType.JSON);

        return request.when().delete(Routes.deleteGeneralPost);
    }
}
