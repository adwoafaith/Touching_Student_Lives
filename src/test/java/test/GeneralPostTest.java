package test;

import Payload.GeneralPostPayload;
import dataProvider.GeneralPostDataProvider;
import endpoints.GeneralPostsEndpoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GeneralPostTest {
    private static String generalPostId;

    @Test(dataProvider = "generalPostDataProvider", dataProviderClass = GeneralPostDataProvider.class)
    public void createGeneralPost(Map<String, String> formData){

        // Send API request
        Response response = GeneralPostsEndpoint.createGeneralPosts(formData);


        generalPostId = response.jsonPath().getString("[0].id");  // Extracts the first post's ID
        System.out.println("Extracted General Post ID: " + generalPostId);

        // Basic assertion - Check if status code is 200
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 200!");
        response.then().log().body();

        // Check if title property exists in the response
        Assert.assertNotNull(response.jsonPath().getString("title"), "Title property is missing!");

        // Check if type is 'general'
        Assert.assertEquals(response.jsonPath().getString("[0].type"), "general", "Type is not 'general'!");

    }

    @Test(dependsOnMethods = "GeneralPostTest")  // Ensures delete runs after create
    public void deleteGeneralPost() {
        Response deleteResponse = GeneralPostsEndpoint.deleteGeneralPosts(generalPostId);
        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Failed to delete post! Expected 200 but got " + deleteResponse.getStatusCode());

    }

}
