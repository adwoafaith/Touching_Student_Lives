package test;

import dataProvider.GeneralPostDataProvider;
import dataProvider.UpdateGeneralPostDataProvider;
import endpoints.GeneralPostsEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.Map;

public class GeneralPostTest {
    private static String generalPostId;

    @Test(dataProvider = "generalPostDataProvider", dataProviderClass = GeneralPostDataProvider.class)
    public void createGeneralPost(Map<String, String> formData){

        // Send API request
        Response response = GeneralPostsEndpoint.createGeneralPosts(formData);


        generalPostId = response.jsonPath().getString("[0].id");  // Extracts the first post's ID
        System.out.println("Extracted General Post ID: " + generalPostId);

        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 200!");
        response.then().log().body();
        Assert.assertNotNull(response.jsonPath().getString("title"), "Title property is missing!");// Check if title property exists in the response
        Assert.assertEquals(response.jsonPath().getString("[0].type"), "general", "Type is not 'general'!");  // Check if type is 'general'

    }

    @Test(dataProvider = "updatePostDataProvider", dataProviderClass = UpdateGeneralPostDataProvider.class)
    public void updateGeneralPost(Map<String, String> formData){


        Response response = GeneralPostsEndpoint.createGeneralPosts(formData);   // Send API request
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 200!");
        response.then().log().body();
        Assert.assertNotNull(response.jsonPath().getString("title"), "Title property is missing!");// Check if title property exists in the response
        Assert.assertEquals(response.jsonPath().getString("[0].type"), "general", "Type is not 'general'!");// Check if type is 'general'

    }

    @Test(dependsOnMethods = "updateGeneralPost")  // Ensures delete runs after create
    public void deleteGeneralPost() {
        Response deleteResponse = GeneralPostsEndpoint.deleteGeneralPosts(generalPostId);
        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Failed to delete post! Expected 200 but got " + deleteResponse.getStatusCode());

    }

}
