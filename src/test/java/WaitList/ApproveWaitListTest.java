package WaitList;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ApproveWaitListTest {

    @Test(dataProvider = "userIdsProvider", dataProviderClass = WaitListDataProvider.class, priority = 2)
    public void approveWaitList(List<String> userIds) {
        WaitListPayload payload = new WaitListPayload(userIds);

        Response response = ApproveWaitListEndpoint.approveWaitList(payload);
        response.then().log().body();

        int total = response.jsonPath().getInt("total");
        int statusCode = response.jsonPath().getInt("statusCode");
        List<Map<String, Object>> data = response.jsonPath().getList("data");

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "HTTP status code is not 200");
        Assert.assertEquals(statusCode, 200, "Response 'statusCode' field is not 200");
        Assert.assertEquals(total, userIds.size(), "'total' field doesn't match submitted user count");

        for (Map<String, Object> user : data) {
            String state = (String) user.get("state");
            Assert.assertEquals(state, "inactive", "User state should be 'inactive'");
        }
    }

}
