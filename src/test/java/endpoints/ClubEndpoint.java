package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class ClubEndpoint {
    public static Response getClubs(){
        Response response = given()
                    .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ authToken)
                    .header("Connection","keep-alive")
                    .header("x-client-type","web")
                .when()
                .get(Routes.getClubs);
        return response;

    }

    public static Response createClub(){

    }
}
