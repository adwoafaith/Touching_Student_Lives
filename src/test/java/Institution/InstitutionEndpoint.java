package Institution;

import endpoints.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static SuperAdmin.SuperAdminLogin.authToken;
import static io.restassured.RestAssured.given;

public class InstitutionEndpoint {
    public static Response getAllInstitutions(){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+authToken)
                .header("Connection","keep-alive")
                .header("x-client-type","web")
                .when()
                .get(Routes.getAllInstitutions);
        return response;

    }
    public static Response createInstitution(String data){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+authToken)
                .header("Connection","keep-alive")
                .header("x-client-type","web")
                .body(data)
                .when()
                .post(Routes.createInstitution);
        return response;

    }
    public static Response getSingleInstitution(String institutionId){
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+authToken)
                .header("Connection","keep-alive")
                .header("x-client-type","web")
                .pathParam("institution_id",institutionId)
                .when()
                .get(Routes.getSingleInstitution);
        return response;

    }


}
