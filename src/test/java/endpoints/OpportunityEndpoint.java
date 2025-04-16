package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class OpportunityEndpoint {

    public static Response createOpportunity(Map<String, String> formData) {
        // Initialize the request
        RequestSpecification request = given()
                .contentType(ContentType.MULTIPART)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");

        // Add form-data dynamically
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            request.multiPart(entry.getKey(), entry.getValue());
        }

        // Send the request and return the response
        return request.when().post(Routes.createOpportunity);
    }

    public static Response updateOpportunity(String opportunityId, Map<String, String> formData) {
        RequestSpecification request = given()
                .contentType(ContentType.MULTIPART)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web")
                .pathParam("post_Id", opportunityId);

        // Add updated form-data dynamically
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            request.multiPart(entry.getKey(), entry.getValue());
        }

        Response response = request.when().put(Routes.updateOpportunity);

        return response;
    }
}
