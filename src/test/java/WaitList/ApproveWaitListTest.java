package WaitList;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ApproveWaitListTest {

    @Test(dataProvider = "userIdsProvider", dataProviderClass = WaitListDataProvider.class, priority = 2)
    public void approveWaitList(List<String> userIds) {
        WaitListPayload payload = new WaitListPayload(userIds);

        Response response = ApproveWaitListEndpoint.approveWaitList(payload);
        response.then().log().body();

        String message = response.jsonPath().getString("message");

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 not received");
        Assert.assertEquals(message, "User approved successfully", "Message does not match, please try again later");
    }

}
