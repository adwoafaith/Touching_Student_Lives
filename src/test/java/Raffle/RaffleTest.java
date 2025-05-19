package Raffle;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RaffleTest {
    public static String raffleId;

    @Test(priority = 1, dataProvider = "raffleData", dataProviderClass = RaffleDataProvider.class)

    public void CreateRaffleTest(RafflePayload rafflePayload) {
        Response response = RaffleEndpoint.CreateRaffleEndpoint(rafflePayload);
        raffleId = response.jsonPath().getString("summary.raffleId");
        System.out.println(raffleId);
        response.prettyPrint();

        String status = response.jsonPath().getString("status");
        String successMessage = response.jsonPath().getString("message");
        Assert.assertEquals(response.statusCode(),201,"Status code does not match");
        Assert.assertEquals(status,"success","Raffle not created");
        Assert.assertEquals(successMessage,"Raffle executed instantly and winners selected successfully.","Message does not match");

    }
    @Test(priority = 2)
    public void getActiveRaffleTest() {
        Response response = RaffleEndpoint.getActiveRaffleEndpoint();
        response.prettyPrint();

       int totalRafflecount = response.jsonPath().getInt("total"); //get total number of raffles

        if(totalRafflecount==0){
            Assert.assertEquals(response.statusCode(),200, "Status code does not match when no raffles exists"); //check if there are no availble raffle return a status code of 200
        }
        else{
            String raffleStatus = response.jsonPath().getString("data[0].status");
            Assert.assertEquals(raffleStatus,"active","Raffle status is not active");//assert that status is active
            Assert.assertEquals(totalRafflecount,totalRafflecount,"There is no raffle");
        }

    }

   @Test(priority = 3)
    public void getExpiredRaffleTest() {
        Response response = RaffleEndpoint.getExpiredRaffleEndpoint();
        response.prettyPrint();

        int totalRafflecount = response.jsonPath().getInt("total"); //get total number of raffles

        if(totalRafflecount==0){
            Assert.assertEquals(response.statusCode(),200, "Status code does not match when no raffles exists"); //check if there are no availble raffle return a status code of 200
        }
        else{
            String raffleStatus = response.jsonPath().getString("data[0].status");
            Assert.assertEquals(raffleStatus,"expired","Raffle status is not expired");//assert that status is active
            Assert.assertEquals(totalRafflecount,totalRafflecount,"There is no raffle");
        }

    }

    @Test(priority = 3, dependsOnMethods = "CreateRaffleTest" )
    public void getRaffleParticipantDetailsTest() {
        Response response = RaffleEndpoint.getDetailedRaffleParticipantsEndpoint(raffleId);
        response.prettyPrint();

        String raffleIdINResponse = response.jsonPath().getString("data[0].raffleId");

        Assert.assertNotNull(response.jsonPath().get("data[0].id"),"Id is not available");
        Assert.assertEquals(raffleIdINResponse,raffleId,"Raffle Id's does not match");

    }

    @Test(priority = 4)
    public void getAllRaffleTest() {
        Response response = RaffleEndpoint.getAllRaffleEndpoint();
        response.prettyPrint();

        String raffleIdINResponse = response.jsonPath().getString("data[0].id");
        String name = response.jsonPath().getString("data[0].name");

        Assert.assertNotNull(raffleIdINResponse,"Id is not available");
        Assert.assertNotNull(name,"Name property is available");

    }


    @Test(priority = 5)
    public void getAllRaffleWinnersTest() {
        Response response = RaffleEndpoint.getAllRaffleWinnersEndpoint();
        response.prettyPrint();

        String Id = response.jsonPath().getString("data[0].id");
        String raffleIdInResponse = response.jsonPath().getString("data[0].raffleId");
        String studentId = response.jsonPath().getString("data[0].participant.studentId");

        Assert.assertNotNull(raffleIdInResponse,"Id is not available");
        Assert.assertNotNull(Id,"Id attribute is not available");
        Assert.assertNotNull(studentId,"student id  attribute is not available");

    }
    }