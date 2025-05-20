package NotificationHistory;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NotificationHistoryTest {

    @Test(priority = 2)
    public void getAllNotificationHistory() {
        Response response = NotificationHistoryEndpoint.getNotificationHistory(); //send API request

        List<Map<String, Object>> dataList = response.jsonPath().getList("data");
        response.then().log().body();

        //expected titles
        List<String> expectedTitles = Arrays.asList(
                "New Quiz",
                "Active Quiz",
                "New Post",
                "New Event",
                "New Opportunity",
                "General Announcement"
        );

        //extract all the title from the body
        List<String> actualTitles = new ArrayList<>();
        for (Map<String, Object> item : dataList) {
            Map<String, Object> notificationType = (Map<String, Object>) item.get("notification_type");
            String title = (String) notificationType.get("name");
            actualTitles.add(title);
        }

        Assert.assertEquals(dataList.size(), 6, "Expected 6 notification types");
        boolean isSuccess = response.jsonPath().getBoolean("success");
        Assert.assertTrue(isSuccess, "Expected 'success' to be true");
        for (String expected : expectedTitles) {
            Assert.assertTrue(actualTitles.contains(expected), "Missing title: " + expected);
        }





    }
}
