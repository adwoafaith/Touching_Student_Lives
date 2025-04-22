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
    // Static list to store the created organizations
    public static List<OrganizationPayload> createdOrganizations = new ArrayList<>();

    //@Test
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

    //@Test(dataProvider = "organizationPayloadProvider",dataProviderClass = OrganizationDataProvider.class)
    public void createOrganizationsTest(String organizationPayload) {

        Response response = OrganizationsEndpoint.createOrganizations(organizationPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),201);

        organizationEmail = response.jsonPath().getString("email");
        organizationId =  response.jsonPath().getString("user_id");

        System.out.println("Created Organization ID: " + organizationId);
        System.out.println("Created Organization Email: " + organizationEmail);



    }

    @Test(dataProvider = "updatedOrganizationPayloadProvider", dataProviderClass = UpdateOrganizationDataProvider.class)
    public void updateOrganizationsTest(String organizationId, String organizationPayload) {
        Response response = OrganizationsEndpoint.updateOrganizations(organizationPayload, organizationId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);

        String updatedEmail = response.jsonPath().getString("email");
        String updatedId = response.jsonPath().getString("user_id");

        System.out.println("✅ Updated Organization ID: " + updatedId);
        System.out.println("📬 Updated Organization Email: " + updatedEmail);
    }

}
