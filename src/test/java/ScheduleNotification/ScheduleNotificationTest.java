package ScheduleNotification;

import NotificationTemplate.NotificationTemplateEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ScheduleNotificationTest {

    @Test
    public void getAllScheduledNotification() {

        Response response = NotificationTemplateEndpoint.getNotificationTemplateEndpoint(); //send API request

        List<Map<String, Object>> templates = response.jsonPath().getList("data"); ///extract body into a variable
        response.then().log().body();

        int expectedTemplateCount = 7;
        Assert.assertEquals(templates.size(), expectedTemplateCount, "Template count mismatch");
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertNotNull(response.getBody(), "Response body should not be null");  /// Validate response is not null

        /// Loop through and check essential fields
        for (Map<String, Object> template : templates) {
            Assert.assertNotNull(template.get("id"), "Template id is missing");
            Assert.assertNotNull(template.get("name"), "Template name is missing");
            Assert.assertNotNull(template.get("description"), "Template description is missing");
            Assert.assertNotNull(template.get("email_subject_template"), "Email subject template is missing");


        }
    }
}
