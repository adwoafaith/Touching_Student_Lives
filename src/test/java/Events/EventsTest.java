package Events;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class EventsTest {
    public static String eventId;

    @Test(priority = 1, dataProvider = "eventDataProvider", dataProviderClass = EventDataProvider.class)
    public void createEventTest(Map<String, Object> formData) {


        Response response = EventsEndpoint.createEvent(formData);
        eventId = response.jsonPath().getString("id");
        if(response.getStatusCode() != 201) {
            System.out.println("Full Response:");
            response.then().log().body();
        }

        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 201!");
        response.then().log().body();

        Assert.assertEquals(
                response.jsonPath().getString("title"),
                formData.get("title").toString(),  // Added .toString() for Object
                "Title doesn't match!"
        );
        Assert.assertEquals(
                response.jsonPath().getString("status"),
                formData.get("status").toString(),  // Added .toString() for Object
                "Status doesn't match!"
        );
    }

    @Test(priority = 2)
    public void deleteEventPost() {
        Response deleteResponse = EventsEndpoint.deleteEventPosts(eventId);
        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Failed to delete post! Expected 200 but got " + deleteResponse.getStatusCode());

    }
}