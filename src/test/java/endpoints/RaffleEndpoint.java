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
    public static Response getExpiredRaffleEndpoint(){
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParam("status","expired")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");

        Response response = request.when().get(Routes.getRaffleStatus);
        return response;
    }
    public static Response getActiveRaffleEndpoint(){
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParam("status","active")
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");

        Response response = request.when().get(Routes.getRaffleStatus);
        return response;
    }
    public static Response getDetailedRaffleParticipantsEndpoint(String raffleId){
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .queryParam("raffleId",raffleId)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");

        Response response = request.when().get(Routes.getRaffleParticipantsDetails);
        return response;
    }

    public static Response getAllRaffleEndpoint(){
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");

        Response response = request.when().get(Routes.getAllRaffle);
        return response;
    }
    public static Response getAllRaffleWinnersEndpoint(){
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .header("Connection", "keep-alive")
                .header("x-client-type", "web");

        Response response = request.when().get(Routes.getAllRaffleWinners);
        return response;
    }
}
