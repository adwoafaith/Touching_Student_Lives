package Events;

import Routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static WebAdminLoginTest.LoginTest.authToken;

public class EventsEndpoint {

    public static Response createEvent(String payload) {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("x-client-type", "web")
                .body(payload)
                .contentType("multipart/form-data");
//
//        // Add form-data parameters
//        // Add all form parts
//        for (Map.Entry<String, Object> entry : formData.entrySet()) {
//            if (entry.getValue() instanceof File) {
//                File file = (File) entry.getValue();
//                request.multiPart(entry.getKey(), file, "image/jpeg");  // Explicit MIME type
//            } else {
//                request.multiPart(entry.getKey(), entry.getValue().toString());
//            }
//        }

        return request.post(Routes.creatingEventsPost);
    }

    public static Response deleteEventPosts(String eventPostId) {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .pathParams("deletePostId",eventPostId)
                .contentType(ContentType.JSON);

        return request.when().delete(Routes.deleteEventsPost);
    }
}
