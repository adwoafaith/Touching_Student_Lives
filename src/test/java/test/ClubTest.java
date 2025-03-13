package test;

import Payload.ClubPayload;
import endpoints.ClubEndpoint;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ClubTest {
    private static String newlyCreatedClubId;
    private static String clubAdminId;
    private static String institutionId;
    private static String countryId;

    @Test(priority = 1)
    public void getClubTestCase() {
        Response response = ClubEndpoint.getClubs();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);

        // Parse JSON response
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> clubs = jsonPath.getList("data");

        boolean clubFound = false;

        // Extract the admin's club details
        for (Map<String, Object> club : clubs) {
            Map<String, Object> createdBy = (Map<String, Object>) club.get("created_by");
            String clubAdminEmail = createdBy.get("email").toString();

            if (clubAdminEmail.equals(LoginTest.email)) {
                //clubAdminId = club.get("club_admin").toString();
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

    // Data Provider that supplies ClubPayload with extracted IDs
    @DataProvider(name = "clubDataProvider")
    public Object[][] provideClubData() {
        ClubPayload clubPayload = new ClubPayload();
        clubPayload.setName("Automation Club");
        clubPayload.setDescription("A test club created using RestAssured");
        clubPayload.setInstitution_id(institutionId);  // Use extracted institution ID
        clubPayload.setCountry_id(countryId);  // Use extracted country ID
        clubPayload.setIs_active(true);
        clubPayload.setClub_logo_url("https://example.com/logo.png");
        clubPayload.setClub_banner_url("https://example.com/banner.png");

        return new Object[][]{{clubPayload}};
    }



    @Test(priority = 2, dependsOnMethods = "getClubTestCase", dataProvider = "clubDataProvider")
    public void createClubTestCase(ClubPayload clubPayload) {
        Response response = ClubEndpoint.createClub(clubPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);

        // Attempt to extract the club ID from the "Location" header
        String locationHeader = response.getHeader("Location");
        if (locationHeader != null && locationHeader.contains("/clubs/")) {
             String clubId = locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
            // Ensure clubId was extracted correctly
            Assert.assertNotNull(clubId, "Club ID should not be null!");

            System.out.println("Extracted Club ID from Headers: " + clubId);
        }

    }



    //fetch all clubs the second time in other to get the latest or newly create club
    // Declare the variable at the class level so it's accessible across methods

    @Test(priority = 3, dependsOnMethods = "createClubTestCase")
    public void getClubTestCaseAfterCreation() {
        Response response = ClubEndpoint.getClubs();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> updatedClubs = jsonPath.getList("data");

        boolean clubFound = false;

        for (Map<String, Object> club : updatedClubs) {
            String clubName = club.get("name").toString();

            if (clubName.equals("Automation Club")) {
                newlyCreatedClubId = club.get("id").toString(); // Assigning the ID to the class variable
                System.out.println("Newly Created Club ID: " + newlyCreatedClubId);
                clubFound = true;
                break;
            }
        }

        Assert.assertTrue(clubFound, "No new club found after creation!");
        Assert.assertNotNull(newlyCreatedClubId, "Newly created club ID should not be null!");
    }



    @Test(priority = 4, dependsOnMethods = "getClubTestCaseAfterCreation")
    public void getSingleClub(){

        Response response = ClubEndpoint.getSingleClub(newlyCreatedClubId);
        response.then().log().all();

        JsonPath jsonPath = response.jsonPath();

        //perform assertions
        Assert.assertEquals(response.getStatusCode(), 200,"Expected status code 200 not received");
        Assert.assertTrue(jsonPath.getMap("$").containsKey("name"), "Response does not contain 'name' property");
    }
}
