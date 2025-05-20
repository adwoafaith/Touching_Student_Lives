package NotificationType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NotificationTypeTest {

    public static String announcementId;
    public static String announcementName;


    @Test()
    public void getNotificationTypes() {
        Response response = NotificationTypeEndpoint.getNotificationType();
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        JsonPath json = response.jsonPath();
        boolean isSuccess = json.getBoolean("success");
        Assert.assertTrue(isSuccess, "success is not true");

        int totalCount = json.getList("data").size(); //get the lenght of the response
        System.out.println("total count : " + totalCount);


        for (int i = 0; i < totalCount; i++) {
            String code = json.getString("data[" + i + "].code");
            if ("announcement".equals(code)) {
                announcementId = json.getString("data[" + i + "].id");
                announcementName = json.getString("data[" + i + "].name");
                break;
            }


        }
        Assert.assertNotNull(announcementId, "Announcement ID should not be null");
        Assert.assertNotNull(announcementName, "Announcement name should not be null");
    }
}
