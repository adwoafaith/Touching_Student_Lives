package test;

import endpoints.EventsTestEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Map;
import dataProvider.EventDataProvider;

public class EventsTest {
    @Test(dataProvider = "eventDataProvider", dataProviderClass = EventDataProvider.class)
    public void createEventTest(Map<String, Object> formData) {  // Changed from Map<String, String>

        File imageFile = new File("src/main/resources/1742285372304.jpeg");
        System.out.println("File exists: " + imageFile.exists());

        System.out.println("Request Payload: " + formData);
        Response response = EventsTestEndpoint.createEvent(formData);

        if(response.getStatusCode() != 201) {
            System.out.println("Full Response:");
            response.then().log().all();
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
}