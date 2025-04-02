package endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class EventsTestEndpoint {

    public static Response createEvent(Map<String, Object> formData) {
        RequestSpecification request = given()
                .header("Authorization", "Bearer " + authToken)
                .contentType("multipart/form-data");

        // Add form-data parameters
        // Add all form parts
        for (Map.Entry<String, Object> entry : formData.entrySet()) {
            if (entry.getValue() instanceof File) {
                File file = (File) entry.getValue();
                request.multiPart(entry.getKey(), file, "image/jpeg");  // Explicit MIME type
            } else {
                request.multiPart(entry.getKey(), entry.getValue().toString());
            }
        }

        return request.post(Routes.creatingEventsPost);
    }
}
