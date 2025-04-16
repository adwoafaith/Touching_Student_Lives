package endpoints;

import Payload.RafflePayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class InstitutionsEndpoint {

    public static Response getInstitutions(){
    RequestSpecification request = given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + authToken)
            .header("Connection", "keep-alive")
            .header("x-client-type", "web");
    Response response = request.when().get(Routes.getInstitutions);
        return response;
}
}