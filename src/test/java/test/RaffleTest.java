package test;

import Payload.RafflePayload;
import dataProvider.RaffleDataProvider;
import endpoints.RaffleEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RaffleTest {

    @Test(priority = 1, dataProvider = "raffleData", dataProviderClass = RaffleDataProvider.class)
    public void Raffle(RafflePayload rafflePayload) {
        Response response = RaffleEndpoint.CreateRaffleEndpoint(rafflePayload);
        response.prettyPrint();

        String status = response.jsonPath().getString("status");
        String successMessage = response.jsonPath().getString("message");
        Assert.assertEquals(response.statusCode(),201,"Status code does not match");
        Assert.assertEquals(status,"success","Raffle not created");
        Assert.assertEquals(successMessage,"Raffle executed instantly and winners selected successfully.","Message does not match");

    }
}