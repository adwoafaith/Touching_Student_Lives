package Organizations;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class OrganizationsTest{

    public static String organizationId;
    public static String organizationName;
    public static String organizationEmail;


    public static List<OrganizationPayload> createdOrganizations = new ArrayList<>();

    @Test(priority = 1)
    public void getAllOrganizationsTest() {
        Response response = OrganizationsEndpoint.getOrganizations();

        Assert.assertEquals(response.statusCode(),200);

        JsonPath jsonPath =response.jsonPath();
        List<Map<String, Object>> users = jsonPath.getList("");

        //check the size of data in the array
        if(users.size()>1){
            Map<String, Object> secondUser = users.get(1);
            organizationId = (String) secondUser.get("id");
            organizationName = (String) secondUser.get("name");

        }

    }

    @Test(priority = 2, dataProvider = "organizationPayloadProvider",dataProviderClass = OrganizationDataProvider.class)
    public void createOrganizationsTest(String organizationPayload) {

        Response response = OrganizationsEndpoint.createOrganizations(organizationPayload);
        response.then().log().body();
        Assert.assertEquals(response.statusCode(),201);

        organizationEmail = response.jsonPath().getString("email");
        organizationId =  response.jsonPath().getString("id");




    }

    //@Test(dataProvider = "updatedOrganizationPayloadProvider", dataProviderClass = UpdateOrganizationDataProvider.class)
    public void updateOrganizationsTest(String organizationId, String organizationPayload) {
        Response response = OrganizationsEndpoint.updateOrganizations(organizationPayload, organizationId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);

        String updatedEmail = response.jsonPath().getString("email");
        String updatedId = response.jsonPath().getString("user_id");

        System.out.println("✅ Updated Organization ID: " + updatedId);
        System.out.println("📬 Updated Organization Email: " + updatedEmail);
    }

    @Test(priority = 3)
    public void deleteOrganizationsTest() {
        Response response = OrganizationsEndpoint.deleteOrganizations(organizationId);
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 201 but got something else");
        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message.contains(organizationId), " Message does not contain the expected organization ID");

        // Extract result object
        List<Map<String, Object>> resultList = response.jsonPath().getList("result");
        Assert.assertFalse(resultList.isEmpty(), "Result list is empty. No organization info found.");

        Map<String, Object> orgData = resultList.get(0);

        String returnedOrgId = (String) orgData.get("id");
        String email = (String) orgData.get("email");
        String userId = (String) orgData.get("user_id");
        String deleted = (String) orgData.get("deleted");

        // Assert essential fields
        Assert.assertEquals(returnedOrgId, organizationId, "Organization ID mismatch");
        Assert.assertNotNull(email, "Email should not be null");
        Assert.assertNotNull(userId, "User ID should not be null");
        Assert.assertEquals(deleted, "false", "Deleted flag should be 'false'");

    }


}
