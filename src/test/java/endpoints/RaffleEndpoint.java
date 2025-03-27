package endpoints;

import Payload.RafflePayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static test.LoginTest.authToken;

public class RaffleEndpoint {
    public static Response CreateRaffleEndpoint(RafflePayload rafflePayload){
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .body(rafflePayload)
                .header("x-client-type", "web");

        Response response = request.when().post(Routes.createRaffle);
        return response;
    }
}
