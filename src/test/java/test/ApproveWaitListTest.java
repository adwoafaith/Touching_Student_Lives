package test;

import endpoints.ApproveWaitListEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static test.WaitListTest.waitListId;

public class ApproveWaitListTest {
    @Test()
    public void approveWaitList(){

        Response response = ApproveWaitListEndpoint.approveWaitList(waitListId);
        response.then().log().body();

        String message = response.jsonPath().getString("message");

        //perform assertions
        Assert.assertEquals(response.getStatusCode(), 200,"Expected status code 200 not received");
        Assert.assertEquals(message,"User approved successfully","Message does not match please try again later");
    }

}
