package test;

import endpoints.ClubEndpoint;
import endpoints.LoginEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClubTest {

    @Test(priority = 3)
    public void getClubTestCase(){
        Response response = ClubEndpoint.getClubs();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);

    }
}
