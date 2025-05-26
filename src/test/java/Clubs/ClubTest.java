package Clubs;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import WebAdminLoginTest.LoginTest;

import java.util.List;
import java.util.Map;

public class ClubTest {
    public static String clubAdminId;
    public static String institutionId;
    public static String countryId;

    @Test(priority = 1)
    public void getClubTestCase() {
        Response response = ClubEndpoint.getClubs();
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> clubs = jsonPath.getList("data");

        boolean clubFound = false;

        // Extract the admin's club details
        for (Map<String, Object> club : clubs) {
            Map<String, Object> createdBy = (Map<String, Object>) club.get("created_by");
            String clubAdminEmail = createdBy.get("email").toString();

            if (clubAdminEmail.equals(LoginTest.email)) {
                clubAdminId = club.get("club_admin").toString();
                institutionId = club.get("institution_id").toString();
                countryId = club.get("country_id").toString();

                System.out.println("Extracted club_admin: " + clubAdminId);
                System.out.println("Extracted institution_id: " + institutionId);
                System.out.println("Extracted country_id: " + countryId);

                clubFound = true;
                break;
            }
        }

        if (!clubFound) {
            throw new RuntimeException("No club found with the logged-in email as club_admin!");
        }
    }





    @Test(priority = 2, dataProvider = "clubDataProvider",dataProviderClass = ClubDataProvider.class)
    public void createClubTestCase(ClubPayload clubPayload) {
        Response response = ClubEndpoint.createClub(clubPayload);
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 201);

        clubAdminId = response.jsonPath().getString("id");
        String name = response.jsonPath().getString("name");
        String description = response.jsonPath().getString("description");

        Assert.assertNotNull(name,"Name is null");
        Assert.assertNotNull(description,"Description is null");

    }

    @Test(priority = 3, dependsOnMethods = "createClubTestCase")
    public void getClubTestCaseAfterCreation() {
        Response response = ClubEndpoint.getClubs();
        response.then().log().body();
        Assert.assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> updatedClubs = jsonPath.getList("data");
        boolean clubFound = false;

        for (Map<String, Object> club : updatedClubs) {
            String clubName = club.get("name").toString();

            if (clubName.equals("Automation Club")) {
                clubAdminId = club.get("id").toString(); // Assigning the ID to the class variable
                System.out.println("Newly Created Club ID: " + clubAdminId);
                clubFound = true;
                break;
            }
        }

        Assert.assertTrue(clubFound, "No new club found after creation!");
        Assert.assertNotNull(clubAdminId, "Newly created club ID should not be null!");
    }



    @Test(priority = 4)
    public void getSingleClub(){
        Response response = ClubEndpoint.getSingleClub(clubAdminId);
        response.then().log().body();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200,"Expected status code 200 not received");
        Assert.assertTrue(jsonPath.getMap("$").containsKey("name"), "Response does not contain 'name' property");
    }


    @Test(priority = 5)
    public void deleteClub(){
        Response response = ClubEndpoint.deleteClub(clubAdminId);
        response.then().log().body();
    }
}
