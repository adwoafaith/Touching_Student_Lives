package test;

import endpoints.LeaderboadEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeaderboardTest {

    @Test(priority = 1)
    public void leaderBoardDailyTest(){

        Response response = LeaderboadEndpoint.leaderBoardDalyEndpoint();
        response.prettyPrint();

        Assert.assertNotNull("data.student_id","student id is not present");
        Assert.assertNotNull("data.first_name","Name is not present");
        Assert.assertNotNull("data.rank","rank is not present");
        Assert.assertNotNull("data.level","Name is not present");
    }
}
