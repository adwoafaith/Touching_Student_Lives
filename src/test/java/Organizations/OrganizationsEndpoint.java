package Organizations;

import Routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static SuperAdminLogin.SuperAdminLogin.authToken;
import static io.restassured.RestAssured.given;


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

    public static Response createOrganizations(String data){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+authToken)
                .header("Connection","keep-alive")
                .header("x-client-type","web")
                .body(data)
                .when()
                .post(Routes.createOrganizations);
        return response;

    }

    public static Response updateOrganizations(String data, String organizationId){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+authToken)
                .header("Connection","keep-alive")
                .header("x-client-type","web")
                .body(data)
                .pathParam("organization_id",organizationId)
                .when()
                .post(Routes.updateOrganizations);
        return response;

    }
}
