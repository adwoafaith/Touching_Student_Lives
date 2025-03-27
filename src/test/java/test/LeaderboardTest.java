package test;

import endpoints.LeaderboadEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeaderboardTest {

    @Test(priority = 1)
    public void leaderBoardDailyTest(){

        Response response = LeaderboadEndpoint.leaderBoardDailyEndpoint();
        response.prettyPrint();

        Assert.assertNotNull("data.student_id","student id is not present");
        Assert.assertNotNull("data.first_name","Name is not present");
        Assert.assertNotNull("data.rank","rank is not present");
        Assert.assertNotNull("data.level","Name is not present");
    }

    @Test(priority = 2)
    public void leaderBoardWeeklyTest(){

        Response response = LeaderboadEndpoint.leaderBoardWeeklyEndpoint();
        response.prettyPrint();

        Assert.assertNotNull("data.student_id","student id is not present");
        Assert.assertNotNull("data.first_name","Name is not present");
        Assert.assertNotNull("data.rank","rank is not present");
        Assert.assertNotNull("data.level","Name is not present");
    }


    @Test(priority = 3)
    public void leaderBoardQuarterlyTest(){

        Response response = LeaderboadEndpoint.leaderBoardQuarterlyEndpoint();
        response.prettyPrint();

        Assert.assertNotNull("data.student_id","student id is not present");
        Assert.assertNotNull("data.first_name","Name is not present");
        Assert.assertNotNull("data.rank","rank is not present");
        Assert.assertNotNull("data.level","Name is not present");
    }

    @Test(priority = 4)
    public void leaderBoardMonthlyTest(){

        Response response = LeaderboadEndpoint.leaderBoardMonthlyEndpoint();
        response.prettyPrint();

        Assert.assertNotNull("data.student_id","student id is not present");
        Assert.assertNotNull("data.first_name","Name is not present");
        Assert.assertNotNull("data.rank","rank is not present");
        Assert.assertNotNull("data.level","Name is not present");
    }


    @Test(priority = 5)
    public void leaderBoardYearlyTest(){

        Response response = LeaderboadEndpoint.leaderBoardYearlyEndpoint();
        response.prettyPrint();

        Assert.assertNotNull("data.student_id","student id is not present");
        Assert.assertNotNull("data.first_name","Name is not present");
        Assert.assertNotNull("data.rank","rank is not present");
        Assert.assertNotNull("data.level","Name is not present");
    }


    @Test(priority = 6)
    public void leaderBoardFristQuarterTest(){

        Response response = LeaderboadEndpoint.leaderBoardFristQuarterEndpoint();
        response.prettyPrint();

        Assert.assertNotNull("data.student_id","student id is not present");
        Assert.assertNotNull("data.first_name","Name is not present");
        Assert.assertNotNull("data.rank","rank is not present");
        Assert.assertNotNull("data.level","Name is not present");
    }
    @Test(priority = 6)
    public void leaderBoardSecondQuarterTest(){

        Response response = LeaderboadEndpoint.leaderBoardSecondQuarterEndpoint();
        response.prettyPrint();

        Assert.assertNotNull("data.student_id","student id is not present");
        Assert.assertNotNull("data.first_name","Name is not present");
        Assert.assertNotNull("data.rank","rank is not present");
        Assert.assertNotNull("data.level","Name is not present");
    }
}
