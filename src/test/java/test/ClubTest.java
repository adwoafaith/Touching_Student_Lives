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

    // Data Provider that supplies ClubPayload with extracted IDs
    @DataProvider(name = "clubDataProvider")
    public Object[][] provideClubData() {
        ClubPayload clubPayload = new ClubPayload();
        clubPayload.setName("Automation Club");
        clubPayload.setDescription("A test club created using RestAssured");
        clubPayload.setClub_admin(clubAdminId);  // Use extracted admin ID
        clubPayload.setInstitution_id(institutionId);  // Use extracted institution ID
        clubPayload.setCountry_id(countryId);  // Use extracted country ID
        clubPayload.setIs_active(true);
        clubPayload.setClub_logo_url("https://media.licdn.com/dms/image/v2/D4D12AQHUef-3Y2RTDQ/article-cover_image-shrink_720_1280/article-cover_image-shrink_720_1280/0/1690821267969?e=2147483647&v=beta&t=6VGH8FbKU8FNyofFadzL7sfI6dWCzyVCKqKpuMr7pxE");
        clubPayload.setClub_banner_url("https://media.licdn.com/dms/image/v2/D4E12AQEBftIxqu-Skw/article-cover_image-shrink_720_1280/article-cover_image-shrink_720_1280/0/1738324080832?e=2147483647&v=beta&t=DP1dQVn3jGTBvCQInf9FURPwTNqBLw8DK9FZHqE9F18");

        return new Object[][]{{clubPayload}};
    }

    @Test(priority = 2, dependsOnMethods = "getClubTestCase", dataProvider = "clubDataProvider")
    public void createClubTestCase(ClubPayload clubPayload) {
        Response response = ClubEndpoint.createClub(clubPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);

        // Additional validation
        JsonPath jsonPath = response.jsonPath();
        String createdClubName = jsonPath.getString("data.name");
        Assert.assertEquals(createdClubName, clubPayload.getName(), "Club name mismatch!");
    }
}
