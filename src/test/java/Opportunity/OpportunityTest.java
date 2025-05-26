package Opportunity;

import Organizations.UpdateOpportunityDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class OpportunityTest {
    private static String opportunityId;

    //@Test(priority = 1, dependsOnMethods = "test.LoginTest.loginAndVerifyMagicLink", dataProvider = "opportunityDataProvider", dataProviderClass = OpportunityDataProvider.class)
    public void createOpportunityTest(Map<String, String> formData){
        Response response = OpportunityEndpoint.createOpportunity(formData);

        // Extract postId instead of id
        List<Map<String, Object>> responseBody = response.jsonPath().getList("");
        Assert.assertNotNull(responseBody, "Response body should not be null");

        opportunityId = (String) responseBody.get(0).get("postId"); // Extract correct ID
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201, "Opportunity creation failed!");


    }

   // @Test(priority = 2, dependsOnMethods = "createOpportunityTest", dataProvider = "UpdateOpportunityDataProvider", dataProviderClass = UpdateOpportunityDataProvider.class)
    public void updateOpportunityTest(Map<String, String> formData){
            Assert.assertNotNull(opportunityId, "Opportunity ID should not be null before update");

            Response response = OpportunityEndpoint.updateOpportunity(opportunityId, formData);

            Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for successful update");

          }

}
