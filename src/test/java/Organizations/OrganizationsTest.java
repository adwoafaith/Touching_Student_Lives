package Organizations;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class OrganizationsTest{
    public static String organizationId;
    public static String organizationName;

    @Test
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
}
