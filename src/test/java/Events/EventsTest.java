package Events;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class EventsTest {
    public static String eventId;

    @Test(priority = 1, dataProvider = "eventDataProvider", dataProviderClass = EventDataProvider.class)
    public void createEventTest(String payload) {


        Response response = EventsEndpoint.createEvent(payload);
        eventId = response.jsonPath().getString("id");
        if(response.getStatusCode() != 201) {
            System.out.println("Full Response:");
            response.then().log().body();
        }

        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 201!");
        response.then().log().body();

        String title = response.jsonPath().getString("title");
        String status = response.jsonPath().getString("status");

        Assert.assertEquals(title,"Tech Conference 2025", "Title does not match");
        Assert.assertEquals(status,"active", "status does not match");

    }

    @Test(priority = 2)
    public void deleteEventPost() {
        Response deleteResponse = EventsEndpoint.deleteEventPosts(eventId);
        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Failed to delete post! Expected 200 but got " + deleteResponse.getStatusCode());

    }
}