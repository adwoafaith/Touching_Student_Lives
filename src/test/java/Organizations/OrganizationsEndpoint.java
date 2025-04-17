package Organizations;

import endpoints.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class OrganizationsEndpoint {

    public static Response getOrganizations(){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection","keep-alive")
                .header("x-client-type","web")
                .when()
                .get(Routes.getAllOrganizations);
        return response;

    }
}
